package com.doubleskyline.core.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * @Description
 * @Author ZhangZhengyang
 * @Date 2019-08-12 14:39
 **/
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "{phone}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
