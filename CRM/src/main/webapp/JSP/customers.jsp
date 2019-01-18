<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tomasz Staudt
  Date: 12.01.2019
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>
<body>
    <table class = "table table-sm table-dark">
        <tr>
            <td colspan="100%" style="text-align: center">
                <header><jsp:include page="header.jsp"/></header>
            </td>
        </tr>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Date of birth</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <c:if test="${customer.id == edit_id}">
                <tr>
                    <form action="/customers" method="post">
                        <td>
                            <input type="number" class="form-control form-control-sm" name="edit_id" value="${customer.id}" readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_name" value="${customer.name}">
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_surname" value="${customer.surname}">
                        </td>
                        <td>
                            <input type="date" class="form-control form-control-sm" name="edit_dob" value="${customer.dateOfBirth}">
                        </td>
                        <td>
                            <button type="submit" class="btn btn-secondary btn-sm">Zakończ edycję</button>
                        </td>
                    </form>
                </tr>
            </c:if>
            <c:if test="${customer.id != edit_id}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.surname}</td>
                    <td>${customer.dateOfBirth}</td>
                    <td>
                        <div class="btn-group">
                            <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Opcje
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="http://localhost:8080/customers?edit=${customer.id}">Edytuj</a>
                                <a class="dropdown-item" href="http://localhost:8080/customers?delete=${customer.id}">Usuń</a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        <tr>
            <form action="/customers" method="post">
                <td></td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="name" placeholder="name">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="surname" placeholder="surname">
                </td>
                <td>
                    <input type="date" class="form-control form-control-sm" name="dob" placeholder="date">
                </td>
                <td>
                    <button type="submit" class="btn btn-secondary btn-sm">Dodaj</button>
                </td>
            </form>
        </tr>

    </table>

    <footer><jsp:include page="footer.jsp"></jsp:include></footer>
</body>
</html>
