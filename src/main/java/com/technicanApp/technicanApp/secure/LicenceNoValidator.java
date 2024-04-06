package com.technicanApp.technicanApp.secure;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LicenceNoValidator implements ConstraintValidator<LicenceNo,String> {
    private String licenceNo_prefix;
    @Override
    public void initialize(LicenceNo licenceNo) {
       licenceNo_prefix=licenceNo.value();
    }

    @Override
    public boolean isValid(String licenceNo, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if(licenceNo!=null){
            result = licenceNo.startsWith(licenceNo_prefix);
        }
        else{
            result=true;
        }

        return result;
    }
}
