package com.ead.authuser.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfConstraintImpl.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfConstraint {
    String message() default "Documento Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
