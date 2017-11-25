package com.kiesoft.sstarter.controller.mvc.user;

import com.kiesoft.sstarter.service.role.RoleService;
import com.kiesoft.sstarter.service.user.UserService;
import com.kiesoft.sstarter.validator.UserDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractUserController {

    public final static String ROUTING_USER_CONTROLLER = "/user";

    public final static String ROUTING_MANAGE = "/manage";
    public final static String VIEW_MANAGE = "user/manage";

    public final static String ROUTING_VIEW = "/view/{id}";
    public final static String VIEW_VIEW = "user/view";

    public final static String ROUTING_CREATE_FORM = "/createForm";
    public final static String VIEW_CREATE_FORM = "user/create";

    public final static String ROUTING_CREATE = "/create";
    public final static String VIEW_CREATE = "redirect:/user/manage";

    public final static String ROUTING_UPDATE_FORM = "/updateForm/{id}";
    public final static String VIEW_UPDATE_FORM = "user/update";

    public final static String ROUTING_UPDATE = "/update";
    public final static String VIEW_UPDATE = "redirect:/user/manage";

    public final static String ROUTING_DELETE = "/delete/{id}";
    public final static String VIEW_DELETE = "redirect:/user/manage";

    public final static String ROUTING_ADD_ROLE = "/add/{idUser}/{idRole}";
    public final static String ROUTING_REMOVE_ROLE = "/remove/{idUser}/{idRole}";
    public final static String VIEW_ADD_REMOVE_ROLE = "redirect:/user/updateForm/";

    /**
     * The same String is used in: 1) Backend: InitBinder for bind the name of
     * the form 2) Backend: ModelAttribute, in the methods create and update 3)
     * Frontend: commandName in <form:form commandName="entityDTO" ... 4)
     * Frontend: JSTL in the views
     */
    protected static final String FORM_BIND = "userDTO";

    /**
     * MVC BINDS
     */
    protected static final String MVC_BIND_ITEM = "item";
    protected static final String MVC_BIND_ITEMS = "items";
    protected static final String MVC_BIND_ROLES = "roles";

    /**
     * Used to lock a key-value in the session in order to check it a parameter
     * wasnt manipulated in the update form This variable is the key and the
     * value will be set in the controller
     */
    protected static final String FORM_SECURITYKEY_ID = "id";

    @Autowired
    protected UserService userService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    private UserDTOValidator userDTOValidator;

    @InitBinder(FORM_BIND)
    public void initBinderUser(WebDataBinder binder) {
        binder.setValidator(userDTOValidator);
    }

}
