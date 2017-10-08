package com.kiesoft.sstarter.controller.user;

import com.kiesoft.sstarter.controller.TemplateIntegrationTest;
import com.kiesoft.sstarter.dto.role.RoleDTO;
import com.kiesoft.sstarter.dto.user.UserDTO;
import com.kiesoft.sstarter.jpa.entity.user.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.kiesoft.sstarter.controller.exception.AbstractExceptionControllerAdvice.VIEW_ELEMENT_BAD_REQUEST_EXCEPTION;
import static com.kiesoft.sstarter.controller.exception.AbstractExceptionControllerAdvice.VIEW_ELEMENT_NOT_FOUND_EXCEPTION;
import static com.kiesoft.sstarter.controller.login.AbstractLoginController.ROUTING_LOGIN_PAGE;
import static com.kiesoft.sstarter.controller.user.AbstractUserController.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerIntegrationTest extends TemplateIntegrationTest {

    @Before
    public void setUp() {
        this.mockHttpSession = new MockHttpSession();
    }

    /**
     * Manage endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitManage_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitManage_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitManage_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitManage_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_MANAGE))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVC_BIND_ITEMS))
                .andExpect(view().name(VIEW_MANAGE));
    }

    /**
     * View Endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitView_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_VIEW, ID_USER_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitView_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_VIEW, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitView_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_VIEW, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitViewAndUserDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_VIEW, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitView_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_VIEW, ID_USER_ADMIN))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(MVC_BIND_ITEM))
                .andExpect(view().name(VIEW_VIEW));
    }


    /**
     * CreateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateForm_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_CREATE_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    /**
     * Create endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitCreate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingUsernameAndPasswordAndPoints_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingUsernameAndPassword_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingUsernameAndPoints_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingUsername_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingPasswordAndPoints_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndMissingPassword_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndUsernameTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "pe")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndUsernameTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "thisisveryveryveryveryveryverylongusername")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndPasswordTooLow_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "username")
                .param("password", "pa"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndPasswordTooBig_Then_ValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "username")
                .param("password", "thisisveryveryveryveryveryverylongpassword"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(view().name(VIEW_CREATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitCreateAndValidValidation_Then_SuccessfullyCreated() throws Exception {
        String password = "password";
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_CREATE)
                .with(csrf())
                .param("username", "username")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_CREATE));

        // Check there is an extra user
        List<UserEntity> users = new ArrayList<>();
        CollectionUtils.addAll(users, userRepository.findAll().iterator());
        assertThat(users.size(), is(5));

        // Check new user attributes
        UserDTO userDTO = userService.findOne(1L);
        assertThat(userDTO.getUsername(), is("username"));
        assertThat(userDTO.getPassword(), is(DigestUtils.md5Hex(password)));
        assertThat(userDTO.getEnabled(), is(Boolean.TRUE));
    }

    /**
     * UpdateForm endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdateForm_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, ID_USER_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdateForm_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateFormAndUserDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateFormWithAdminUserThatHasOneRole_Then_AccessGranted() throws Exception {
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, ID_USER_ADMIN)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
        assertThat(mockHttpSession.getAttribute(FORM_SECURITYKEY_ID), is(ID_USER_ADMIN));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateFormWithPedrolaUserThatDoesNotHaveAnyRole_Then_AccessGranted() throws Exception {
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_PEDROLA).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_UPDATE_FORM, ID_USER_PEDROLA)
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
        assertThat(mockHttpSession.getAttribute(FORM_SECURITYKEY_ID), is(ID_USER_PEDROLA));
    }

    /**
     * Update endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitUpdate_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingUsernameAndPasswordAndPoints_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingUsernameAndPassword_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingUsernameAndPoints_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingUsername_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingPasswordAndPoints_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndMissingPassword_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndUsernameTooLow_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "pe")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndUsernameTooBig_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "thisisveryveryveryveryveryverylongusername")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndPasswordTooLow_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username")
                .param("password", "pa")
                .param("points", "50"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateAndPasswordTooBig_Then_ValidationError() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        List<RoleDTO> expectedRoles = roleService.findAll();
        // Missing user roles
        expectedRoles.removeAll(userService.findOne(ID_USER_ADMIN).getRoles());
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username")
                .param("password", "thisisveryveryveryveryveryverylongpassword")
                .param("points", "50"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(FORM_BIND))
                .andExpect(model().attribute(MVC_BIND_ROLES, expectedRoles))
                .andExpect(view().name(VIEW_UPDATE_FORM));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateWithAdminUser_Then_AttributesAreUpdated() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        String password = "password";
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username")
                .param("password", "password")
                .param("enabled", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_UPDATE));

        // Check user attributes
        UserDTO userDTO = userService.findOne(ID_USER_ADMIN);
        assertThat(userDTO.getUsername(), is("username"));
        assertThat(userDTO.getPassword(), is(DigestUtils.md5Hex(password)));
        assertThat(userDTO.getEnabled(), is(Boolean.TRUE));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitUpdateWithAdminUserAndWithoutEnabledAttribute_Then_AttributesAreUpdatedAndUserIsDisabled() throws Exception {
        mockHttpSession.setAttribute(FORM_SECURITYKEY_ID, ID_USER_ADMIN);
        String password = "password";
        mockMvc.perform(MockMvcRequestBuilders.post(ROUTING_USER_CONTROLLER + ROUTING_UPDATE)
                .session(mockHttpSession)
                .with(csrf())
                .param("username", "username")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_UPDATE));

        // Check user attributes
        UserDTO userDTO = userService.findOne(ID_USER_ADMIN);
        assertThat(userDTO.getUsername(), is("username"));
        assertThat(userDTO.getPassword(), is(DigestUtils.md5Hex(password)));
        assertThat(userDTO.getEnabled(), is(Boolean.FALSE));
    }

    /**
     * Add Role endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitAddRole_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_ADD_ROLE, ID_USER_ADMIN, ID_ROLE_EDITOR))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitAddRole_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_ADD_ROLE, ID_USER_ADMIN, ID_ROLE_EDITOR))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitAddRole_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_ADD_ROLE, ID_USER_ADMIN, ID_ROLE_EDITOR))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitAddRoleAndUserAlreadyContainsIt_Then_BadRequestException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_ADD_ROLE, ID_USER_ADMIN, ID_ROLE_ADMIN))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_BAD_REQUEST_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitAddRoleAndUserDoesNotHaveIt_Then_AddRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_ADD_ROLE, ID_USER_PEDROLA, ID_ROLE_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_ADD_REMOVE_ROLE + ID_USER_PEDROLA));

        // Check the user has the new Role
        UserDTO userDTO = userService.findOne(ID_USER_PEDROLA);
        assertThat(userDTO.getRoles().size(), is(1));
        assertThat(userDTO.getRoles().contains(roleService.getRoleAdmin()), is(Boolean.TRUE));
    }

    /**
     * Remove Role endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitRemoveRole_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_REMOVE_ROLE, ID_USER_ADMIN, ID_ROLE_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitRemoveRole_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_REMOVE_ROLE, ID_USER_ADMIN, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitRemoveRole_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_REMOVE_ROLE, ID_USER_ADMIN, ID_ROLE_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitRemoveRoleAndUserDoesNotContainsIt_Then_BadRequestException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_REMOVE_ROLE, ID_USER_ADMIN, ID_ROLE_EDITOR))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_BAD_REQUEST_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitRemoveRoleAndUserHasIt_Then_RemoveRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_REMOVE_ROLE, ID_USER_ADMIN, ID_ROLE_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_ADD_REMOVE_ROLE + ID_USER_ADMIN));

        // Check the user does not contain that Role
        UserDTO userDTO = userService.findOne(ID_USER_ADMIN);
        assertThat(userDTO.getRoles().size(), is(0));
    }

    /**
     * Delete endpoint
     */
    @Test
    public void given_NotAuthenticateUser_When_HitDelete_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_DELETE, ID_USER_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitDelete_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_DELETE, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitDelete_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_DELETE, ID_USER_ADMIN))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_EditorUser_When_HitDeleteAndUserDoesNotExist_Then_ElementNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_DELETE, 0L))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ELEMENT_NOT_FOUND_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_EditorUser_When_HitDelete_Then_SuccessfullyDeletedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USER_CONTROLLER + ROUTING_DELETE, ID_USER_ADMIN))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_DELETE));

        // Check that user was deleted
        List<UserEntity> users = new ArrayList<>();
        CollectionUtils.addAll(users, userRepository.findAll().iterator());
        assertThat(users.size(), is(3));
    }


}
