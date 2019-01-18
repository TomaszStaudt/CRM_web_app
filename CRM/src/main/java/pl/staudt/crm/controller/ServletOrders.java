package pl.staudt.crm.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import pl.staudt.crm.dao.EmployeeDao;
import pl.staudt.crm.dao.OrderDao;
import pl.staudt.crm.dao.VehicleDao;
import pl.staudt.crm.model.Employee;
import pl.staudt.crm.model.Order;
import pl.staudt.crm.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ServletOrders", urlPatterns = "/orders")
public class ServletOrders extends HttpServlet {

    private static final String EDIT_ORDER_DATE = "edit_order_date";
    private static final String EDIT_EST_START_DATE = "edit_est_start_date";
    private static final String EDIT_START_DATE = "edit_start_date";
    private static final String EDIT_PROBLEM_DESCRIPTION = "edit_problem_description";
    private static final String EDIT_REPAIR_DESCRIPTION = "edit_repair_description";
    private static final String EDIT_VEHICLE_ID = "edit_vehicle_id";
    private static final String EDIT_EMPLOYEE_ID = "edit_employee_id";
    private static final String EDIT_STATUS = "edit_status";
    private static final String EDIT_MATERIAL_COST = "edit_material_cost";
    private static final String EDIT_REPAIR_COST = "edit_repair_cost";
    private static final String EDIT_TOTAL_HOURS = "edit_total_hours";

    private static final String ORDER_DATE = "order_date";
    private static final String EST_START_DATE = "est_start_date";
    private static final String START_DATE = "start_date";
    private static final String PROBLEM_DESCRIPTION = "problem_description";
    private static final String REPAIR_DESCRIPTION = "repair_description";
    private static final String VEHICLE_ID = "vehicle_id";
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String STATUS = "status";
    private static final String MATERIAL_COST = "material_cost";
    private static final String REPAIR_COST = "repair_cost";
    private static final String TOTAL_HOURS = "total_hours";

    private static final String ASSIGN_STATUS = "assign_status";

    private static final String DELETE = "delete";
    private static final String EDIT = "edit";
    private static final String EDIT_ID = "edit_id";
    private static final String ASSIGN = "assign";
    private static final String ASSIGN_ID = "assign_id";

    private static final String ORDERS = "orders";
    private static final String EMPLOYEES = "employees";
    private static final String VEHICLES = "vehicles";
    private Order order;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int editId = request.getParameter(EDIT_ID) == null ? 0 : Integer.parseInt(request.getParameter(EDIT_ID));
        int assignId = request.getParameter(ASSIGN_ID) == null ? 0 : Integer.parseInt(request.getParameter(ASSIGN_ID));

        if (editId != 0) {
            LocalDate editOrderDate = LocalDate.parse(request.getParameter(EDIT_ORDER_DATE));
            LocalDate editEstStartDate = LocalDate.parse(request.getParameter(EDIT_EST_START_DATE));
            LocalDate editStartDate= LocalDate.parse(request.getParameter(EDIT_START_DATE));
            String editProblemDescription = request.getParameter(EDIT_PROBLEM_DESCRIPTION);
            String editRepairDescription = request.getParameter(EDIT_REPAIR_DESCRIPTION);
            int editVehicleId = Integer.parseInt(request.getParameter(EDIT_VEHICLE_ID));
            int editEmployeeId = Integer.parseInt(request.getParameter(EDIT_EMPLOYEE_ID));
            int editTotalHours = Integer.parseInt(request.getParameter(EDIT_TOTAL_HOURS));
            int editStatus = Integer.parseInt(request.getParameter(EDIT_STATUS));
            BigDecimal editMaterialCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(EDIT_MATERIAL_COST)));
            BigDecimal editRepairCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(EDIT_REPAIR_COST)));
            order = new Order(editId, editOrderDate, editEstStartDate, editStartDate, editProblemDescription, editRepairDescription, editStatus, editVehicleId, editEmployeeId, editTotalHours, editRepairCost, editMaterialCost);

            try {
                OrderDao.update(order);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/orders");
            return;
        } else if (assignId != 0) {
            int assignStatus = Integer.parseInt(request.getParameter(ASSIGN_STATUS));
            order = new Order(assignId, assignStatus);

            try {
                OrderDao.updateStatus(order);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/orders");
            return;
        }

        LocalDate orderDate = LocalDate.parse(request.getParameter(ORDER_DATE));
        LocalDate estStartDate = LocalDate.parse(request.getParameter(EST_START_DATE));
        LocalDate startDate= LocalDate.parse(request.getParameter(START_DATE));
        String problemDescription = request.getParameter(PROBLEM_DESCRIPTION);
        String repairDescription = request.getParameter(REPAIR_DESCRIPTION);
        int vehicleId = Integer.parseInt(request.getParameter(VEHICLE_ID));
        int employeeId = Integer.parseInt(request.getParameter(EMPLOYEE_ID));
        int totalHours = Integer.parseInt(request.getParameter(TOTAL_HOURS));
        int status = Integer.parseInt(request.getParameter(STATUS));
        BigDecimal materialCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(MATERIAL_COST)));
        BigDecimal repairCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(REPAIR_COST)));
        order = new Order(orderDate, estStartDate, startDate, problemDescription, repairDescription, status, vehicleId, employeeId, totalHours, repairCost, materialCost);

        try {
            OrderDao.saveToDB(order);
            response.sendRedirect("http://localhost:8080/orders");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int delete = request.getParameter(DELETE) == null ? 0 : Integer.parseInt(request.getParameter(DELETE));
        int edit = request.getParameter(EDIT) == null ? 0 : Integer.parseInt(request.getParameter(EDIT));
        int assign = request.getParameter(ASSIGN) == null ? 0 : Integer.parseInt(request.getParameter(ASSIGN));

        try {
            ArrayList<Order> orders = (ArrayList<Order>) OrderDao.loadAll();
            ArrayList<Employee> employees = (ArrayList<Employee>) EmployeeDao.loadAll();
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) VehicleDao.loadAll();
            request.setAttribute(ORDERS, orders);
            request.setAttribute(EMPLOYEES, employees);
            request.setAttribute(VEHICLES, vehicles);

            if (delete != 0) {
                for (Order order : orders) {
                    if (order.getId() == delete) {
                        OrderDao.delete(order);
                        response.sendRedirect("http://localhost:8080/orders");
                        return;
                    }
                }
            } else if (edit != 0) {
                request.setAttribute(EDIT_ID, edit);
                getServletContext().getRequestDispatcher("/JSP/orders.jsp").forward(request,response);
            } else if (assign != 0) {
                request.setAttribute(ASSIGN_ID, assign);
                getServletContext().getRequestDispatcher("/JSP/orders.jsp").forward(request,response);
            } else {
                getServletContext().getRequestDispatcher("/JSP/orders.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
