package com.kiesoft.sstarter.controller.mvc.user;

import com.kiesoft.sstarter.dto.role.RoleDTO;
import com.kiesoft.sstarter.dto.user.UserDTO;
import com.kiesoft.sstarter.exception.BadRequestException;
import org.apache.commons.codec.digest.DigestUtils;
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

import java.util.List;
import java.util.Objects;

import static com.kiesoft.sstarter.controller.mvc.user.AbstractUserController.ROUTING_USER_CONTROLLER;

@Controller
@RequestMapping(ROUTING_USER_CONTROLLER)
public class UserController extends AbstractUserController {

    @RequestMapping(value = ROUTING_MANAGE, method = RequestMethod.GET)
    public String manage(Model model, Pageable pageable) {
        model.addAttribute(MVC_BIND_ITEMS, userService.findAll(pageable));

        return VIEW_MANAGE;
    }

    @RequestMapping(value = ROUTING_VIEW, method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model) {
        UserDTO dto = userService.findOne(id);

        model.addAttribute(MVC_BIND_ITEM, dto);

        return VIEW_VIEW;
    }

    @RequestMapping(value = ROUTING_CREATE_FORM, method = RequestMethod.GET)
    public String createForm(Model model) {
        UserDTO dto = new UserDTO();
        model.addAttribute(FORM_BIND, dto);

        return VIEW_CREATE_FORM;
    }

    @RequestMapping(value = ROUTING_CREATE, method = RequestMethod.POST)
    public String create(@ModelAttribute(FORM_BIND) @Validated UserDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM_BIND, dto);
            return VIEW_CREATE_FORM;
        }
        // Enable user
        dto.setEnabled(Boolean.TRUE);
        dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));

        userService.save(dto);

        return VIEW_CREATE;
    }

    @RequestMapping(value = ROUTING_UPDATE_FORM, method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
        UserDTO dto = userService.findOne(id);

        // Lock troubled parameters
        httpSession.setAttribute(FORM_SECURITYKEY_ID, dto.getId());

        model.addAttribute(FORM_BIND, dto);

        // Only add roles than the user doesnt have
        List<RoleDTO> roles = roleService.findAll();
        roles.removeAll(dto.getRoles());
        model.addAttribute("roles", roles);

        return VIEW_UPDATE_FORM;
    }

    @RequestMapping(value = ROUTING_UPDATE, method = RequestMethod.POST)
    public String update(@ModelAttribute(FORM_BIND) @Validated UserDTO dto, BindingResult result, Model model, HttpSession httpSession) {
        UserDTO dbDTO = userService.findOne((Long) httpSession.getAttribute(FORM_SECURITYKEY_ID));

        if (result.hasErrors()) {
            // Roles are empty when the object come back to the server
            dto.setId(dbDTO.getId());
            dto.setEnabled(dbDTO.getEnabled());
            dto.setRoles(dbDTO.getRoles());

            // Only add roles than the user doesnt have
            List<RoleDTO> roles = roleService.findAll();
            roles.removeAll(dto.getRoles());
            model.addAttribute("roles", roles);

            model.addAttribute(FORM_BIND, dto);
            return VIEW_UPDATE_FORM;
        }
        dbDTO.setUsername(dto.getUsername());
        dbDTO.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        dbDTO.setEnabled(Objects.isNull(dto.getEnabled()) ? Boolean.FALSE : dto.getEnabled());

        userService.save(dbDTO);

        return VIEW_UPDATE;
    }

    @RequestMapping(value = ROUTING_DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        UserDTO dto = userService.findOne(id);

        userService.delete(dto);

        return VIEW_DELETE;
    }

    @RequestMapping(value = ROUTING_ADD_ROLE, method = RequestMethod.GET)
    public String addRole(@PathVariable Long idUser, @PathVariable Long idRole, Model model) {
        return persistRole(idUser, idRole, true);
    }

    @RequestMapping(value = ROUTING_REMOVE_ROLE, method = RequestMethod.GET)
    public String removeRole(@PathVariable Long idUser, @PathVariable Long idRole, Model model) {
        return persistRole(idUser, idRole, false);
    }

    private String persistRole(Long idParent, Long idChild, Boolean add) {

        UserDTO userDTO = userService.findOne(idParent);
        RoleDTO roleDTO = roleService.findOne(idChild);

        if (add != null) {

            Boolean persist = Boolean.FALSE;

            if (add == true) {
                if (userDTO.getRoles().contains(roleDTO) == false) {
                    userDTO.getRoles().add(roleDTO);
                    persist = Boolean.TRUE;
                }

            } else if (add == false) {
                if (userDTO.getRoles().contains(roleDTO) == true) {
                    userDTO.getRoles().remove(roleDTO);
                    persist = Boolean.TRUE;
                }
            }

            if (persist == Boolean.TRUE) {
                userService.save(userDTO);
                return VIEW_ADD_REMOVE_ROLE + idParent;
            }
        }
        throw new BadRequestException("Unable to persist new Role");
    }
}
