package com.kiesoft.sstarter.validator;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class ValidatorHelper {

    @Autowired
    private Environment env;

    public void rejectFieldIfEmptyOrWhitespace(String field, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "error.field-required", "This field is required");
    }

    public void rejectFieldIfStringMinMaxLength(String field, String string, int min, int max, Errors errors) {
        if ((string.length() < min) || (string.length() > max)) {
            errors.rejectValue(field, "error.field-length", new Object[]{min, max}, "Wrong length");
        }
    }

    public void rejectFieldIfNumberMinMaxRange(String field, Integer number, int min, int max, Errors errors) {
        if ((number < min) || (number > max)) {
            errors.rejectValue(field, "error.field-range", new Object[]{min, max}, "Wrong range");
        }
    }

    public String sanitizeString(String unTrustedString) {
        // I dont allow anything, just raw text
        PolicyFactory sanitizer = new HtmlPolicyBuilder().toFactory();
        return sanitizer.sanitize(unTrustedString);
    }

}
