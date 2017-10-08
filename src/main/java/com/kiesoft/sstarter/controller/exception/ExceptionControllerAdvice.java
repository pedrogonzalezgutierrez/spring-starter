package com.kiesoft.sstarter.controller.exception;

import com.kiesoft.sstarter.exception.AccessDeniedException;
import com.kiesoft.sstarter.exception.BadRequestException;
import com.kiesoft.sstarter.exception.ElementNotFoundException;
import com.kiesoft.sstarter.exception.PersistenceProblemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice extends AbstractExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    public static final String USER_POINTS = "userPoints";
    public static final String ITEM_POINTS = "itemPoints";

    @ExceptionHandler(ElementNotFoundException.class)
    public String handleElementNotFoundException(ElementNotFoundException e) {
        logger.warn("ElementNotFoundException: Element not found");
        return VIEW_ELEMENT_NOT_FOUND_EXCEPTION;
    }

    @ExceptionHandler(PersistenceProblemException.class)
    public String handlePersistenceProblemException(PersistenceProblemException e) {
        logger.warn("PersistenceProblemException: Unable to persits this element");
        return VIEW_ELEMENT_PERSISTENCE_PROBLEM_EXCEPTION;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e) {
        logger.warn("AccessDeniedException: No allow to access the resource: " + e.getTypeElement());
        return VIEW_ELEMENT_ACCESS_DENIED_EXCEPTION;
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException e) {
        logger.warn("BadRequestException: Unable to request, " + e.getTypeElement());
        return VIEW_ELEMENT_BAD_REQUEST_EXCEPTION;
    }

}