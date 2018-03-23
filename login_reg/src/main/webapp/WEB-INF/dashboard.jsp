<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
</head>
<body>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!"/>
    </form>
    <h1>Welcome <c:out value="${currentUser.first}"></c:out>!</h1>
    <fieldset>
        <p>First Name: ${currentUser.first}</p>
        <p>Last Name: ${currentUser.last}</p>
        <p>Email: ${currentUser.email}</p>
        <p>Sign Up date: <fmt:formatDate pattern = "MM-dd-yyyy" value="${currentUser.createdAt}"></fmt:formatDate></p>
		<p>Last Sign in: <fmt:formatDate pattern = "MM-dd-yyyy" value="${currentUser.updatedAt}"></fmt:formatDate></p>

        
    </fieldset>
</body>
</html>