<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Authorisation</title>
    <link rel="stylesheet" type="text/css" media="screen" href="main.css" />
    
	<link rel="stylesheet" href="libs/bootstrap.min.css" />
	<script src="libs/bootstrap.js"></script>
</head>
<body>
    <div class="container">
        <form method="post" action="/">
            <h1 class="auth">Authorisation</h1>
            <div class="field">
                <label for="email">E-mail: </label>
                <input id="email" name="login_email" type="email" required>
            </div>
            <div class="field">
                <label for="password">Password: </label>
                <input id="password" name="login_password" type="password" required>
            </div>
            <input type="submit" value="Enter">
        </form>
        <select class="language">
            <option>Українська</option>
            <option>Російська</option>
            <option selected>English</option>
        </select>
    </div>    
</body>
</html>