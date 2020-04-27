package com.doubleskyline.core.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @Description
 * @Author ZhangZhengyang
 * @Date 2019-08-12 14:40
 **/
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone paramA) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if (StringUtils.isEmpty(phoneNo) || phoneNo.matches("^(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})$")) {
            return true;
        }
        return false;
    }
}

