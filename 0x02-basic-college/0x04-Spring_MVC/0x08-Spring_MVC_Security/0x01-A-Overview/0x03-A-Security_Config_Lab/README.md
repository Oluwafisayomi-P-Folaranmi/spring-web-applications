# Security Configuration Laboratory 


## The SecuredController 

```java DemoController
package com.opf.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }
}

```


## The Security Filter Class 

Here, we will create a Spring Security Configuration class, `@Configuration` and then add users, passwords and roles. 


### The Users 

In this illustration we have three users, John, Mary, and Susan that we will test. The passwords that will be used are: 

   + john:
     + Password: test123
     + Roles: EMPLOYEE

   + mary:
     + Password: test123
     + Roles: EMPLOYEE, MANAGER 

   + susan: 
     + Password: test123
     + Roles: EMPLOYEE, MANAGER, ADMIN

We can give any names to the roles. 

In Spring Security passwords are stored using specific format, like: {id}encoded_password. You give the actual id inside curly braces followed by the password. Below are the examples of id used in Spring Security: 

   + noop: means No-Operation. No enscryption, no hashing, just plain texts passwords. 
   + bcrypt: Bcrypt hashing. 

An example password in this format is: {noop}test123, {bcrypt}test123. 


### The Security Filter Class 

```java DemoSecurityConfig
package com.opf.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    
}

```

We will add db support for users, passwords and roles later. 


## The Thymeleaf View Page 

We will actually define the thymeleaf view page. 

```html templates/home
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>luv2code Company Home Page</title>
    </head>

    <body>

        <h2>luv2code Company Home Page - Yoohoo - Silly Goose - NO SOUP!!!</h2>
        <hr>
        Welcome to the luv2code company home page!

    </body>
</html>

```
