package pl.staudt.crm.dao;

import pl.staudt.crm.model.Employee;
import pl.staudt.crm.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static Employee employee;

    public static List<Employee> loadAll() throws SQLException {
        ArrayList<Employee> customers = new ArrayList<>();
        String sql =
                "SELECT employee.id, employee.name, employee.surname, employee.address, employee.note, employee.hcost, employee.phone\n" +
                "FROM crm.employee;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            employee = new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("address"),
                    resultSet.getString("note"),
                    resultSet.getBigDecimal("hcost"),
                    resultSet.getInt("phone")
            );
            customers.add(employee);
        }
        return customers;
    }

    public static void saveToDB(Employee employee) throws SQLException {
        String sql = "INSERT INTO crm.employee VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setNull(1, employee.getId());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getSurname());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setString(5, employee.getNote());
        preparedStatement.setBigDecimal(6, employee.getHcost());
        preparedStatement.setInt(7, employee.getPhone());
        preparedStatement.executeUpdate();
    }

    public static void delete(Employee employee) throws SQLException {
        String sql = "DELETE FROM crm.employee WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.executeUpdate();
    }

    public static void update(Employee employee) throws SQLException {
        String sql =
                "UPDATE crm.employee\n" +
                "SET \n" +
                "  employee.name = ?, \n" +
                "  employee.surname = ?, \n" +
                "  employee.address = ?, \n" +
                "  employee.note = ?, \n" +
                "  employee.hcost = ?, \n" +
                "  employee.phone = ?\n" +
                "WHERE employee.id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getAddress());
        preparedStatement.setString(4, employee.getNote());
        preparedStatement.setBigDecimal(5, employee.getHcost());
        preparedStatement.setInt(6, employee.getPhone());
        preparedStatement.setInt(7, employee.getId());
        preparedStatement.executeUpdate();
    }
}
