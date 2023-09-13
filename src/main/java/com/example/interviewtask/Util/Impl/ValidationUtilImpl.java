package com.example.interviewtask.Util.Impl;

import com.example.interviewtask.Util.ValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    @Operation(description = "validationUtil which validate email and first name of User")
    public <E> Set<ConstraintViolation<E>> isValid(E entity) {
        return validator.validate(entity);
    }


}
