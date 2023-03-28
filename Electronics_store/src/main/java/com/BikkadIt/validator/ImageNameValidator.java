package com.BikkadIt.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        //logic
        if(value.isBlank()){
            return false;
        }else {
            return true;
        }
    }
}
