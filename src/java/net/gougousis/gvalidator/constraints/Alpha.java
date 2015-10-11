package net.gougousis.gvalidator.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {AlphaValidator.class})   // Indicates to the bean validation provider that this is a constraint annotation. It also points to the constraint validation implementation routine
@Target({ElementType.FIELD, ElementType.PARAMETER}) //This constraint annotation can be used only on fields and method parameters.
@Retention(value = RetentionPolicy.RUNTIME)         // the bean validation provider will inspect your objects at runtime
@Documented
public @interface Alpha {

  String message() default "^This field should contain only letters";    // Provide a default error validation message 

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
