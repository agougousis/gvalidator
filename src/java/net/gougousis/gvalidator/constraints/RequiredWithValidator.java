package net.gougousis.gvalidator.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class RequiredWithValidator extends BaseValidator implements ConstraintValidator<RequiredWith,Object> {
    
    private String testFieldName;
    private String dependOFieldName;
    
    @Override
    public void initialize(RequiredWith constraintAnnotation) {
        
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
            if((!emptyString(dependOnFieldValue))&&(emptyString(testFieldValue))){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Field '"+testFieldName+"' is required when field '"+dependOFieldName+"' is not empty.")
                    .addConstraintViolation();                  
                return false;
            } else {
                return true;
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
