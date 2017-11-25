package com.kiesoft.sstarter.controller.mvc.profile;

import com.kiesoft.sstarter.controller.TemplateIntegrationTest;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kiesoft.sstarter.controller.mvc.login.AbstractLoginController.ROUTING_LOGIN_PAGE;
import static com.kiesoft.sstarter.controller.mvc.profile.AbstractUserProfileController.ROUTING_SHOW;
import static com.kiesoft.sstarter.controller.mvc.profile.AbstractUserProfileController.ROUTING_USERPROFILE_CONTROLLER;
import static com.kiesoft.sstarter.controller.mvc.profile.AbstractUserProfileController.VIEW_SHOW;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserProfileControllerIntegrationTest extends TemplateIntegrationTest {

    @Test
    public void given_NotAuthenticateUser_When_HitManager_Then_RedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USERPROFILE_CONTROLLER + ROUTING_SHOW))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(HOST + ROUTING_LOGIN_PAGE));
    }

    @Test
    @WithUserDetails(value = "pedrola")
    public void given_NormalUser_When_HitManageAvatar_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USERPROFILE_CONTROLLER + ROUTING_SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_SHOW));
    }

    @Test
    @WithUserDetails(value = "editor")
    public void given_EditorUser_When_HitManageAvatar_Then_AccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USERPROFILE_CONTROLLER + ROUTING_SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_SHOW));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void given_AdminUser_When_HitManageAvatar_Then_AccessGranted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROUTING_USERPROFILE_CONTROLLER + ROUTING_SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_SHOW));
    }




}
