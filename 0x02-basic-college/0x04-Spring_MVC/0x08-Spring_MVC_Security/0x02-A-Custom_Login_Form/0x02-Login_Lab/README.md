# Custom Login Form Laboratory 


## The SecuredController 

Below is the controller that controls the pages. 


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

Below is the controller that will be used for controlling the login page. 


```java LoginController
package com.opf.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {


        return "plain-login";
    }
}

```


## The Thymeleaf View Pages 

Below is the defined thymeleaf view for the home page. 

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

Next, we define the thymeleaf view for the the login page. 


```html templates/plain-login
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <link
                rel="stylesheet"
                href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        />
        <title>Custom Login Page</title>
    </head>
    <body>

        <h3>My Custom Login Page</h3>

        <form action="#" th:action="@{/authenticateTheUser}" method="POST">

            <p>
                User name: <input type="text" name="username" class="form-control mb-4 w-25"/>
            </p>

            <p>
                Password: <input type="password" name="password" class="form-control mb-4 w-25"/>
            </p>

            <input type="submit" value="Login" class="btn btn-info col-2"/>

        </form>

    </body>
</html>

```


## The Security Filter Class 

Here, we will create the Spring Security Configuration class, `@Configuration`. Here we are going to configure security of web paths in application, login, logout, etc. 


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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .anyRequest()
                .authenticated()
        ).formLogin(form -> form
                        .loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .permitAll()
        );

        return null;
    }
    
}

```

The method `filterChain` references your custom login form. 

As evident, a security configuration class is that class where the adjustment to the Spring Security configuration can be specified explicitly. We specified what we need the users, roles and passwords to be instead of the traditional one provided by Spring container. Also, we specified the custom login form of our choice by overriding the login form provided by the Spring container.
