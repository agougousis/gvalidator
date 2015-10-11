package net.gougousis.gvalidator.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class SameAsValidator extends BaseValidator implements ConstraintValidator<SameAs,Object> {
    
    private String testFieldName;
    private String dependOFieldName;
    
    @Override
    public void initialize(SameAs constraintAnnotation) {
        
        testFieldName = constraintAnnotation.testFieldName();
        dependOFieldName = constraintAnnotation.dependOnFieldName();
        
    }

    @Override
    public boolean isValid(Object object,ConstraintValidatorContext context) {   
        
        try {
            // Using reflection to retrieve the field values from the bean
            String testFieldValue = BeanUtils.getProperty(object, testFieldName);
            String dependOnFieldValue = BeanUtils.getProperty(object, dependOFieldName);
            
            // Checking the necessery conditions
            if((emptyString(dependOnFieldValue))&&(emptyString(testFieldValue))){
                return true;
            } else {
                if(emptyString(dependOnFieldValue)){
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Field '"+dependOFieldName+"' should not be empty.")
                        .addConstraintViolation();                  
                    return false;
                } else {
                    if(dependOnFieldValue.equals(testFieldValue)){
                        return true;
                    } else {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("Field '"+dependOFieldName+"' should have same value as field '"+testFieldName+"'.")
                            .addConstraintViolation();                  
                        return false;
                    }
                }
            }

        } catch (final NoSuchMethodException ex) {
            logText("NoSuchMethodException - "+ex.getMessage());
            return false;

        } catch (final InvocationTargetException ex) {
            logText("InvocationTargetException - "+ex.getMessage());
            return false;

        } catch (final IllegalAccessException ex) {
            logText("IllegalAccessException - "+ex.getMessage());
            return false;
        } catch (Exception ex){
            logText("Exception - "+ex.getMessage());
            return false;
        }

    }        
    
}
