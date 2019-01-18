package pl.staudt.crm.controller;

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
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "index")
public class ServletStartPage extends HttpServlet {

    private static final String EMPLOYEES = "employees";
    public static final String ORDERS = "orders";
    private static final String VEHICLES = "vehicles";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ArrayList<Order> orders = (ArrayList<Order>) OrderDao.loadAll();
            ArrayList<Employee> employees = (ArrayList<Employee>) EmployeeDao.loadAll();
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) VehicleDao.loadAll();
            request.setAttribute(ORDERS, orders);
            request.setAttribute(EMPLOYEES, employees);
            request.setAttribute(VEHICLES, vehicles);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
