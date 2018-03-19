<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Category Profile Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1 class="jumbotron">${category.name}</h1>
        <a href="/"><button class="button btn-sm btn">Home</button></a>
        <br>
        <div class="row">
            <div class="col-md-8">
                <h3>Categories:</h3>
                <c:forEach items="${category.products}" var="categ">
                        <h4>${categ.name}</h4>
                </c:forEach>
                <h4>${none}</h4>
            </div>
            <div class="col-md-4">
                <h3>Add Product:</h3>            
                <form action="/categories/${category.id}/join" method="POST" class="form-inline">
                    <div class="form-group"> 
                        <select type="text" name="product_id">
                            <c:forEach items="${availProds}" var="available">
                                <option value="${available.id}">${available.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="Add" class="button btn-xs btn">
                </form>
            </div>
        </div>
    </div>
</body>
</html>