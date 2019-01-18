<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tomasz Staudt
  Date: 12.01.2019
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
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
            <th>Order date</th>
            <th>Est start date</th>
            <th>Start Date</th>
            <th>Problem desc.</th>
            <th>Repair desc.</th>
            <th>Vehicle</th>
            <th>Employee</th>
            <th>Total Hours</th>
            <th>Material cost</th>
            <th>Repair Cost</th>
            <th>Status</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <c:choose>
                <c:when test="${order.id == edit_id}">
                    <tr>
                        <form action="/orders" method="post">
                            <td>
                                <input type="number" class="form-control form-control-sm" name="edit_id" value="${order.id}" readonly>
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="edit_order_date" value="${order.orderDate}">
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="edit_est_start_date" value="${order.estimatedStartDate}">
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="edit_start_date" value="${order.startDate}">
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="edit_problem_description" value="${order.problemDescription}">
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="edit_repair_description" value="${order.repairDescription}">
                            </td>
                            <td>
                                <select class="form-control form-control-sm" name="edit_vehicle_id">
                                    <c:forEach var="vehicle" items="${vehicles}">
                                        <option value=${vehicle.id}>${vehicle}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select class="form-control form-control-sm" name="edit_employee_id">
                                        <%--<c:forEach var='employee' items='${employees}'>--%>
                                    <option value=1>1</option>
                                        <%--</c:forEach>--%>
                                </select>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="edit_total_hours" value="${order.totalHours}">
                            </td>
                            <td>
                                <input type="number" step="0.01" min="0.01" class="form-control form-control-sm" name="edit_material_cost" value=${order.materialCost}>
                            </td>
                            <td>
                                <input type="number" step="0.01" min="0.01" class="form-control form-control-sm" name="edit_repair_cost" value=${order.repairCost}>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="edit_status" value="${order.status}" readonly>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-secondary btn-sm">Zakończ edycję</button>
                            </td>
                        </form>
                    </tr>
                </c:when>
                <c:when test="${order.id == assign_id}">
                    sfdaasdfasdfasdfasfd
                    <tr>
                        <form action="/orders" method="post">
                            <td>
                                <input type="number" class="form-control form-control-sm" name="assign_id" value="${order.id}" readonly>
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="order_date" value="${order.orderDate}" readonly>
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="est_start_date" value="${order.estimatedStartDate}" readonly>
                            </td>
                            <td>
                                <input type="date" class="form-control form-control-sm" name="start_date" value="${order.startDate}" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="problem_description" value="${order.problemDescription}" readonly>
                            </td>
                            <td>
                                <input type="text" class="form-control form-control-sm" name="repair_description" value="${order.repairDescription}" readonly>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="vehicle_id" value="${order.vehicleId}" readonly>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="employee_id" value="${order.employeeId}" readonly>
                            </td>
                            <td>
                                <input type="number" class="form-control form-control-sm" name="total_hours" value="${order.totalHours}" readonly>
                            </td>
                            <td>
                                <input type="number" step="0.01" class="form-control form-control-sm" name="material_cost" value="${order.materialCost}" readonly>
                            </td>
                            <td>
                                <input type="number" step="0.01" class="form-control form-control-sm" name="repair_cost" value="${order.repairCost}" readonly>
                            </td>
                            <td>
                                <select class="form-control form-control-sm" name="assign_status">
                                    <option value=1>Przyjęty</option>
                                    <option value=2>Zatwierdzone koszty naprawy</option>
                                    <option value=3>W naprawie</option>
                                    <option value=4>Gotowy do odbioru</option>
                                    <option value=5>Rezygnacja</option>
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
                    <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.estimatedStartDate}</td>
                    <td>${order.startDate}</td>
                    <td style="font-size: x-small">${order.problemDescription}</td>
                    <td style="font-size: x-small">${order.repairDescription}</td>
                    <td style="font-size: x-small">
                        <c:forEach var="vehicle" items="${vehicles}">
                            <c:if test="${vehicle.id == order.vehicleId}">${vehicle}</c:if>
                        </c:forEach>
                    </td>
                    <td style="font-size: x-small">
                        <c:forEach var="employee" items="${employees}">
                            <c:if test="${employee.id == order.employeeId}">${employee}</c:if>
                        </c:forEach>
                    </td>
                    <td>${order.totalHours}</td>
                    <td>${order.materialCost}</td>
                    <td>${order.repairCost}</td>
                    <c:choose>
                        <c:when test="${order.status == 1}"><td style="font-size: x-small">Przyjęty</td></c:when>
                        <c:when test="${order.status == 2}"><td style="font-size: x-small">Zatwierdzone koszty naprawy</td></c:when>
                        <c:when test="${order.status == 3}"><td style="font-size: x-small">W naprawie</td></c:when>
                        <c:when test="${order.status == 4}"><td style="font-size: x-small">Gotowy do odbioru</td></c:when>
                        <c:when test="${order.status == 5}"><td style="font-size: x-small">Rezygnacja</td></c:when>
                        <c:otherwise><td></td></c:otherwise>
                    </c:choose>
                    <td>
                        <div class="btn-group">
                            <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Opcje
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="http://localhost:8080/orders?edit=${order.id}">Edytuj</a>
                                <a class="dropdown-item" href="http://localhost:8080/orders?delete=${order.id}">Usuń</a>
                                <a class="dropdown-item" href="http://localhost:8080/orders?assign=${order.id}">Zmień status</a>
                            </div>
                        </div>
                    </td>
                </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <tr>
            <form action="/orders" method="post">
                <td></td>
                <td>
                    <input type="date" class="form-control form-control-sm" name="order_date" placeholder="date">
                </td>
                <td>
                    <input type="date" class="form-control form-control-sm" name="est_start_date" placeholder="date">
                </td>
                <td>
                    <input type="date" class="form-control form-control-sm" name="start_date" placeholder="date">
                </td>
                <td style="font-size: x-small">
                    <input type="text" class="form-control form-control-sm" name="problem_description" placeholder="problem description">
                </td>
                <td style="font-size: x-small">
                    <input type="text" class="form-control form-control-sm" name="repair_description" placeholder="repair description">
                </td>
                <td style="font-size: x-small">
                    <select class="form-control form-control-sm" name="vehicle_id">
                        <c:forEach var="vehicle" items="${vehicles}">
                            <option value=${vehicle.id}>${vehicle}</option>
                        </c:forEach>
                    </select>
                </td>
                <td style="font-size: x-small">
                    <select class="form-control form-control-sm" name="employee_id">
                        <c:forEach var='employee' items='${employees}'>
                            <option value=${employee.id}>${employee}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="number" class="form-control form-control-sm" name="total_hours" placeholder="total hours">
                </td>
                <td>
                    <input type="number" step="0.01" class="form-control form-control-sm" name="material_cost" placeholder="material cost">
                </td>
                <td>
                    <input type="number" step="0.01" class="form-control form-control-sm" name="repair_cost" placeholder="repair cost">
                </td>
                <td style="font-size: x-small">
                    <input type="number" class="form-control form-control-sm" name="status" value=1 readonly>
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
