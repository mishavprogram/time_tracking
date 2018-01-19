<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Add activity</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
    
	<link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
	<script src="/bootstrap/bootstrap.js"></script>
</head>
<body>
    <div class="container">
        <form method="post" action="/addingActivity">
            <h1 class="auth">Add activity</h1>
            <div class="field">
                <label for="actName">Name: </label>
                <input id="actName" name="actName" type="text" required>
            </div>
            <div class="field">
                <label for="startDate">Start date: </label>
                <input id="startDate" name="startDate" type="date" required>
            </div>
            <div class="field">
                <label for="endDate">End Date: </label>
                <input id="endDate" name="endDate" type="date" required>
            </div>
            <input type="number" hidden name="userId" value="${sessionScope.get("userId")}" />
            <input type="submit" value="Add">
        </form>
        <aside>
            <div>
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
            </div>
            <h6>Info:</h6>
            <textarea cols="20" rows="5" readonly></textarea>
        </aside>
    </div>    
</body>
</html>