<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login/Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <c:if test="${logout != null}">
        <p><c:out value="${logout}"></c:out></p>
    </c:if>
    <h1>Login</h1>
    <c:if test="${logError != null}">
        <p>${logError}</p>
    </c:if>
    <form method="POST" action="/login">
        <p>Email: <input type="text" id="email" name="username"/></p>
        <p>Password: <input type="password" id="password" name="password"/></p>
        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login!"/>
    </form>
    <br>
    <hr>

    <h1>Register!</h1>
    <p><form:errors path="user.*"/></p>
    <form:form method="POST" action="/register" modelAttribute="user">
        <p><form:label path="email">Email: </form:label><form:input path="email"/></p>
        <p><form:label path="first">First Name: </form:label><form:input path="first"/></p>
        <p><form:label path="last">Last Name: </form:label><form:input path="last"/></p>
        <p><form:label path="password">Password: </form:label><form:password path="password"/></p>
        <p><form:label path="confirm">Confirm Password: </form:label><form:password path="confirm"/></p>
        <input type="submit" value="Register!"/>
    </form:form>
</body>
</html>