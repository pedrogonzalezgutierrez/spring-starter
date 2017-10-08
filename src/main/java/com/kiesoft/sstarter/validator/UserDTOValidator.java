package com.kiesoft.sstarter.validator;

import com.kiesoft.sstarter.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDTOValidator implements Validator {

    @Autowired
    private Environment env;

    @Autowired
    private ValidatorHelper validatorHelper;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDTO dto = (UserDTO) target;
        dto.setUsername(validatorHelper.sanitizeString(dto.getUsername()));
        dto.setPassword(validatorHelper.sanitizeString(dto.getPassword()));

        validatorHelper.rejectFieldIfEmptyOrWhitespace("username", errors);
        validatorHelper.rejectFieldIfEmptyOrWhitespace("password", errors);

        if (errors.hasErrors() == true) {
            return;
        }

        validatorHelper.rejectFieldIfStringMinMaxLength(
                "username",
                dto.getUsername(),
                Integer.valueOf(env.getProperty("limit.user.username-min")),
                Integer.valueOf(env.getProperty("limit.user.username-max")),
                errors);

        validatorHelper.rejectFieldIfStringMinMaxLength(
                "password",
                dto.getPassword(),
                Integer.valueOf(env.getProperty("limit.user.password-min")),
                Integer.valueOf(env.getProperty("limit.user.password-max")),
                errors);

    }

}
