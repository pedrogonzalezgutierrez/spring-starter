package com.kiesoft.sstarter.controller.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.kiesoft.sstarter.controller.profile.AbstractUserProfileController.ROUTING_USERPROFILE_CONTROLLER;

@Controller
@RequestMapping(ROUTING_USERPROFILE_CONTROLLER)
public class UserProfileController extends AbstractUserProfileController {

    @RequestMapping(value = ROUTING_SHOW, method = RequestMethod.GET)
    public String manage(Model model) {
        return VIEW_SHOW;
    }

}
