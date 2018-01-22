<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="m" uri="/WEB-INF/taglib/Paginator.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8" />
    <title>Admin page</title>
    
	<link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
	<script src="/bootstrap/jquery-3.2.1.min.js"></script>
    <script src="/bootstrap/bootstrap.js"></script>
    <script src="/js/selectRow.js"></script>
    
    <link rel="stylesheet" type="text/css" media="screen" href="/css/user.css" />
</head>
<body>
    <div class="container">
        <header>
            <h1 class="greeting"><fmt:message key="hello" bundle="${msg}"/>, <c:out value="${sessionScope.get(\"userName\")} ${sessionScope.get(\"userSurname\")}!"/></h1>
            <nav class="navigation">
                <form method="get" action="/adminPage">
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
                        <td>email</td>
                        <td>userName</td>
                        <td>userSurname</td>
                        <td>activityName</td>
                        <td>action</td>
                        <td></td><!--button to set-->
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.userEmail}</td>
                            <td>${order.userName}</td>
                            <td>${order.userSurname}</td>
                            <td>${order.activityName}</td>
                            <td>${order.action}</td>
                            <td><a href="/adminPage/${order.id}"><button class="btn-primary">confirm or reject</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <m:display paginParamName="page" totalPages="${pages}"/>
            </div>
        </section>
    </div>
</body>
</html>