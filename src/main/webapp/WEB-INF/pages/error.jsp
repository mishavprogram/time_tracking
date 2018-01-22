<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Success</title>
	<link rel="stylesheet" href="../../bootstrap/bootstrap.min.css" />
	<script src="../../bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/main.css" />
</head>
<body>
    <div class="container">
        <div>
            <h1>Some error was...</h1>
            <form method="get" action="/home">
            <input type="submit" value="Return to home page">
            </form>
        </div>
        <select class="language">
            <option>Українська</option>
            <option>Російська</option>
            <option selected>English</option>
        </select>
    </div>    
</body>
</html>