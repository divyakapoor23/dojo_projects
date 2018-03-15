<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${lang.name}</title>
</head>
<body>
    <h3><a href="/">Dashboard</a></h3>
    <h3>${lang.name}</h3>
    <h3>${lang.creator}</h3>
    <h3>${lang.currentVersion}</h3>
    <h3><a href="/edit/${langs.indexOf(lang)}">Edit</a></h3>
    <h3><a href="/delete/${langs.indexOf(lang)}">Delete</a></h3>
</body>
</html>