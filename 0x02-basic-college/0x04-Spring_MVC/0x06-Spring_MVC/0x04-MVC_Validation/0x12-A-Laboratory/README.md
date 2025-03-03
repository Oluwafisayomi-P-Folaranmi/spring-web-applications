# Laboratory: Custom Validation 


```Java CourseCode
package com.opf.springboot.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};

}

```

```Java CourseCodeConstraintValidator
package com.opf.springboot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    public String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {

        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {

        boolean result;
        if (theCode != null) {
            result = theCode.startsWith(coursePrefix);
        }
        else {
            result = true;
        }

        return result;
    }
}

```

```Java Customer
package com.opf.springboot;

import com.opf.springboot.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    public String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    public String lastName;

    @NotNull(message = "is required")
    @Min(value=0, message = "must be equal to or greater than 0.")
    @Max(value=10, message = "must be less than or equal to 10.")
    public Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    public String postalCode;

    @CourseCode(value = "UNILAG", message = "must start with UNILAG")
    public String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}

```

```Java CustomerController
package com.opf.springboot;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @InitBinder
    public void initBinder(WebDataBinder theWebDataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        theWebDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel) {

        theModel.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/processCustomerForm")
    public String processCustomerForm(
            @Valid @ModelAttribute("customer") Customer theModelCustomer,
            BindingResult theBindingResult) {

        if(theBindingResult.hasErrors()) {
            return "customer-form";
        }
        else {
            return "customer-confirmation";
        }
    }
 }

```

```Html customer-form
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Form</title>
    <style>
        .error {color: red}
    </style>
</head>
<body>

    <i>Fill out the form. Asterisk (*) means required.</i>
    <br><br>

    <form th:action="@{/processCustomerForm}" th:object="${customer}" method="POST">

        First Name:
        <input type="text" th:field="${customer.firstName}" placeholder="Enter your first name"/>
        <br><br>

        Last Name (*):
        <input type="text" th:field="${customer.lastName}" placeholder="Enter your last name"/>

        <span th:if="${#fields.hasErrors('lastName')}"
              th:errors="${customer.lastName}"
              class="error"></span>
        <br><br>

        Free Passes (*):
        <input type="text" th:field="${customer.freePasses}" placeholder="Free passes"/>

        <span th:if="${#fields.hasErrors('freePasses')}"
              th:errors="${customer.freePasses}"
              class="error"></span>
        <br><br>

        Postal Code (*):
        <input type="text" th:field="${customer.postalCode}" placeholder="Postal Code"/>

        <span th:if="${#fields.hasErrors('postalCode')}"
              th:errors="${customer.postalCode}"
              class="error"></span>
        <br><br>

        Course Code (*):
        <input type="text" th:field="${customer.courseCode}" placeholder="Course Code"/>

        <span th:if="${#fields.hasErrors('courseCode')}"
              th:errors="${customer.courseCode}"
              class="error"></span>
        <br><br>

        <input type="submit" value="Ok"/>

    </form>

</body>
</html>

```

```Html customer-confirmation
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Customer Confirmation</title>
</head>
<body>

    The customer is confirmed: <span th:text="${customer.firstName} + ' ' + ${customer.lastName}"></span>

    <br><br>

    Free passes: <span th:text="${customer.freePasses}"></span>

    <br><br>

    Postal code: <span th:text="${customer.postalCode}"></span>

    <br><br>

    Postal code: <span th:text="${customer.courseCode}"></span>

</body>
</html>

```