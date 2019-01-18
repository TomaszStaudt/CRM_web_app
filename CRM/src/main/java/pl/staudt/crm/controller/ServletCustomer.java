package pl.staudt.crm.controller;

import pl.staudt.crm.dao.CustomerDao;
import pl.staudt.crm.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ServletCustomer", urlPatterns = "/customers")
public class ServletCustomer extends HttpServlet {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String DOB = "dob";

    private static final String EDIT_NAME = "edit_name";
    private static final String EDIT_SURNAME = "edit_surname";
    private static final String EDIT_DOB = "edit_dob";

    private static final String DELETE = "delete";
    private static final String EDIT = "edit";
    private static final String EDIT_ID = "edit_id";

    private static final String CUSTOMERS = "customers";
    private Customer customer;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int editId = request.getParameter(EDIT_ID) == null ? 0 : Integer.parseInt(request.getParameter(EDIT_ID));

        if (editId != 0) {
            String editName = request.getParameter(EDIT_NAME);
            String editSurname = request.getParameter(EDIT_SURNAME);
            LocalDate editDob = LocalDate.parse(request.getParameter(EDIT_DOB));
            customer = new Customer(editId, editName, editSurname, editDob);
            try {
                CustomerDao.update(customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/customers");
            return;
        }

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        LocalDate dob = LocalDate.parse(request.getParameter(DOB));
        customer = new Customer(name, surname, dob);

        try {
            CustomerDao.saveToDB(customer);
            response.sendRedirect("http://localhost:8080/customers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int delete = request.getParameter(DELETE) == null ? 0 : Integer.parseInt(request.getParameter(DELETE));
        int edit = request.getParameter(EDIT) == null ? 0 : Integer.parseInt(request.getParameter(EDIT));

        try {
            ArrayList<Customer> customers = (ArrayList<Customer>) CustomerDao.loadAll();
            request.setAttribute(CUSTOMERS, customers);

            if (delete != 0) {
                for (Customer customer : customers) {
                    if (customer.getId() == delete) {
                        CustomerDao.delete(customer);
                        response.sendRedirect("http://localhost:8080/customers");
                        return;
                    }
                }
            } else if (edit != 0) {
                request.setAttribute(EDIT_ID, edit);
                getServletContext().getRequestDispatcher("/JSP/customers.jsp").forward(request,response);
            } else {
                getServletContext().getRequestDispatcher("/JSP/customers.jsp").forward(request,response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
