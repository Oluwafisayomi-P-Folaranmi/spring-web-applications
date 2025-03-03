# Login Error Lab 


The url `/authenticateTheUser` which is by default sent to Spring Security has some inheremt parameters: `continue`, and `error`. When a non-user trys the form to login, it fails. Consequently, Spring will send the user back to the Login Form, and append `error` as parameter to the url. This will be on the url as: `http://localhost:8080/showMyLoginPage?error`. We can then write the code to check for the parameter, whether `error` or `continue`. 

The modification for error login is done on the Login Form. 


## The Thymeleaf View Pages 

Below is the defined thymeleaf view for the Loging Form page. 


```html templates/plain-login
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <link
                rel="stylesheet"
                href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        />
        <style>
            .failed {
                color: red;
            }
        </style>        
        <title>Custom Login Page</title>
    </head>
    <body>

        <h3>My Custom Login Page</h3>

        <form action="#" th:action="@{/authenticateTheUser}" method="POST">

			<!-- Check for login error -->
            <div th:if="${param.error}">

                <i class="failed">Sorry! You entered invalid username/password.</i>

            </div>

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

In Thymeleaf, the `${param}` expression is used to access HTTP request parameters within a Thymeleaf template. It allows you to retrieve values from the query string or form data submitted with an HTTP request. This is useful when you want to perform actions or display content based on the parameters passed in the request.

Here, we have added Css styling to the error message, with a bit of bootstraps.
