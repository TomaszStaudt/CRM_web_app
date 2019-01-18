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
    <title>Employees</title>
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
            <th>Address</th>
            <th>Note</th>
            <th>Hourly cost</th>
            <th>Phone</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <c:choose>
                <c:when test="${employee.id == edit_id}">
                    <form action="/employees" method="post">
                        <td>
                            <input type="number" class="form-control form-control-sm" name="edit_id" value="${employee.id}" readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_name" value="${employee.name}">
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_surname" value="${employee.surname}">
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_address" value="${employee.address}">
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm" name="edit_note" value="${employee.note}">
                        </td>
                        <td>
                            <input type="number" step="0.01" class="form-control form-control-sm" name="edit_hourly_cost" value="${employee.hcost}">
                        </td>
                        <td>
                            <input type="number" class="form-control form-control-sm" name="edit_phone" value="${employee.phone}">
                        </td>
                        <td>
                            <button type="submit" class="btn btn-secondary btn-sm">Zakończ edycję</button>
                        </td>
                    </form>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td style="font-size: x-small">${employee.address}</td>
                        <td style="font-size: x-small">${employee.note}</td>
                        <td>${employee.hcost}</td>
                        <td>${employee.phone}</td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Opcje
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="http://localhost:8080/employees?edit=${employee.id}">Edytuj</a>
                                    <a class="dropdown-item" href="http://localhost:8080/employees?delete=${employee.id}">Usuń</a>
                                    <a class="dropdown-item" href="http://localhost:8080/orders?employee=${employee.id}">Przeglądaj zlecenia</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <tr>
            <form action="/employees" method="post">
                <td>
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="name" placeholder="name">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="surname" placeholder="surname">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="address" placeholder="address">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="note" placeholder="note">
                </td>
                <td>
                    <input type="number" step="0.01" class="form-control form-control-sm" name="hourly_cost" placeholder="hourly cost">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="phone" placeholder="phone">
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
