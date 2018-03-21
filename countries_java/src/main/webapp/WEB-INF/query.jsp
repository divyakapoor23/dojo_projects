<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Countries - JPA Version</title>
</head>
<body>
    <div style="padding: 5px; text-align: center;">
        <a style="padding: 5px;" href="/slovene">Slovene</a>
        <a style="padding: 5px;" href="/total">Total Cities</a>
        <a style="padding: 5px;" href="/mexico">Mexico</a>
        <a style="padding: 5px;" href="/mainlangs">Main Languages</a>
        <a style="padding: 5px;" href="/surfpop">Surface Area & Population</a>
        <a style="padding: 5px;" href="/monarch">Some Constitutional Monarchies</a>
        <a style="padding: 5px;" href="/argentina">Argentina</a>
        <a style="padding: 5px;" href="/regions">Regions</a>
    </div>
    <h1>${query}</h1>
    <h4>${desc}</h4>
    <br>
    <table>
        <thead><!-- how to get the column names to show????  -->
            <tr style="padding: 5px; text-align: left;">
                <c:forEach items="${columns}" var="name">
                    <th style="padding: 5px; text-align: left; border: 1px solid black;">${name}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${table}" var="row">
                <tr style="padding: 5px; text-align: left;">
                <c:forEach items="${row}" var="data">
                    <td style="padding: 5px; text-align: left; border: 1px solid black;">${data}</td>
				</c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>