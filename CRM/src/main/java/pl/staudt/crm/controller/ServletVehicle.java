package pl.staudt.crm.controller;

import pl.staudt.crm.dao.CustomerDao;
import pl.staudt.crm.dao.VehicleDao;
import pl.staudt.crm.model.Customer;
import pl.staudt.crm.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ServletVehicle", urlPatterns = "/vehicles")
public class ServletVehicle extends HttpServlet {

    private static final String MODEL = "model";
    private static final String BRAND = "brand";
    private static final String CRN = "crn";
    private static final String YEAR = "year";
    private static final String REVIEW = "review";
    private static final String CUSTOMER_ID = "customer_id";

    private static final String EDIT_MODEL = "edit_model";
    private static final String EDIT_BRAND = "edit_brand";
    private static final String EDIT_CRN = "edit_crn";
    private static final String EDIT_YEAR = "edit_year";
    private static final String EDIT_REVIEW = "edit_review";
    private static final String EDIT_CUSTOMER_ID = "edit_customer_id";

    private static final String DELETE = "delete";
    private static final String EDIT = "edit";
    private static final String ASSIGN = "assign";
    private static final String EDIT_ID = "edit_id";
    private static final String ASSIGN_ID = "assign_id";

    private static final String VEHICLES = "vehicles";
    private static final String CUSTOMERS = "customers";
    private Vehicle vehicle;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int editId = request.getParameter(EDIT_ID) == null ? 0 : Integer.parseInt(request.getParameter(EDIT_ID));
        int assignId = request.getParameter(ASSIGN_ID) == null ? 0 : Integer.parseInt(request.getParameter(ASSIGN_ID));

        if (editId != 0) {
            int id = Integer.parseInt(request.getParameter(EDIT_ID));
            String editModel = request.getParameter(EDIT_MODEL);
            String editBrand = request.getParameter(EDIT_BRAND);
            String editCRN = request.getParameter(EDIT_CRN);
            int editYear = Integer.parseInt(request.getParameter(EDIT_YEAR));
            LocalDate editReview = LocalDate.parse(request.getParameter(EDIT_REVIEW));
            int editCustomerId = Integer.parseInt(request.getParameter(EDIT_CUSTOMER_ID));
            vehicle = new Vehicle(id, editModel, editBrand, editYear, editCRN, editReview, editCustomerId);

            try {
                VehicleDao.update(vehicle);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/vehicles");
            return;
        } else if (assignId != 0) {
            int id = Integer.parseInt(request.getParameter(ASSIGN_ID));
            int editCustomerId = Integer.parseInt(request.getParameter(EDIT_CUSTOMER_ID));
            vehicle = new Vehicle(id, editCustomerId);

            try {
                VehicleDao.updateCustomerId(vehicle);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/vehicles");
            return;
        }

        String model = request.getParameter(MODEL);
        String brand = request.getParameter(BRAND);
        Integer year = Integer.parseInt(request.getParameter(YEAR));
        String crn = request.getParameter(CRN);
        LocalDate review = LocalDate.parse(request.getParameter(REVIEW));
        Integer customer_id = request.getParameter(CUSTOMER_ID).equals("") ? 0 : Integer.parseInt(request.getParameter(CUSTOMER_ID));

        vehicle = new Vehicle(model, brand, year, crn, review, customer_id);

        try {
            VehicleDao.saveToDB(vehicle);
            response.sendRedirect("http://localhost:8080/vehicles");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int delete = request.getParameter(DELETE) == null ? 0 : Integer.parseInt(request.getParameter(DELETE));
        int edit = request.getParameter(EDIT) == null ? 0 : Integer.parseInt(request.getParameter(EDIT));
        int assign = request.getParameter(ASSIGN) == null ? 0 : Integer.parseInt(request.getParameter(ASSIGN));

        try {
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) VehicleDao.loadAll();
            ArrayList<Customer> customers = (ArrayList<Customer>) CustomerDao.loadAll();
            request.setAttribute(VEHICLES, vehicles);
            request.setAttribute(CUSTOMERS, customers);

            if (delete != 0) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId() == delete) {
                        VehicleDao.delete(vehicle);
                        response.sendRedirect("http://localhost:8080/vehicles");
                        return;
                    }
                }
            } else if (edit != 0) {
                request.setAttribute(EDIT_ID, edit);
                getServletContext().getRequestDispatcher("/JSP/vehicles.jsp").forward(request,response);
            } else if (assign != 0) {
                request.setAttribute(ASSIGN_ID, assign);
                getServletContext().getRequestDispatcher("/JSP/vehicles.jsp").forward(request,response);
            } else {
                getServletContext().getRequestDispatcher("/JSP/vehicles.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
