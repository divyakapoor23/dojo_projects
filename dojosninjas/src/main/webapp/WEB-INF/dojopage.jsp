<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1 class="jumbotron">${dojo.name} Location - Ninjas</h1>
        <a href="/"><button class="button btn-sm btn pull-right">Home</button></a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Age</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${ninjas}" var="ninja">
                <tr>
                    <td>${ninja.first_name}</td>
                    <td>${ninja.last_name}</td>
                    <td>${ninja.age}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <h3 class="text-center">${noone}</h3>
    </div>
</body>
</html>