# Laboratory: Required Integer Field 


```Java Customer
package com.opf.springboot;

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
}

```


## Handle String Input for Integer Fields 

```messages.properties
typeMismatch.customer.freePasses = Invalid number

```


## Debugging Tips 


