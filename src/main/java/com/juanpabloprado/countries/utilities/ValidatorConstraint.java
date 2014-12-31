package com.juanpabloprado.countries.utilities;

import com.google.common.base.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Juan on 12/24/2014.
 */
public class ValidatorConstraint {
    public static Optional<ArrayList<ErrorRepresentation>> validate(Object object ,Validator validator) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        // Are there any constraint violations?
        if (violations.size() > 0) {
            // Validation errors occurred
            ArrayList<ErrorRepresentation> validationMessages = new ArrayList<ErrorRepresentation>();

            for (ConstraintViolation<Object> violation : violations) {
                validationMessages.add(
                        new ErrorRepresentation(Response.Status.CONFLICT.getStatusCode(), violation.getPropertyPath().toString(), violation.getPropertyPath().toString() + ": " + violation.getMessage())
                       );

            }
            return Optional.of(validationMessages);
        }
        return Optional.absent();
    }
}
