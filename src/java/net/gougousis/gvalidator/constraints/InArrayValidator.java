package net.gougousis.gvalidator.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InArrayValidator extends BaseValidator implements ConstraintValidator<InArray,String> {
        
    String[] valueList;
    
    @Override
    public void initialize(InArray constraintAnnotation) {
        valueList = constraintAnnotation.valueList();
        
    }

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {   
                
        if(value == null){
            return true;
        } else {
            List values = Arrays.asList(valueList);
            if(values.contains(value)){
                return true;
            } else {
                return false;
            }
        }       
        
    }        
    
    
}
