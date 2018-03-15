<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/css/ninjagold.css">
</head>
<body>
    <div class="container"> 
        <div class="top">
            <h2>Ninja Gold: <span class="golds ${ topcolor }">${ golds }</span></h2>
            
        </div>
        <div class="activities">
            <form action="/money" method="post">
                <h4>Farm</h4>
                <p>(earns 10-20 golds)</p>
                <input type="hidden" name="building" value="farm"/>
                <input type="submit" value="Find Gold!"/>
            </form>
            <form action="/money" method="post">
                <h4>Cave</h4>
                <p>(earns 5-10 golds)</p>
                <input type="hidden" name="building" value="cave"/>
                <input type="submit" value="Find Gold!"/>
            </form>
            <form action="/money" method="post">
                <h4>House</h4>
                <p>(earns 2-5 golds)</p>
                <input type="hidden" name="building" value="house"/>
                <input type="submit" value="Find Gold!"/>
            </form>
            <form action="/money" method="post">
                <h4>Casino</h4>
                <p>(earns/takes 0-50 golds)</p>
                <input type="hidden" name="building" value="casino"/>
                <input type="submit" value="Find Gold!"/>
            </form>
            <form action="/money" method="post">
                <h4>Spa</h4>
                <p>(takes 5-20 golds)</p>
                <input type="hidden" name="building" value="spa"/>
                <input type="submit" value="Treat yo'self!!"/>
            </form>
        </div>
        <div class="bottom">
            <h4>Activites: </h4>
            <div class="log">
                <c:forEach items="${ choices }" var="choice">
                    <c:if test="${choice}.charAt(0)=='E'">
                        <p class="green">${ choice }</p>
                    </c:if>
                    <c:if test="${choice}.charAt(0)=='L'">
                        <p class="red">${ choice }</p>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <a href="/reset"><button>Reset Ninja Gold</button></a>
    </div> 
</body>
</html>