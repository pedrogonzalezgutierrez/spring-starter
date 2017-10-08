package com.kiesoft.sstarter.controller.role;

import com.kiesoft.sstarter.service.role.RoleService;
import com.kiesoft.sstarter.validator.RoleDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractRoleController {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractRoleController.class);

    public final static String ROUTING_ROLE_CONTROLLER = "/role";

    public final static String ROUTING_MANAGE = "/manage";
    public final static String VIEW_MANAGE = "role/manage";

    public final static String ROUTING_VIEW = "/view/{id}";
    public final static String VIEW_VIEW = "role/view";

    public final static String ROUTING_CREATE_FORM = "/createForm";
    public final static String VIEW_CREATE_FORM = "role/create";

    public final static String ROUTING_CREATE = "/create";
    public final static String VIEW_CREATE = "redirect:/role/manage";

    public final static String ROUTING_UPDATE_FORM = "/updateForm/{id}";
    public final static String VIEW_UPDATE_FORM = "role/update";

    public final static String ROUTING_UPDATE = "/update";
    public final static String VIEW_UPDATE = "redirect:/role/manage";

    public final static String ROUTING_DELETE = "/delete/{id}";
    public final static String VIEW_DELETE = "redirect:/role/manage";

    /**
     * The same String is used in: 1) Backend: InitBinder for bind the name of
     * the form 2) Backend: ModelAttribute, in the methods create and update 3)
     * Frontend: commandName in <form:form commandName="entityDTO" ... 4)
     * Frontend: JSTL in the views
     */
    protected static final String FORM_BIND = "roleDTO";

    /**
     * MVC BINDS
     */
    protected static final String MVC_BIND_ITEM = "item";
    protected static final String MVC_BIND_ITEMS = "items";

    /**
     * Used to lock a key-value in the session in order to check it a parameter
     * wasnt manipulated in the update form This variable is the key and the
     * value will be set in the controller
     */
    protected static final String FORM_SECURITYKEY_ID = "id";

    @Autowired
    protected RoleService roleService;

    @Autowired
    private RoleDTOValidator roleDTOValidator;

    @InitBinder(FORM_BIND)
    public void initBinderRole(WebDataBinder binder) {
        binder.setValidator(roleDTOValidator);
    }

}
