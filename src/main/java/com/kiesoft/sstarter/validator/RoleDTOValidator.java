package com.kiesoft.sstarter.validator;

import com.kiesoft.sstarter.dto.role.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleDTOValidator implements Validator {

    @Autowired
    private Environment env;

    @Autowired
    private ValidatorHelper validatorHelper;

    private final static int ROLENAME_MIN_SIZE = 4;
    private final static int ROLENAME_MAX_SIZE = 15;

    @Override
    public boolean supports(Class<?> clazz) {
        return RoleDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RoleDTO dto = (RoleDTO) target;
        dto.setRolename(validatorHelper.sanitizeString(dto.getRolename()));

        validatorHelper.rejectFieldIfEmptyOrWhitespace("rolename", errors);

        if (errors.hasErrors() == true) {
            return;
        }

        validatorHelper.rejectFieldIfStringMinMaxLength("rolename", dto.getRolename(), Integer.valueOf(env.getProperty("limit.role.name-min")), Integer.valueOf(env.getProperty("limit.role.name-max")), errors);
    }

}
