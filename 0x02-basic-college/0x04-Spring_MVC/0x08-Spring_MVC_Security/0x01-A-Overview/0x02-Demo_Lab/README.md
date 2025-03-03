# Laboratory: 


## Setting Up 

The dependencies that will be used are: 

   + `spring-boot-starter-security` 
   + `spring-boot-starter-thymeleaf` 
   + `spring-boot-starter-web`, and  
   + `thymeleaf-extras-springsecurity6`


## The SecuredController 

```java DemoController
package com.opf.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showForm() {

        return "home";
    }
}

```


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
