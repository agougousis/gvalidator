package net.gougousis.gvalidator.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaPlusValidator extends BaseValidator implements ConstraintValidator<AlphaPlus,String> {
        
    @Override
    public void initialize(AlphaPlus constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {   
        
        if(emptyString(value)){
            return true;
        } else {        
            return value.matches("[ A-Za-z0-9_-]+");
        }
    }        
    
}
