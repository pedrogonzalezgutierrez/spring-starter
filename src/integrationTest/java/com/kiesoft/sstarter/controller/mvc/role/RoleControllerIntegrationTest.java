package com.kiesoft.sstarter.controller.mvc.role;

import com.kiesoft.sstarter.controller.TemplateIntegrationTest;
import com.kiesoft.sstarter.dto.role.RoleDTO;
import com.kiesoft.sstarter.jpa.entity.role.RoleEntity;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.kiesoft.sstarter.controller.mvc.exception.AbstractExceptionControllerAdvice.VIEW_ELEMENT_NOT_FOUND_EXCEPTION;
import static com.kiesoft.sstarter.controller.mvc.exception.AbstractExceptionControllerAdvice.VIEW_ELEMENT_PERSISTENCE_PROBLEM_EXCEPTION;
import static com.kiesoft.sstarter.controller.mvc.login.AbstractLoginController.ROUTING_LOGIN_PAGE;
import static com.kiesoft.sstarter.controller.mvc.role.AbstractRoleController.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RoleControllerIntegrationTest extends TemplateIntegrationTest {

    @Before
    public void setUp() {
        this.mockHttpSession = new MockHttpSession();
    }

    /**
     * Manage endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitManage_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitManage_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitManage_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitManage_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVC_BIND_ITEMS))
                .andExpect(view().name(VIEW_MANAGE));
    }

    /**
     * View Endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitView_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_VIEW, ID_ROLE_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitView_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_VIEW, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitView_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_VIEW, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitViewAndItDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_VIEW, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitView_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_VIEW, ID_ROLE_ADMIN))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVC_BIND_ITEM))
                .andExpect(view().name(VIEW_VIEW));
    }

    /**
     * CreateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    /**
     * Create endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingRolename_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndRolenameTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("rolename", "pe"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndRolenameTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("rolename", "thisisaveryveryveryveryveryveryveryrolename"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndValidValidation_Then_SuccessfullyCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("rolename", "ROLE_WHATEVER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_CREATE));

        // Check there is an extra role
        List<RoleEntity> roles = new ArrayList<>();
        CollectionUtils.addAll(roles, roleRepository.findAll().iterator());
        assertThat(roles.size(), is(4));

        // Check new Role attributes
        RoleDTO roleDTO = roleService.findOne(1L);
        assertThat(roleDTO.getRolename(), is("ROLE_WHATEVER"));
    }

    /**
     * UpdateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE_FORM, ID_ROLE_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE_FORM, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE_FORM, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateFormAndRoleDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE_FORM, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE_FORM, ID_ROLE_ADMIN)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
        assertThat(mockHttpSession.getAttribute(FORM_SECURITYKEY_ID), is(ID_ROLE_ADMIN));
    }

    /**
     * Update endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingRolename_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndRolenameTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("rolename", "pe"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndRolenameTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE)
                .with(csrf())
                .param("rolename", "thisisaveryveryveryveryveryveryveryrolename"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdate_Then_AttributesAreUpdated() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_ROLE_ADMIN);
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_ROLE_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("rolename", "ROLE_WHATEVER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_UPDATE));

        // Check role attributes
        RoleDTO roleDTO = roleService.findOne(ID_ROLE_ADMIN);
        assertThat(roleDTO.getRolename(), is("ROLE_WHATEVER"));
    }

    /**
     * Delete endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitDelete_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, ID_ROLE_STAFF))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitDelete_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, ID_ROLE_STAFF))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitDelete_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, ID_ROLE_STAFF))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitDeleteAndRoleDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitDeleteOfARoleNotBindToAnyUser_Then_SuccessfullyDeletedRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, ID_ROLE_STAFF))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_DELETE));

        // Check tha the role was deleted
        List<RoleEntity> roles = new ArrayList<>();
        CollectionUtils.addAll(roles, roleRepository.findAll().iterator());
        assertThat(roles.size(), is(2));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitDeleteOfARoleBindToUsers_Then_NotDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_ROLE_CONTROLLER + ROUTING_DELETE, ID_ROLE_ADMIN))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_PERSISTENCE_PROBLEM_EXCEPTION));

        // Check tha the role was not deleted
        List<RoleEntity> roles = new ArrayList<>();
        CollectionUtils.addAll(roles, roleRepository.findAll().iterator());
        assertThat(roles.size(), is(3));
    }


}
