# Laboratory: 


```Java Customer
package com.opf.springboot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {

    public String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    public String lastName;

    @Min(value=0, message = "must be equal to or greater than 0.")
    @Max(value=10, message = "must be less than or equal to 10.")
    public int freePasses;

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

    public int getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(int freePasses) {
        this.freePasses = freePasses;
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
        <br><br>
        <span th:if="${#fields.hasErrors('lastName')}"
              th:errors="${customer.lastName}"
              class="error"></span>
        <br><br>

        Free Passes (*):
        <input type="text" th:field="${customer.freePasses}" placeholder="Free passes"/>
        <br><br>
        <span th:if="${#fields.hasErrors('freePasses')}"
              th:errors="${customer.freePasses}"
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

</body>
</html>

```
