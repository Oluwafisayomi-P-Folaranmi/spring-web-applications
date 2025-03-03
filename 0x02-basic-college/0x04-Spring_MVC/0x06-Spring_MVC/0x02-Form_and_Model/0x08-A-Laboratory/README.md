# Laboratory: `GetMapping` and `PostMapping` 


```Java HelloWorldController.java
package com.opf.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model model) {

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey My Friend from v3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
}

```

```Html helloworld-form.html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

	<head>
	    <meta charset="UTF-8">
	    <title>Hello World - Input Form</title>
	</head>
	<body>

	<form th:action="@{/processFormVersionThree}" method="POST">

	    <input type="text" name="studentName"
	           placeholder="What's your name?" />
	    <input type="submit" />

	</form>

	</body>

</html>

```

```Html helloworld.html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

	<head>
	    <title>Thymeleaf Demo</title>
	</head>

	<body>

	Hello World of Spring!

	<br><br>

	Student name: <span th:text="${param.studentName}" />

	<br><br>

	The message: <span th:text="${message}" />

	</body>

</html>

```

```Css demo.css
.funny {
	font-style: italic;
	color: green;
	
}

```
