# Laboratory: 


```Java Customer
package com.opf.springboot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {

    public String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    public String lastName;

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
}

```

```Java CustomerController
package com.opf.springboot;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

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
    <title>Title</title>
    <style>
        .error {color: red;}
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
        <br><br>

        <span th:if="${#fields.hasErrors('lastName')}"
              th:errors="${customer.lastName}"
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

</body>
</html>

```

