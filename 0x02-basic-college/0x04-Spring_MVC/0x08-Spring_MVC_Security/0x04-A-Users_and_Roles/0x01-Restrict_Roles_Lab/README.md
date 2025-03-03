# Restrict Roles Laboratory


## Login to the Home Page

Following is the login page which will take a user to the home page. The home page can be viewed by all users.


```html
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
    <head>
        <title>Login Page</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Bootstrap demo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    </head>

    <body>
        <div class="container">
            <div id="loginbox" style="margin-top: 50px;" class="col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
                <div class="card border-info">
                    <div class="card-header bg-info">
                        Sign In
                    </div>

                    <div class="card-body">
                        <div class="card-text">

                            <!-- Login Form -->
                            <form action="" th:action="@{/authenticateTheUser}" method="POST" class="form-horizontal">

                                <!-- Place for messages: error, alert etc ... -->
                                <div class="form-group">
                                    <div class="col-xs-15">
                                        <div>
                                            <!-- Check for login error -->

                                            <div th:if="${param.error}">

                                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                                    Invalid username and password.
                                                </div>

                                            </div>
                                            <!-- Check for logout -->

                                            <div th:if="${param.logout}">

                                                <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                                    You have been logged out.
                                                </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <!-- User name -->
                                <div style="margin-bottom: 25px;" class="input-group">
                                    <input type="text" name="username" placeholder="username" class="form-control" />
                                </div>

                                <!-- Password -->
                                <div style="margin-bottom: 25px;" class="input-group">
                                    <input type="password" name="password" placeholder="password" class="form-control" />
                                </div>

                                <!-- Login/Submit Button -->
                                <div style="margin-top: 10px;" class="form-group">
                                    <div class="col-sm-6 controls">
                                        <button type="submit" class="btn btn-success">Login</button>
                                    </div>
                                </div>

                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

```

As seen below is the home page that is displayed immediately after the user is logged in. There are two links that point to some role-based protected pages: `/leaders` and `/systems`.


```html home
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
                xmlns:sec="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8">
        <title>luv2code Company Home Page</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>

        <h2>luv2code Company Home Page</h2>
        <hr>

        <p>Welcome to the luv2code company home page!</p>

        <!-- Display user and roles -->
        <p>
            User: <span sec:authentication="principal.username" />
            <br><br>
            Role(s): <span sec:authentication="principal.authorities" />
        </p>

        <!-- Add link to point to /leaders.......... This is for the managers -->
        <p>
            <a th:href="@{/leaders}">Only for Leaders</a>
            (Leadership Meetings)
        </p>

        <!-- Add link to point to /systems.......... This is for the administrators -->
        <p>
            <a th:href="@{/systems}">Only for Admin</a>
            (IT Systems Meetings)
        </p>

        <!-- Add a logout button -->
        <form action="#" th:action="@{/logout}" method="POST">

            <input type="submit" value="Logout" />
        </form>

    </body>
</html>

```

We have added the links to the paths `/leaders` and `/systems`.


## The Controller

Here we want to create the controller code for the path `/leaders` and `/systems`.


```java
package com.opf.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuredController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }

    // add a request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeadersPage() {

        return "leaders";
    }

    // add a request mapping for /systems
    @GetMapping("/systems")
    public String showSystemsPage() {

        return "systems";
    }
}

```


## The Security Configuration

We will configure the security configuration file to restrict access to roles. To restrict access to roles, here is a general syntax:


```java
requestMatchers(<< add path to match on >>).hasRole(<< authorised roles >>);

```

So for example, we can restrict access to a given path, `/systems/**`. And we want to give a user with a given role to access this path, say `ADMIN`. In a case where we have multiple roles that can access a path, we use:


```java
requestMatchers(<< add path to match on >>).hasAnyRole(<< authorised roles >>);

```

Then we can give a comma-delimited list of roles.


```java SecurityConfig
package com.opf.demosecurity.security;
... 

@Configuration
public class DemoSecurityConfig {

	... 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/leaders/**").hasRole("MANAGER")
                .requestMatchers("/systems/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
        ).logout(logout -> logout.permitAll()
        );

        return http.build();
    }

}

```
