package com.kiesoft.sstarter.controller.mvc.language;

import com.kiesoft.sstarter.service.language.LanguageService;
import com.kiesoft.sstarter.validator.LanguageDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractLanguageController {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractLanguageController.class);

    public static final String ROUTING_LANGUAGE_CONTROLLER = "/language";

    public final static String ROUTING_MANAGE = "/manage";
    public final static String VIEW_MANAGE = "language/manage";

    public final static String ROUTING_VIEW = "/view/{id}";
    public final static String VIEW_VIEW = "language/view";

    public final static String ROUTING_CREATE_FORM = "/createForm";
    public final static String VIEW_CREATE_FORM = "language/create";

    public final static String ROUTING_CREATE = "/create";
    public final static String VIEW_CREATE = "redirect:/language/manage";

    public final static String ROUTING_UPDATE_FORM = "/updateForm/{id}";
    public final static String VIEW_UPDATE_FORM = "language/update";

    public final static String ROUTING_UPDATE = "/update";
    public final static String VIEW_UPDATE = "redirect:/language/manage";

    public final static String ROUTING_DELETE = "/delete/{id}";
    public final static String VIEW_DELETE = "redirect:/language/manage";

    /**
     * The same String is used in: 1) Backend: InitBinder for bind the name of
     * the form 2) Backend: ModelAttribute, in the methods create and update 3)
     * Frontend: commandName in <form:form commandName="entityDTO" ... 4)
     * Frontend: JSTL in the views
     */
    protected final static String FORM_BIND = "languageDTO";
    protected final static String MVB_BIND_LANGUAGES = "languages";

    /**
     * Used to lock a key-value in the session in order to check if a parameter
     * wasnt manipulated in the update form This variable is the key and the
     * value will be set in the controller
     */
    protected static final String FORM_SECURITYKEY_ID = "id";

    @Autowired
    protected LanguageService languageService;

    @Autowired
    private LanguageDTOValidator languageDTOValidator;

    @InitBinder(FORM_BIND)
    public void initBinderLanguage(WebDataBinder binder) {
        binder.setValidator(languageDTOValidator);
    }

}
