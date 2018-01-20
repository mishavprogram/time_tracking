<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="m" uri="/WEB-INF/Paginator.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>User page</title>

    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
    <script src="/bootstrap/jquery-3.2.1.min.js"></script>
    <script src="/bootstrap/bootstrap.js"></script>
    
    <link rel="stylesheet" type="text/css" media="screen" href="/css/user.css" />
</head>
<body>
    <div class="container">
        <header>
            <h1 class="greeting">Hello, <c:out value="${sessionScope.get(\"userName\")} ${sessionScope.get(\"userSurname\")}!"/></h1>
            <nav class="navigation">
                <form method="get" action="/userPage">
                    <input type="submit" value="home">
                </form>
                <form method="get" action="/logout">
                    <input type="submit" value="log out">
                </form>
            </nav>
            <select class="language">
                <option>Українська</option>
                <option>Російська</option>
                <option selected>English</option>
            </select>
        </header>

        <section class="mainSection">
            <div class="tableBlock">
                <table class="table">
                    <tr>
                        <td>name</td>
                        <td>startDate</td>
                        <td>endDate</td>
                        <td>hours</td><!--Треба якось брати к-сть годин-->
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach items="${activities}" var="activity">
                        <tr>
                            <td>${activity.name}</td>
                            <td>${activity.startDate.toString()}</td>
                            <td>${activity.endDate.toString()}</td>
                            <td></td>
                            <td><form method="get"><button class="btn-primary">set time</button></form></td>
                            <td><form method="get"><button class="btn-primary">delete activity</button></form></td>
                        </tr>
                    </c:forEach>
                </table>
                <m:display paginParamName="page" totalPages="${pages}"/>
            </div>
            <div class="rightBlock">
                <div class="calendar">
                    <h3>Choose date:</h3>
                    <form method="get" action="/userPage">
                        <input type="date" name = "date">
                        <input type="submit" value="chooseDay">
                    </form>
                </div>
                <div class="buttons">
                    <form method="get" action="/addingActivity">
                    <input type="submit" value="Add activity">
                    </form>
                    <input type="submit" value="All activities">
                </div>
            </div>
        </section>
    </div>
</body>
</html>