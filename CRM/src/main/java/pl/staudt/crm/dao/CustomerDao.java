package pl.staudt.crm.dao;

import pl.staudt.crm.model.Customer;
import pl.staudt.crm.utils.DbUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static Customer customer;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Customer> loadAll() throws SQLException {
        String sql =
                "SELECT customer.id, customer.name, customer.surname, customer.dob\n" +
                "FROM customer;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static Customer loadById(int id) throws SQLException {
        String sql =
                "SELECT customer.id, customer.name, customer.surname, customer.dob\n" +
                "FROM customer\n" +
                "WHERE customer.id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        return new Customer(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                LocalDate.parse(resultSet.getString("dob"), formatter)
        );
    }

    public static void saveToDB(Customer customer) throws SQLException {
        String sql = "INSERT INTO crm.customer VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setNull(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getSurname());
        preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirth()));
        preparedStatement.executeUpdate();
    }

    public static void delete(Customer customer) throws SQLException {
        String sql = "DELETE FROM crm.customer WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.executeUpdate();
    }

    public static void update(Customer customer) throws SQLException {
        String sql =
                "UPDATE crm.customer\n" +
                "SET customer.name = ?, customer.surname = ?, customer.dob = ?\n" +
                "WHERE customer.id = ?;\n";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setDate(3, Date.valueOf(customer.getDateOfBirth()));
        preparedStatement.setInt(4, customer.getId());
        preparedStatement.executeUpdate();
    }

    private static List<Customer> makeListFromResultSet(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    LocalDate.parse(resultSet.getString("dob"), formatter)
            );
            customers.add(customer);
        }
        return customers;
    }
}
