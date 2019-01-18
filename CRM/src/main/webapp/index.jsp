<%--
  Created by IntelliJ IDEA.
  User: Tomasz Staudt
  Date: 08.01.2019
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Start Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
    <table class="table table-sm table-dark">
        <tr>
            <td colspan="100%" style="text-align: center">
                <header><jsp:include page="JSP/header.jsp"/></header>
            </td>
        </tr>
        <tr>
            <th>ID</th>
            <th>Order Date</th>
            <th>Employee</th>
            <th>Vehicle</th>
            <th>Status</th>
        </tr>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.startDate}</td>
            <td>
                <c:forEach var="employee" items="${employees}">
                    <c:if test="${employee.id == order.employeeId}">${employee}</c:if>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="vehicle" items="${vehicles}">
                    <c:if test="${vehicle.id == order.vehicleId}">${vehicle}</c:if>
                </c:forEach>
            </td>
            <c:choose>
                <c:when test="${order.status == 1}"><td>Przyjęty</td></c:when>
                <c:when test="${order.status == 2}"><td>Zatwierdzone koszty naprawy</td></c:when>
                <c:when test="${order.status == 3}"><td>W naprawie</td></c:when>
                <c:when test="${order.status == 4}"><td>Gotowy do odbioru</td></c:when>
                <c:when test="${order.status == 5}"><td>Rezygnacja</td></c:when>
                <c:otherwise><td></td></c:otherwise>
            </c:choose>
        </tr>
        </c:forEach>
    </table>
    <footer><jsp:include page="JSP/footer.jsp"></jsp:include></footer>
</body>
</html>
