<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${lang.name}</title>
</head>
<body>
    <h3><a href="/delete/${langs.indexOf(lang)}">Delete</a></h3>
    <h3><a href="/">Dashboard</a></h3>
    <form:form action="/edit/${langs.indexOf(lang)}" method="POST" modelAttribute="lang">
        <form:label path="name">Name
            <form:errors path="name"></form:errors>
            <form:input type="text" path="name" value="${lang.name}"></form:input></form:label>
        <form:label path="creator">Creator
            <form:errors path="creator"></form:errors>
            <form:input type="text" path="creator" value="${lang.creator}"></form:input></form:label>
        <form:label path="currentVersion">Version
            <form:errors path="currentVersion"></form:errors>
            <form:input type="text" path="currentVersion" value="${lang.currentVersion}"></form:input></form:label>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>