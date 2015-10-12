# gvalidator
An input validation library for J2EE projects¨

This library provides a ```FormValidator``` class that helps developers validate user input in server-side (servlet) using bean validation, extending at the same time the constraints available by Hibernate Validator. The assignment of data sent to a servlet through a GET or POST request are automatically assigned.

For example, the developer that wants to validate a registration form defines e.g a RegistrationForm class annotated with validation constraints, like the following:

```java
@SameAs(testFieldName="repeatPassword",dependOnFieldName="password")
public class RegistrationForm extends BaseForm implements FormBean {
    
    // Properties (with validation annotation)

    @NotBlank
    @Size(min=2,max=30)
    @Alpha
    private String firstname;   
    
    @NotBlank
    @Size(min=2,max=30)
    @Alpha
    private String lastname;
    
    @NotBlank
    @Size(min=6)
    @AlphaNum
    private String username;
    
    @NotBlank
    @Size(min=8,max=30)
    private String password;
    
    @NotBlank
    private String repeatPassword;    
        
    @IsDate(dateFormat="yyyy-MM-dd")
    private String birthdate;

    // Getters
    public String   getFirstname()      {   return firstname;   }
    public String   getLastname()       {   return lastname;    }
    public String   getUsername()       {   return username;    }
    public String   getPassword()       {   return password;    }
    public String   getRepeatPassword() {   return repeatPassword;    }
    public String   getBirthdate()      {   return birthdate;    }
    
    // Setters
    public void setFirstname(String firstname)  {  this.firstname = firstname;  }    
    public void setLastname(String lastname)    {  this.lastname = lastname;    }  
    public void setUsername(String username)    {  this.username = username;    } 
    public void setPassword(String password)    {  this.password = password;    } 
    public void setRepeatPassword(String repeatPassword)    {  this.repeatPassword = repeatPassword;    } 
    public void setBirthdate(String birthdate)    {  this.birthdate = birthdate;    } 

}
```
After that, the validation that takes place in the servlet looks really simple:

```java
  FormValidator validator = new FormValidator("TestForm");  
  validator.load(request);
  if(validator.fails()){

	} else {
		
	}    
```

The ```load()``` method loads the posted (in case of a POST request) data from the HttpServletRequest object.
The method ```fails()``` runs the validation process returning a boolean about the success of the process. 

The validator object has two more helpful methods. 

The ```getErrors()``` method that returns a Map<String,String> object, where the key represents the name of the form field that caused the  validation error and the value represents the message about what went wrong. 
The ```getBeanForm()``` returns the bean that holds the values of the form:

```java
	RegistrationForm form = (RegistrationForm) validator.getBeanForm();
```
