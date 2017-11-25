package com.kiesoft.sstarter.controller.mvc.role;

import com.kiesoft.sstarter.dto.role.RoleDTO;
import com.kiesoft.sstarter.exception.ElementNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.kiesoft.sstarter.controller.mvc.role.AbstractRoleController.ROUTING_ROLE_CONTROLLER;

@Controller
@RequestMapping(ROUTING_ROLE_CONTROLLER)
public class RoleController extends AbstractRoleController {

    @RequestMapping(value = ROUTING_MANAGE, method = RequestMethod.GET)
    public String manage(Model model, Pageable pageable) {
        model.addAttribute(MVC_BIND_ITEMS, roleService.findAll(pageable));

        return VIEW_MANAGE;
    }

    @RequestMapping(value = ROUTING_VIEW, method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        RoleDTO dto = roleService.findOne(id);

        model.addAttribute(MVC_BIND_ITEM, dto);

        return VIEW_VIEW;
    }

    @RequestMapping(value = ROUTING_CREATE_FORM, method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute(FORM_BIND, new RoleDTO());

        return VIEW_CREATE_FORM;
    }

    @RequestMapping(value = ROUTING_CREATE, method = RequestMethod.POST)
    public String create(@ModelAttribute(FORM_BIND) @Validated RoleDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_BIND, dto);
            return VIEW_CREATE_FORM;
        }

        roleService.save(dto);

        return VIEW_CREATE;
    }

    @RequestMapping(value = ROUTING_UPDATE_FORM, method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
        RoleDTO dto = roleService.findOne(id);

        // Lock troubled parameters
        httpSession.setAttribute(FORM_SECURITYKEY_ID, dto.getId());

        model.addAttribute(FORM_BIND, dto);

        return VIEW_UPDATE_FORM;
    }

    @RequestMapping(value = ROUTING_UPDATE, method = RequestMethod.POST)
    public String update(@ModelAttribute(FORM_BIND) @Validated RoleDTO dto, BindingResult result, Model model, HttpSession httpSession) {
        if (result.hasErrors()) {
            model.addAttribute(FORM_BIND, dto);
            return VIEW_UPDATE_FORM;
        }

        RoleDTO dbRoleDTO = roleService.findOne((Long) httpSession.getAttribute(FORM_SECURITYKEY_ID));
        dbRoleDTO.setRolename(dto.getRolename());
        roleService.save(dbRoleDTO);

        return VIEW_UPDATE;
    }

    @RequestMapping(value = ROUTING_DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable Long id) throws ElementNotFoundException {
        RoleDTO dto = roleService.findOne(id);

        roleService.delete(dto);

        return VIEW_DELETE;
    }
}