<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="black" text="white">
<img width="100%" src="<c:url value="/static/gremlin.jpg"/>">
<br>
<table border="1" align="center" width="100%">
    <thead>
    <tr>
        <td align="center"><b>Отправитель</b></td>
        <td align="center"><b>Получатель</b></td>
        <td align="center"><b>Валюта отправки</b></td>
        <td align="center"><b>валюта получения</b></td>
        <td align="center"><b>Сумма отправки</b></td>
    </tr>
    </thead>
    <c:forEach items="${list}" var="trsnsh">
        <tr>
            <td align="center">${trsnsh.fromUser}</td>
            <td align="center">${trsnsh.toUser}</td>
            <td align="center">${trsnsh.fromCurensy}</td>
            <td align="center">${trsnsh.toCurensy}</td>
            <td align="center">${trsnsh.amount}</td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="/">Вернуться в светлое прошлое)</a>

</body>
</html>
