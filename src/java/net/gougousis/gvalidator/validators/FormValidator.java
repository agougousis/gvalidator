package net.gougousis.gvalidator.validators;

import net.gougousis.gvalidator.base.FormBean;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class FormValidator {
    
    private String beanFormClass;
    private Object beanForm;
    Map<String,String> errors = new HashMap<String,String>();
    private ArrayList<String> errorLog = new ArrayList<String>();
    
    public FormValidator(String beanFormClass){
        this.beanFormClass = "gr.gougousis.beans.forms."+beanFormClass;
    }
    
    public void load(HttpServletRequest request) {

        try {
            // Create a bean instance for the form
            this.beanForm = Class.forName(this.beanFormClass).getConstructor().newInstance();
            // Load form data from request to bean
            String errorMessage = ((FormBean) this.beanForm).load(request);
            if(errorMessage.length() > 0 ){
                errorLog.add(errorMessage);
            }
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (SecurityException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getMessage());
        }
       
    }
    
    public void validate(){
        // Get a Validator instance
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();        
        int counter = 0;
        // Validate all constraintsof beanForm (using the validate() method) and read the validation messages
        for (ConstraintViolation<Object> error : validator.validate( this.beanForm ) ) {
                counter++;              
                errors.put(error.getPropertyPath().toString(), error.getMessage());                       
        }     
    }
    
    public boolean fails() {
        validate();        
        if(this.errors.size() > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public Map<String,String> getErrors(){
        return this.errors;
    }
    
    public ArrayList<String> getErrorLog(){
        return this.errorLog;
    }
    
    public Object getBeanForm(){
        return this.beanForm;
    }    
    
}
