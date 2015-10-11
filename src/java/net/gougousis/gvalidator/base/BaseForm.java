package net.gougousis.gvalidator.base;

import java.lang.reflect.Field;
import javax.servlet.http.HttpServletRequest;

public class BaseForm implements FormBean {
    
    @Override
    public String load(HttpServletRequest request) {
        Class class1;
        try {
            // Read the fields that are declared for this class
            class1 = this.getClass();
            Field fieldlist[] = class1.getDeclaredFields();
            
            // Loop through the declared fields
            for(int i=0; i < fieldlist.length; i++){                
                String fieldName = fieldlist[i].getName();
                // Get field instance from this class object
                Field field  = class1.getDeclaredField(fieldName);
                field.setAccessible(true);                
                
                // Assign the posted value to the field
                String fieldType = field.getType().getSimpleName();
                switch(fieldType){
                    case "String":
                        String value1 = request.getParameter(fieldName);
                        field.set(this,value1);
                        break;
                    case "String[]":
                        String[] value2 = request.getParameterValues(fieldName);
                        field.set(this,value2);
                        break;
                    case "int":
                        int value3 = Integer.parseInt(request.getParameter(fieldName));
                        field.set(this,value3);
                        break;
                }
            }
            return "";
        } catch (NoSuchFieldException ex) {
            return "NoSuchFieldException";
        } catch (SecurityException ex) {
            return "SecurityException";
        } catch (IllegalArgumentException ex) {
            return "IllegalArgumentException";
        } catch (IllegalAccessException ex) {
            return "IllegalAccessException";
        }        
    }
    
}
