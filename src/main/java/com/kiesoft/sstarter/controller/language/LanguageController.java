package com.kiesoft.sstarter.controller.language;

import com.kiesoft.sstarter.dto.language.LanguageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.kiesoft.sstarter.controller.language.AbstractLanguageController.ROUTING_LANGUAGE_CONTROLLER;

@Controller
@RequestMapping(ROUTING_LANGUAGE_CONTROLLER)
public class LanguageController extends AbstractLanguageController {

    @RequestMapping(value = ROUTING_MANAGE, method = RequestMethod.GET)
    public String manage(Model model) {
        model.addAttribute(MVB_BIND_LANGUAGES, languageService.findAll());

        return VIEW_MANAGE;
    }

    @RequestMapping(value = ROUTING_VIEW, method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        LanguageDTO dto = languageService.findOne(id);

        model.addAttribute(FORM_BIND, dto);

        return VIEW_VIEW;
    }

    @RequestMapping(value = ROUTING_CREATE_FORM, method = RequestMethod.GET)
    public String createForm(Model model) {
        LanguageDTO dto = new LanguageDTO();
        model.addAttribute(FORM_BIND, dto);

        return VIEW_CREATE_FORM;
    }

    @RequestMapping(value = ROUTING_CREATE, method = RequestMethod.POST)
    public String create(@ModelAttribute(FORM_BIND) @Validated LanguageDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_BIND, dto);
            return VIEW_CREATE_FORM;
        }
        languageService.save(dto);

        return VIEW_CREATE;
    }

    @RequestMapping(value = ROUTING_UPDATE_FORM, method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
        LanguageDTO dto = languageService.findOne(id);

        // Lock troubled parameters
        httpSession.setAttribute(FORM_SECURITYKEY_ID, dto.getId());

        model.addAttribute(FORM_BIND, dto);

        return VIEW_UPDATE_FORM;
    }

    @RequestMapping(value = ROUTING_UPDATE, method = RequestMethod.POST)
    public String update(@ModelAttribute(FORM_BIND) @Validated LanguageDTO dto, BindingResult result, Model model, HttpSession httpSession) {
        if (result.hasErrors()) {
            model.addAttribute(FORM_BIND, dto);
            return VIEW_UPDATE_FORM;
        }

        LanguageDTO dbLanguageDTO = languageService.findOne((Long) httpSession.getAttribute(FORM_SECURITYKEY_ID));
        dbLanguageDTO.setLanguageName(dto.getLanguageName());
        dbLanguageDTO.setCode(dto.getCode());
        dbLanguageDTO.setFlagCode(dto.getFlagCode());

        languageService.save(dbLanguageDTO);

        return VIEW_UPDATE;
    }

    @RequestMapping(value = ROUTING_DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        LanguageDTO dto = languageService.findOne(id);

        languageService.delete(dto);

        return VIEW_DELETE;
    }

}