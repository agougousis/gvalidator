package net.gougousis.gvalidator.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class RequiredIfValidator extends BaseValidator implements ConstraintValidator<RequiredIf,Object> {
    
    private String fieldName;
    private String expectedFieldValue;
    private String dependFieldName;
    
    @Override
    public void initialize(RequiredIf constraintAnnotation) {
        
        fieldName          = constraintAnnotation.fieldName();
        expectedFieldValue = constraintAnnotation.fieldValue();
        dependFieldName    = constraintAnnotation.dependFieldName();
        
    }

    @Override
    public boolean isValid(Object object,ConstraintValidatorContext context) {   
        
        try {
            // Using reflection to retrieve the field values from the bean
            String fieldValue       = BeanUtils.getProperty(object, fieldName);
            String dependFieldValue = BeanUtils.getProperty(object, dependFieldName);
            
            // Checking the necessery conditions
            if (expectedFieldValue.equals(fieldValue) && ((dependFieldValue == null)||(dependFieldValue.length() == 0))) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Custom validation message")
                    .addNode(dependFieldName)
                    .addConstraintViolation();
                    return false;
            } else {
                return true;
            }

        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(ex.getMessage());

        } catch (final InvocationTargetException ex) {
            throw new RuntimeException(ex.getMessage());

        } catch (final IllegalAccessException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }    
    
}
