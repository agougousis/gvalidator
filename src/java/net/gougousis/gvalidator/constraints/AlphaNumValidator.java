package net.gougousis.gvalidator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaNumValidator extends BaseValidator implements ConstraintValidator<AlphaNum,String> {
        
    @Override
    public void initialize(AlphaNum constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {   
        
        if(emptyString(value)){
            return true;
        } else {        
            return value.matches("[A-Za-z0-9]+");
        }
    }        
    
}
