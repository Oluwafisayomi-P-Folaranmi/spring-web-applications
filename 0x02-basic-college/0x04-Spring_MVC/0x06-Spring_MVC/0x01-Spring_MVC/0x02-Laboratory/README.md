# Thymeleaf and CSS 


## Step 1: The Browser

There is an MVC endpoint that is exposed in the controller, `/hello`.


## Step 2: The Controller

The `@Controller` annotation is a specialization of the `@Component` annotation, indicating that the annotated class is a Spring MVC controller. It is typically used in conjunction with `@RequestMapping` to handle web requests. Controllers annotated with `@Controller` are primarily used to return views (HTML pages) in a web application.

The `@RestController` annotation is a combination of `@Controller` and `@ResponseBody`. It is used to create RESTful web services and automatically serializes the returned objects into JSON or XML. Unlike `@Controller`, `@RestController` does not return views. Instead, it returns data directly to the client.


```Java DemoController.java
package com.opf.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // create a mapping for "/hello"

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", java.time.LocalDateTime.now());

        return "helloworld";
    }
}

```


## Step 3: The View


```Html helloworld.html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Thymeleaf Demo</title>
</head>

<body>

<p th:text="'Time on the server is ' + ${theDate}" class="funny" />

</body>

</html>

```

```Css demo.css
.funny {
	font-style: italic;
	color: green;
	
}

```
