<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All Languages</title>
</head>
<body>
    <table>
        <tr>
            <th>Name</th>
            <th>Creator</th>
            <th>Version</th>
            <th>(actions)</th>
        </tr>
        <tbody>
            <c:forEach items="${langs}" var="lang">
                <tr>
                    <td><a href="/languages/${langs.indexOf(lang)}">${lang.name}</a></td>
                    <td>${lang.creator}</td>
                    <td>${lang.currentVersion}</td>
                    <td><a href="/delete/${langs.indexOf(lang)}">delete</a> <a href="/edit/${langs.indexOf(lang)}">edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <form:form action="/create" method="POST" modelAttribute="Language">
        <form:label path="name">Name 
            <form:errors path="name"></form:errors>
            <form:input type="text" path="name"></form:input>
        </form:label><br>
        <form:label path="creator">Creator
            <form:errors path="creator"></form:errors>
            <form:input type="text" path="creator"></form:input>
        </form:label><br>
        <form:label path="currentVersion">Version
            <form:errors path="currentVersion"></form:errors>
            <form:input type="text" path="currentVersion"></form:input>
        </form:label><br>
        <input type="submit" value="Create Language">
    </form:form>
</body>
</html>