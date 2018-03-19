<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products and Categories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h3 class="text-center"><a href="/categories/new">New Category</a></h3>        
        <h3 class="text-center"><a href="/products/new">New Product</a></h3>
        <div class="row">
            <div class="col-md-6 text-center">
                <h3>All Products</h3>
                <c:forEach items="${products}" var="prod">
                    <h4><a href="/products/${prod.id}">${prod.name}</a></h4>
                </c:forEach>
            </div>
            <div class="col-md-6 text-center">
                <h3>All Categories</h3>
                <c:forEach items="${categories}" var="categ">
                    <h4><a href="/categories/${categ.id}">${categ.name}</a></h4>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>