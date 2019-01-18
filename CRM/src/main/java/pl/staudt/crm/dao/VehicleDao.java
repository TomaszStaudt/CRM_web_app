package pl.staudt.crm.dao;

import pl.staudt.crm.model.Vehicle;
import pl.staudt.crm.utils.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static Vehicle vehicle;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Vehicle> loadAll() throws SQLException {
        String sql =
                "SELECT\n" +
                "  vehicle.id,\n" +
                "  vehicle.model,\n" +
                "  vehicle.brand,\n" +
                "  vehicle.year,\n" +
                "  vehicle.crn,\n" +
                "  vehicle.review_date,\n" +
                "  vehicle.customer_id\n" +
                "FROM crm.vehicle;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static List<Vehicle> loadByCustomerId(int custometId) throws SQLException {
        String sql =
                "SELECT \n" +
                "  vehicle.id, \n" +
                "  vehicle.model, \n" +
                "  vehicle.brand, \n" +
                "  vehicle.year, \n" +
                "  vehicle.crn, \n" +
                "  vehicle.review_date,\n" +
                "  vehicle.customer_id\n" +
                "FROM crm.vehicle\n" +
                "WHERE vehicle.customer_id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, custometId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static void saveToDB(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO crm.vehicle VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setNull(1, vehicle.getId());
        preparedStatement.setString(2, vehicle.getModel());
        preparedStatement.setString(3, vehicle.getBrand());
        preparedStatement.setInt(4, vehicle.getYear());
        preparedStatement.setString(5, vehicle.getCarRegistrationNumber());
        preparedStatement.setDate(6, Date.valueOf(vehicle.getReviewDate()));
        setIntOrNull(preparedStatement, 7, vehicle.getCustomerId());
        preparedStatement.executeUpdate();
    }

    public static void delete(Vehicle vehicle) throws SQLException {
        String sql = "DELETE FROM crm.vehicle WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, vehicle.getId());
        preparedStatement.executeUpdate();
    }

    public static void update(Vehicle vehicle) throws SQLException {
        String sql =
                "UPDATE crm.vehicle\n" +
                "SET \n" +
                "  vehicle.model = ?, \n" +
                "  vehicle.brand = ?, \n" +
                "  vehicle.year = ?, \n" +
                "  vehicle.crn = ?, \n" +
                "  vehicle.review_date = ?, \n" +
                "  vehicle.customer_id = ?\n" +
                "WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, vehicle.getModel());
        preparedStatement.setString(2, vehicle.getBrand());
        preparedStatement.setInt(3, vehicle.getYear());
        preparedStatement.setString(4, vehicle.getCarRegistrationNumber());
        preparedStatement.setDate(5, Date.valueOf(vehicle.getReviewDate()));
        setIntOrNull(preparedStatement, 6, vehicle.getCustomerId());
        preparedStatement.setInt(7, vehicle.getId());
        preparedStatement.executeUpdate();
    }

    public static void updateCustomerId(Vehicle vehicle) throws SQLException {
        String sql =
                "UPDATE crm.vehicle\n" +
                "SET vehicle.customer_id = ?\n" +
                "WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, vehicle.getCustomerId());
        preparedStatement.setInt(2, vehicle.getId());
        preparedStatement.executeUpdate();
    }

    private static List<Vehicle> makeListFromResultSet(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException{
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        while (resultSet.next()) {
            vehicle = new Vehicle(
                    resultSet.getInt("id"),
                    resultSet.getString("model"),
                    resultSet.getString("brand"),
                    resultSet.getInt("year"),
                    resultSet.getString("crn"),
                    LocalDate.parse(resultSet.getString("review_date"), formatter),
                    resultSet.getInt("customer_id")
            );
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    private static void setIntOrNull(PreparedStatement preparedStatement, int parameterIndex, Integer input) throws SQLException {
        if (input != 0) {
            preparedStatement.setInt(parameterIndex, input);
        } else {
            preparedStatement.setNull(parameterIndex, Types.INTEGER);
        }

    }
}
