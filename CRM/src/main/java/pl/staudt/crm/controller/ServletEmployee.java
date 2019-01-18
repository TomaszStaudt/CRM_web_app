package pl.staudt.crm.controller;

import pl.staudt.crm.dao.CustomerDao;
import pl.staudt.crm.dao.EmployeeDao;
import pl.staudt.crm.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletEmployee", urlPatterns = "/employees")
public class ServletEmployee extends HttpServlet {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ADDRESS = "address";
    private static final String NOTE = "note";
    private static final String HOURLY_COST = "hourly_cost";
    private static final String PHONE = "phone";

    private static final String EDIT_NAME = "edit_name";
    private static final String EDIT_SURNAME = "edit_surname";
    private static final String EDIT_ADDRESS = "edit_address";
    private static final String EDIT_NOTE = "edit_note";
    private static final String EDIT_HOURLY_COST = "edit_hourly_cost";
    private static final String EDIT_PHONE = "edit_phone";

    private static final String DELETE = "delete";
    private static final String EDIT = "edit";
    private static final String EDIT_ID = "edit_id";

    public static final String EMPLOYEES = "employees";
    private Employee employee;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int editId = request.getParameter(EDIT_ID) == null ? 0 : Integer.parseInt(request.getParameter(EDIT_ID));

        if (editId != 0) {
            String editName = request.getParameter(EDIT_NAME);
            String editSurname = request.getParameter(EDIT_SURNAME);
            String editAddress = request.getParameter(EDIT_ADDRESS);
            String editNote = request.getParameter(EDIT_NOTE);
            BigDecimal editHourlyCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(EDIT_HOURLY_COST)));
            int editPhone = Integer.parseInt(request.getParameter(EDIT_PHONE));
            employee = new Employee(editId, editName, editSurname, editAddress, editNote, editHourlyCost, editPhone);
            try {
                EmployeeDao.update(employee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("http://localhost:8080/employees");
            return;
        }

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String address = request.getParameter(ADDRESS);
        String note = request.getParameter(NOTE);
        BigDecimal hourlyCost = BigDecimal.valueOf(Double.parseDouble(request.getParameter(HOURLY_COST)));
        int phone = Integer.parseInt(request.getParameter(PHONE));

        employee = new Employee(name, surname, address, note, hourlyCost, phone);
        try {
            EmployeeDao.saveToDB(employee);
            response.sendRedirect("http://localhost:8080/employees");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int delete = request.getParameter(DELETE) == null ? 0 : Integer.parseInt(request.getParameter(DELETE));
        int edit = request.getParameter(EDIT) == null ? 0 : Integer.parseInt(request.getParameter(EDIT));

        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) EmployeeDao.loadAll();
            request.setAttribute(EMPLOYEES, employees);

            if (delete != 0) {
                for (Employee employee : employees) {
                    if (employee.getId() == delete) {
                        EmployeeDao.delete(employee);
                        response.sendRedirect("http://localhost:8080/employees");
                        return;
                    }
                }
            } else if (edit != 0) {
                request.setAttribute(EDIT_ID, edit);
                getServletContext().getRequestDispatcher("/JSP/employees.jsp").forward(request,response);
            } else {
                getServletContext().getRequestDispatcher("/JSP/employees.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
