package net.gougousis.gvalidator.base;

import java.lang.reflect.Field;
import java.util.Map;

public class BaseForm implements FormBean {
    
    @Override
    public String load(Map<String,String[]> params) {
        
        String globalField = "";
        Class class1;
        try {                       
            
            // Read the fields that are declared for this class
            class1 = this.getClass();
            Field fieldlist[] = class1.getDeclaredFields();            
            
            // Loop through the declared fields
            for(int i=0; i < fieldlist.length; i++){    
                // Get the name of the field
                String fieldName = fieldlist[i].getName();    
                
                // Set this for logging purposes
                globalField = fieldName;
                
                // Get field instance from this class object
                Field field  = class1.getDeclaredField(fieldName);       
                
                // In order to access a field on a subclass we need to modify its scope
                field.setAccessible(true);                                                                                                                
                
                // Assign the posted value to the field
                String fieldType = field.getType().getSimpleName();               
                switch(fieldType){                                                            
                    case "String":
                        if(params.containsKey(fieldName)){                            
                            String value = params.get(fieldName)[0];   
                            if (value != null){
                                field.set(this,params.get(fieldName)[0]);
                            }                            
                        }                                                                                                               
                        break;
                    case "String[]":                        
                        if(params.containsKey(fieldName)){                           
                            String[] value2 = params.get(fieldName);
                            field.set(this,value2);
                        } else {                            
                            String[] value2 = new String[1];
                            field.set(this,value2);                           
                        }                                                                       
                        break;
                    case "int":
                        if(params.containsKey(fieldName)){
                            String value = params.get(fieldName)[0];
                            if((value != null)&&(value.length() > 0)){
                                field.set(this,Integer.parseInt(params.get(fieldName)[0]));
                            }
                        }                                                
                        break;
                }
                
                // Restore the field scope
                field.setAccessible(false);
                
            }
            return "";
        } catch (NoSuchFieldException ex) {
            return "NoSuchFieldException from '"+globalField+"' field: "+ex.getMessage();
        } catch (SecurityException ex) {
            return "SecurityException from '"+globalField+"' field: "+ex.getMessage();
        } catch (IllegalArgumentException ex) {
            return "IllegalArgumentException from '"+globalField+"' field: "+ex.getMessage();
        } catch (IllegalAccessException ex) {
            return "IllegalAccessException from '"+globalField+"' field: "+ex.getMessage();
        } catch(ExceptionInInitializerError ex){
            return "ExceptionInInitializerError from '"+globalField+"' field: "+ex.getMessage();
        } catch(Exception ex){
            return "Exception from '"+globalField+"' field: "+ex.getMessage();
        }
    }
    
    public String getFieldAsString(String fieldName){
        
        String fieldValue = "";
        
        try {
            Class class1;
            
            // Read the fields that are declared for this class
            class1 = this.getClass();
            
            // Get field instance from this class object
            Field field  = class1.getDeclaredField(fieldName);
            field.setAccessible(true);  // If not, an illegalaccessexception will be thrown.
            
            // Get the field value as string
            String fieldType = field.getType().getSimpleName();               
            switch(fieldType){                                                            
                case "String":
                    if(field.get(this) != null){
                        fieldValue = field.get(this).toString();
                    }                   
                    break;
                case "String[]":
                    String[] parts = (String[]) field.get(this);
                    for(String part : parts){
                        if(fieldValue.equals("")){
                            fieldValue = part;
                        } else {
                            fieldValue = fieldValue+","+part;
                        }                        
                    }                    
                    break;
                case "int":
                    if(field.get(this) != null){
                        fieldValue = field.get(this).toString();
                    }                    
                    break;
            }
                        
            // Restore the field scope
            field.setAccessible(false);
            
            return fieldValue;
            
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
