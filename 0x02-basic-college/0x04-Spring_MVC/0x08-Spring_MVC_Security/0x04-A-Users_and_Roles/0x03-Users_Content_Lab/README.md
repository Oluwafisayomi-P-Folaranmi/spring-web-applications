# Display Content by User


The only modification we want to make is in the home page using xml to display the links that pertains only to such user. Below is the modified home page.


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

        <div sec:authorize="hasRole('MANAGER')">
        	<!-- Add link to point to /leaders.......... This is for the managers -->
            <p>
                <a th:href="@{/leaders}">Only for Leaders</a>
                (Leadership Meetings)
            </p>
        </div>

        <div sec:authorize="hasRole('ADMIN')">
        	<!-- Add link to point to /systems.......... This is for the administrators -->
            <p>
                <a th:href="@{/systems}">Only for Admin</a>
                (IT Systems Meetings)
            </p>
        </div>

        <!-- Add a logout button -->
        <form action="#" th:action="@{/logout}" method="POST">

            <input type="submit" value="Logout" />
        </form>

    </body>
</html>

```
