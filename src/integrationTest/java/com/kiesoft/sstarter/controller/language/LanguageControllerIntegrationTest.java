package com.kiesoft.sstarter.controller.language;

import com.kiesoft.sstarter.controller.TemplateIntegrationTest;
import com.kiesoft.sstarter.dto.language.LanguageDTO;
import com.kiesoft.sstarter.jpa.entity.language.LanguageEntity;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.kiesoft.sstarter.controller.exception.AbstractExceptionControllerAdvice.VIEW_ELEMENT_NOT_FOUND_EXCEPTION;
import static com.kiesoft.sstarter.controller.language.AbstractLanguageController.*;
import static com.kiesoft.sstarter.controller.login.AbstractLoginController.ROUTING_LOGIN_PAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LanguageControllerIntegrationTest extends TemplateIntegrationTest {

    @Before
    public void setUp() {
        this.mockHttpSession = new MockHttpSession();
    }

    /**
     * Manage endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitManage_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitManage_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitManage_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVB_BIND_LANGUAGES))
                .andExpect(view().name(VIEW_MANAGE));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitManage_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVB_BIND_LANGUAGES))
                .andExpect(view().name(VIEW_MANAGE));
    }

    /**
     * View Endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitView_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_VIEW, ID_LANGUAGE_SPANISH))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitView_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_VIEW, ID_LANGUAGE_SPANISH))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitView_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_VIEW, ID_LANGUAGE_SPANISH))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_VIEW));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitViewAndItDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_VIEW, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitView_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_VIEW, ID_LANGUAGE_SPANISH))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_VIEW));
    }

    /**
     * CreateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    /**
     * Create endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingLanguageNameAndCodeAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingLanguageNameAndCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("flagCode", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingLanguageNameAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("code", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingLanguageName_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("flagCode", "password")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingCodeAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "username")
                .param("flagCode", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "username")
                .param("code", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndLanguageNameTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "E")
                .param("flagCode", "es")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndLanguageNameTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "thisisaveryveryveryveryverylonglanguagename")
                .param("flagCode", "es")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndFlagCodeTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "e")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndFlagCodeTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "thisisaveryveryveryverylongflagcode")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndCodeTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "es")
                .param("code", "e"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndCodeTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "es")
                .param("code", "thisisaveryveryveryveryverylongcode"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreate_Then_SuccessfullyCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("languageName", "Quechua")
                .param("flagCode", "qu")
                .param("code", "qu"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_CREATE));

        // Check there is an extra language
        List<LanguageEntity> languages = new ArrayList<>();
        CollectionUtils.addAll(languages, languageRepository.findAll().iterator());
        assertThat(languages.size(), is(10));

        // Check new Language attributes
        LanguageDTO languageDTO = languageService.findOne(1L);
        assertThat(languageDTO.getLanguageName(), is("Quechua"));
        assertThat(languageDTO.getFlagCode(), is("qu"));
        assertThat(languageDTO.getCode(), is("qu"));
    }

    /**
     * UpdateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE_FORM, ID_LANGUAGE_SPANISH))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE_FORM, ID_LANGUAGE_SPANISH))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE_FORM, ID_LANGUAGE_SPANISH)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
        assertThat(mockHttpSession.getAttribute(FORM_SECURITYKEY_ID), is(ID_LANGUAGE_SPANISH));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateFormAndLanguageDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE_FORM, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE_FORM, ID_LANGUAGE_SPANISH)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
        assertThat(mockHttpSession.getAttribute(FORM_SECURITYKEY_ID), is(ID_LANGUAGE_SPANISH));
    }

    /**
     * Update endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingLanguageNameAndCodeAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingLanguageNameAndCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("flagCode", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingLanguageNameAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("code", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingLanguageName_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("flagCode", "password")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingCodeAndFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "username")
                .param("flagCode", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingFlagCode_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "username")
                .param("code", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndLanguageNameTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "E")
                .param("flagCode", "es")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndLanguageNameTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "thisisaveryveryveryveryverylonglanguagename")
                .param("flagCode", "es")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndFlagCodeTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "e")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndFlagCodeTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "thisisaveryveryveryverylongflagcode")
                .param("code", "es"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndCodeTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "es")
                .param("code", "e"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndCodeTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("languageName", "Español")
                .param("flagCode", "es")
                .param("code", "thisisaveryveryveryveryverylongcode"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdate_Then_AttributesAreUpdated() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_LANGUAGE_SPANISH);
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_LANGUAGE_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("languageName", "Quechua")
                .param("flagCode", "qu")
                .param("code", "qu"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_UPDATE));

        // Check updated Language attributes
        LanguageDTO languageDTO = languageService.findOne(ID_LANGUAGE_SPANISH);
        assertThat(languageDTO.getLanguageName(), is("Quechua"));
        assertThat(languageDTO.getFlagCode(), is("qu"));
        assertThat(languageDTO.getCode(), is("qu"));
    }

    /**
     * Delete endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitDelete_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_DELETE, ID_LANGUAGE_SPANISH))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitDelete_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_DELETE, ID_LANGUAGE_SPANISH))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitDelete_Then_SuccessfullyDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_DELETE, ID_LANGUAGE_SPANISH))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_DELETE));

        // Check that language was deleted
        List<LanguageEntity> languages = new ArrayList<>();
        CollectionUtils.addAll(languages, languageRepository.findAll().iterator());
        assertThat(languages.size(), is(8));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitDeleteAndLanguageDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_DELETE, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitDelete_Then_SuccessfullyDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_LANGUAGE_CONTROLLER + ROUTING_DELETE, ID_LANGUAGE_SPANISH))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_DELETE));

        // Check that language was deleted
        List<LanguageEntity> languages = new ArrayList<>();
        CollectionUtils.addAll(languages, languageRepository.findAll().iterator());
        assertThat(languages.size(), is(8));
    }

}
