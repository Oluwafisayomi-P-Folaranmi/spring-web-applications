# Logout


## The Home Page

We will update the home page to display a logout button.


```html home
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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

        <!-- Add a logout button -->
        <form action="#" th:action="@{/logout}" method="POST">

            <input type="submit" value="Logout" />
        </form>

    </body>
</html>

```

We have added a logout button to the page. This sends data to default logout url: `/logout` which will be added to the configuration. The logout url will be handled by Spring Security. So, what Spring Security does is that when a logout is being processed, by default Spring will invalidate user's HTTP session and remove session cookies, etc, and send user back to your login page. It will then append the parameter `logout` to the url of your login page, which you can check. We can use the `logout` parameter to display the logout message.


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


## The Controller

Spring Security handles the `/logout` url. So nothing is done using the controller.


## The Security Configuration

We will configure the logout in the security configuration file.


```java SecurityConfig
package com.opf.demosecurity.security;
... 

@Configuration
public class DemoSecurityConfig {

	... 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .anyRequest()
                .authenticated()
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
