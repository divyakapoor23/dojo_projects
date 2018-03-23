<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login/Register</title>
</head>
<body>
    <c:if test="${logout != null}">
        <p><c:out value="${logout}"></c:out></p>
    </c:if>
    <p><form:errors path="user.*"/></p>
    <c:if test="${error != null}">
       <p>${error}</p>
    </c:if>
    <h1>Login</h1>
    <form method="POST" action="/login">
        <p>Email: <input type="text" id="email" name="username"/></p>
        <p>Password: <input type="password" id="password" name="password"/></p>
        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login!"/>
    </form>
    <br>
    <hr>

    <h1>Register!</h1>
    <c:if test="${emailMessage != null}">
        <c:out value="${emailMessage}"></c:out>
    </c:if>

    <form:form method="POST" action="/register" modelAttribute="user">
        <p><form:label path="email">Email: </form:label><form:input path="email"/></p>
        <p><form:label path="first">First Name: </form:label><form:input path="first"/></p>
        <p><form:label path="last">Last Name: </form:label><form:input path="last"/></p>
        <p><form:label path="password">Password: </form:label><form:password path="password"/></p>
        <p><form:label path="confirm">Confirm Password: </form:label><form:password path="confirm"/></p>
        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Register!"/>
    </form:form>
</body>
</html>