package net.gougousis.gvalidator.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {SameAsValidator.class})
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SameAs {

    String testFieldName();
    String dependOnFieldName();
    
    String message() default "fdsgsdds";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
