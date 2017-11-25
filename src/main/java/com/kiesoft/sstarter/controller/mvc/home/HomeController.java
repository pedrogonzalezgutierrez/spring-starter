package com.kiesoft.sstarter.controller.mvc.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class HomeController extends AbstractHomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = ROUTING_HOME, method = RequestMethod.GET)
    public String homepage(Locale locale) {
        return VIEW_HOME;
    }

}