package net.gougousis.gvalidator.base;

import java.util.Map;

public interface FormBean {
    
    // Assigns object properties from request parameters
    public String load(Map<String,String[]> params);
    
}
