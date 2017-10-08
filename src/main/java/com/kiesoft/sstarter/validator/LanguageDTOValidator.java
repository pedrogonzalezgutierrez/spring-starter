package com.kiesoft.sstarter.validator;

import com.kiesoft.sstarter.dto.language.LanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LanguageDTOValidator implements Validator {

    @Autowired
    private Environment env;

    @Autowired
    private ValidatorHelper validatorHelper;

    @Override
    public boolean supports(Class<?> clazz) {
        return LanguageDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LanguageDTO dto = (LanguageDTO) target;
        dto.setLanguageName(validatorHelper.sanitizeString(dto.getLanguageName()));
        dto.setCode(validatorHelper.sanitizeString(dto.getCode()));
        dto.setFlagCode(validatorHelper.sanitizeString(dto.getFlagCode()));

        validatorHelper.rejectFieldIfEmptyOrWhitespace("languageName", errors);
        validatorHelper.rejectFieldIfEmptyOrWhitespace("code", errors);
        validatorHelper.rejectFieldIfEmptyOrWhitespace("flagCode", errors);

        if (errors.hasErrors() == true) {
            return;
        }

        validatorHelper.rejectFieldIfStringMinMaxLength(
                "languageName",
                dto.getLanguageName(),
                Integer.valueOf(env.getProperty("limit.language.name-min")),
                Integer.valueOf(env.getProperty("limit.language.name-max")),
                errors);

        validatorHelper.rejectFieldIfStringMinMaxLength(
                "code",
                dto.getCode(),
                Integer.valueOf(env.getProperty("limit.language.code-min")),
                Integer.valueOf(env.getProperty("limit.language.code-max")),
                errors);

        validatorHelper.rejectFieldIfStringMinMaxLength(
                "flagCode",
                dto.getFlagCode(),
                Integer.valueOf(env.getProperty("limit.language.flagCode-min")),
                Integer.valueOf(env.getProperty("limit.language.flagCode-max")),
                errors);
    }

}
