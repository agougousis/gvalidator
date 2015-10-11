package net.gougousis.gvalidator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaValidator extends BaseValidator implements ConstraintValidator<Alpha,String> {
        
    @Override
    public void initialize(Alpha constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {   
        
        if(emptyString(value)){
            return true;
        } else {        
            return value.matches("[a-zA-Z]+");
        }
    }        
    
}
