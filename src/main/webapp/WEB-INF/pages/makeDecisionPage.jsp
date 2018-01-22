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

        <form method="post" action="/makeDecision">
            <h1 class="auth">Confirm order</h1>
            <div class="field">
                <label for="activity_user_email">User_email: </label>
                <input readonly id="activity_user_email" name="activity_user_email" value="${requestScope.get("activity_user_email")}"  type="text" required>
            </div>
            <div class="field">
                <label for="activity_name">Activity_name: </label>
                <input readonly id="activity_name" name="activity_name" value="${requestScope.get("activity_name")}"  type="text" required>
            </div>
            <div class="field">
                <label for="startDate">Start date: </label>
                <input readonly id="startDate" name="startDate"  value="${requestScope.get("activity_startDate")}"  type="text" required>
            </div>
            <div class="field">
                <label for="endDate">End date: </label>
                <input readonly id="endDate" name="endDate"  value="${requestScope.get("activity_endDate")}"  type="text" required>
            </div>
            <div class="field">
                <label for="action">Action: </label>
                <input readonly id="action" name="action"  value="${requestScope.get("activity_action")}"  type="text" required>
            </div>

            <input type="number" hidden name="activity_id" value="${requestScope.get("activity_id")}"/>
            <input type="number" hidden name="order_id" value="${requestScope.get("order_id")}"/>
            <input type="submit" name="confirm" value="confirm">
            <input type="submit" name="reject" value="reject">
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