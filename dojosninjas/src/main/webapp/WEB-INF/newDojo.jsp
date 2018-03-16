<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Dojo</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>New Dojo</h1>
        <form:form action="/dojos/new" method="POST" modelAttribute="newDojo" class="form">
            <form:label path="name">Location Name: 
                    <form:input type="text" path="name"></form:input>
                    <form:errors path="name"></form:errors>
                </form:label><br>
            <input type="submit" value="Create">
        </form:form>
        <br>
        <h3><a href="/">Home</a></h3>
    </div>
</body>
</html>