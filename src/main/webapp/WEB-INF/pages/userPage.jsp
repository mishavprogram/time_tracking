<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>1</td>
                        <td>Убить Билла</td>
                        <td>01.01.2021</td>
                        <td>02.02.2022</td>
                        <td><input type="submit" value="Delete"></td>
                    </tr>
                    <tr>
                            <td>1</td>
                            <td>Убить Билла</td>
                            <td>01.01.2021</td>
                            <td>02.02.2022</td>
                            <td><input type="submit" value="Delete"></td>
                        </tr>
                        <tr>
                                <td>1</td>
                                <td>Убить Билла</td>
                                <td>01.01.2021</td>
                                <td>02.02.2022</td>
                                <td><input type="submit" value="Delete"></td>
                            </tr>
                            <tr>
                                    <td>1</td>
                                    <td>Убить Билла</td>
                                    <td>01.01.2021</td>
                                    <td>02.02.2022</td>
                                    <td><input type="submit" value="Delete"></td>
                                </tr>
                                <tr>
                                        <td>1</td>
                                        <td>Убить Билла</td>
                                        <td>01.01.2021</td>
                                        <td>02.02.2022</td>
                                        <td><input type="submit" value="Delete"></td>
                                    </tr>
                                    <tr>
                                            <td>1</td>
                                            <td>Убить Билла</td>
                                            <td>01.01.2021</td>
                                            <td>02.02.2022</td>
                                            <td><input type="submit" value="Delete"></td>
                                        </tr>
                                        <tr>
                                                <td>1</td>
                                                <td>Убить Билла</td>
                                                <td>01.01.2021</td>
                                                <td>02.02.2022</td>
                                                <td><input type="submit" value="Delete"></td>
                                            </tr>
                                            <tr>
                                                    <td>1</td>
                                                    <td>Убить Билла</td>
                                                    <td>01.01.2021</td>
                                                    <td>02.02.2022</td>
                                                    <td><input type="submit" value="Delete"></td>
                                                </tr>
                                                <tr>
                                                        <td>1</td>
                                                        <td>Убить Билла</td>
                                                        <td>01.01.2021</td>
                                                        <td>02.02.2022</td>
                                                        <td><input type="submit" value="Delete"></td>
                                                    </tr>
                                                    <tr>
                                                            <td>1</td>
                                                            <td>Убить Билла</td>
                                                            <td>01.01.2021</td>
                                                            <td>02.02.2022</td>
                                                            <td><input type="submit" value="Delete"></td>
                                                        </tr>                    

                </table>
            </div>
            <div class="rightBlock">
                <div class="calendar">
                    <h3>Choose date:</h3>
                    <input type="date">
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