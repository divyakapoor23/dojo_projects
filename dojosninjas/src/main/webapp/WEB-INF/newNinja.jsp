<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Ninja</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>New Ninja</h1>
        <form:form action="/ninjas/new" method="POST" modelAttribute="newNinja" class="form">
            <form:label path="dojo">Dojo: 
                <form:select type="text" path="dojo">
                    <c:forEach items="${locations}" var="codingdojo">
                        <form:option value="${codingdojo.id}">${codingdojo.name} Location</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="dojo"></form:errors>
            </form:label><br>
            <form:label path="first_name">First Name:
                <form:input type="text" path="first_name"></form:input>
                <form:errors path="first_name"></form:errors>
            </form:label><br>
            <form:label path="last_name">Last Name:
                <form:input type="text" path="last_name"></form:input>
                <form:errors path="last_name"></form:errors>
            </form:label><br>
            <form:label path="age">Age:
                <form:input type="text" path="age"></form:input>
                <form:errors path="age"></form:errors>
            </form:label><br>
            <input type="submit" value="Create">
        </form:form>
        <br>    
        <h3><a href="/">Home</a></h3>
    </div>
</body>
</html>