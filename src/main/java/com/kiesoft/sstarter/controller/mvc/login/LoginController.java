package com.kiesoft.sstarter.controller.mvc.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends AbstractLoginController {

    @RequestMapping(value = ROUTING_LOGIN_PAGE, method = RequestMethod.GET)
    public String loginForm() {
        return VIEW_LOGIN_PAGE;
    }

}
