package net.gougousis.gvalidator.base;

import javax.servlet.http.HttpServletRequest;

public interface FormBean {
    
    // Assigns object properties from request parameters
    public String load(HttpServletRequest request);
    
}
