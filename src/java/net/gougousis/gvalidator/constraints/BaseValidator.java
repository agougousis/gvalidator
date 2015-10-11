package net.gougousis.gvalidator.constraints;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseValidator {
    
    protected void logText(String message){
        
        PrintWriter out = null;
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            
            out = new PrintWriter(new BufferedWriter(new FileWriter("beanValidator.txt", true)));
            out.println(dateFormat.format(date)+message);
            out.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }                   
    }
    
    protected boolean emptyString(String value){
        if((value == null)||(value.length() == 0)){
            return true;
        } else {
            return false;
        }
    }
    
    
}
