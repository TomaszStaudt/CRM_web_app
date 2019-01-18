<%@ page import="pl.staudt.crm.dao.VehicleDao" %>
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
    <title>Vehicles</title>

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
            <th>Model</th>
            <th>Brand</th>
            <th>Year</th>
            <th>CRG</th>
            <th>Review date</th>
            <th>Owner</th>
        </tr>
        <c:forEach var="vehicle" items="${vehicles}">
            <c:choose>
                <c:when test="${vehicle.id == edit_id}">
                    <tr>
                        <form action="/vehicles" method="post">
                            <td>
                                <input type="number" class="form-control form-control-sm" name="edit_id" value="${vehicle.id}" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="edit_model" value="${vehicle.model}">
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="edit_brand" value="${vehicle.brand}">
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="edit_year" value=${vehicle.year}>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="edit_crn" value="${vehicle.carRegistrationNumber}">
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="edit_review" value="${vehicle.reviewDate}">
                            </td>
                            <td>
                                <select class="form-control form-control-sm" name="edit_customer_id">
                                    <c:forEach var="customer" items="${customers}">
                                        <option value=${customer.id}>${customer}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-secondary btn-sm">Zakończ edycję</button>
                            </td>
                        </form>
                    </tr>
                </c:when>
                <c:when test="${vehicle.id == assign_id}">
                    <tr>
                        <form action="/vehicles" method="post">
                            <td>
                                <input type="number" class="form-control form-control-sm" name="assign_id" value="${vehicle.id}" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="model" value="${vehicle.model}" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="brand" value="${vehicle.brand}" readonly>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="year" value=${vehicle.year} readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="crn" value="${vehicle.carRegistrationNumber}" readonly>
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="review" value="${vehicle.reviewDate}" readonly>
                            </td>
                            <td>
                                <select class="form-control form-control-sm" name="edit_customer_id">
                                    <c:forEach var="customer" items="${customers}">
                                        <option value=${customer.id}>${customer}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-secondary btn-sm">Zakończ edycję</button>
                            </td>
                        </form>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.model}</td>
                        <td>${vehicle.brand}</td>
                        <td>${vehicle.year}</td>
                        <td>${vehicle.carRegistrationNumber}</td>
                        <td>${vehicle.reviewDate}</td>
                        <td>
                            <c:if test="${vehicle.customerId != 0}">
                                <c:forEach var="customer" items="${customers}">
                                    <c:if test="${vehicle.customerId == customer.id}">${customer}</c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                        <td>
                            <div class="btn-group">
                                <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Opcje
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="http://localhost:8080/vehicles?edit=${vehicle.id}">Edytuj</a>
                                    <a class="dropdown-item" href="http://localhost:8080/vehicles?delete=${vehicle.id}">Usuń</a>
                                    <a class="dropdown-item" href="http://localhost:8080/vehicles?assign=${vehicle.id}">Przypisz do klienta</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <tr>
            <form action="/vehicles" method="post">
                <td>
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="model" placeholder="model">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="brand" placeholder="brand">
                </td>
                <td>
                    <input type="number" class="form-control form-control-sm" name="year" placeholder="year">
                </td>
                <td>
                    <input type="text" class="form-control form-control-sm" name="crn" placeholder="crn">
                </td>
                <td>
                    <input type="date" class="form-control form-control-sm" name="review" placeholder="date">
                </td>
                <td>
                    <select class="form-control form-control-sm" name="customer_id">
                        <c:forEach var="customer" items="${customers}">
                            <option value=${customer.id}>${customer}</option>
                        </c:forEach>
                    </select>
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
