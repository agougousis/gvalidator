package net.gougousis.gvalidator.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsDateValidator extends BaseValidator implements ConstraintValidator<IsDate,String> {
        
    String dateFormat = "";
    
    @Override
    public void initialize(IsDate constraintAnnotation) {
        dateFormat = constraintAnnotation.dateFormat();
    }

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {   
        
        if(emptyString(value)){
            return true;
        }
        
        if(dateFormat.isEmpty()){
            return true;
        } else {
            return isValidDate(value,dateFormat);
        }

    }        
    
    public boolean isValidDate(String dateString,String dateFormat){
        boolean valid = true;
        Date date;
        
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);
        try {
            dateString = dateString.trim();
            date = format.parse(dateString);            
        } catch (ParseException ex) {
            valid = false;
        }
        
        return valid;
        
    }
    
}
