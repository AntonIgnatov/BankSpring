<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Bank</title>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>--%>
</head>
<body bgcolor="black" text="white">
<div align="center">
    <img width="100%" src="<c:url value="/static/gremlin.jpg"/>">
    <form action="/transfer" method="post">

        <h3>Перевод от одного пользователя к другому в одной валюте:</h3>
        От: <select name="user_from">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
        для:<select name="user_to">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>

        в валюте: <select name="curensy_single">
        <option value="0">UAH</option>
        <option value="1">USD</option>
        <option value="2">EUR</option>

    </select>
        на сумму: <input type="text" name="amount"> <input type="submit" value="подтвердить">
    </form>
    <br>
    <form action="/add" method="post">

        <h3>Пополнение счета в выбранной валюте:</h3>
        Для: <select name="user_single">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
        в валюте: <select name="curensy_single">
        <option value="0">UAH</option>
        <option value="1">USD</option>
        <option value="2">EUR</option>

    </select>
        на сумму: <input type="text" name="amount"> <input type="submit" value="подтвердить">
    </form>
    <br>
    <form action="/exchange" method="post">

        <h3>Обмен валюты:</h3>
        Для: <select name="user_id">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
        из валюты: <select name="curensy_from">
        <option value="0">UAH</option>
        <option value="1">USD</option>
        <option value="2">EUR</option>

    </select>
        </select>
        в: <select name="curensy_to">
        <option value="0">UAH</option>
        <option value="1">USD</option>
        <option value="2">EUR</option>

    </select>
        сумма для перевода: <input type="text" name="amount"> <input type="submit" value="подтвердить">
    </form>

    <h3>Трасзакциии:</h3>
    <div>
        <form action="/transh/user" method="post">
            Для пользователя: <select name="user_id">
            <option value="-1">Все</option>
            <c:forEach items="${users}" var="user">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>

        </select>
            <br>
            <input type="submit" value="подглядеть">
        </form>
    </div>
    <div>
        <form action="/transh/curensy" method="post">
            Для валюты: <select name="curensy_name">
            <option value="ALL">Все</option>
            <option value="UAH">UAH</option>
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>

        </select>
            <br>
            <input type="submit" value="подглядеть">
        </form>
    </div>




</div>
</body>
</html>
