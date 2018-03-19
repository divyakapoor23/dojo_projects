<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>New Product</h1>
        <form:form action="/products/new" method="POST" modelAttribute="newProduct" class="form">
            <form:label path="name">Product Name: 
                <form:input type="text" path="name"></form:input>
                <form:errors path="name"></form:errors>
            </form:label><br>
            <form:label path="description">Description: 
                <form:input type="textarea" path="description"></form:input>
                <form:errors path="description"></form:errors>
            </form:label><br>
            <form:label path="price">Price: 
                <form:input type="text" path="price"></form:input>
                <form:errors path="price"></form:errors>
            </form:label><br>
            <input type="submit" value="Create">
        </form:form>
        <br>
        <h3><a href="/">Home</a></h3>
    </div>
</body>
</html>