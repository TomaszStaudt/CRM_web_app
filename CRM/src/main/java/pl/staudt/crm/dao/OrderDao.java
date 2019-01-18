package pl.staudt.crm.dao;

import pl.staudt.crm.model.Order;
import pl.staudt.crm.model.Vehicle;
import pl.staudt.crm.utils.DbUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static Order order;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Order> loadAll() throws SQLException {
        String sql =
                "SELECT\n" +
                "  orders.id,\n" +
                "  orders.order_date,\n" +
                "  orders.pstart_date,\n" +
                "  orders.start_date,\n" +
                "  orders.pdescription,\n" +
                "  orders.rdescription,\n" +
                "  orders.status,\n" +
                "  orders.vehicle_id,\n" +
                "  orders.employee_id,\n" +
                "  orders.rcost,\n" +
                "  orders.mcost,\n" +
                "  orders.total_hours\n" +
                "FROM crm.orders;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static List<Order> loadAllById(int id) throws SQLException {
        String sql =
                "SELECT\n" +
                "  orders.order_date,\n" +
                "  orders.pstart_date,\n" +
                "  orders.start_date,\n" +
                "  orders.pdescription,\n" +
                "  orders.rdescription,\n" +
                "  orders.status,\n" +
                "  orders.vehicle_id,\n" +
                "  orders.employee_id,\n" +
                "  orders.rcost,\n" +
                "  orders.mcost,\n" +
                "  orders.total_hours\n" +
                "FROM crm.orders\n" +
                "WHERE order.id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static List<Order> loadAllByEmployeeIdAndVehicleId(int employeeId, int vehicleId) throws SQLException {
        String sql =
                "SELECT\n" +
                "  orders.id,\n" +
                "  orders.order_date,\n" +
                "  orders.pstart_date,\n" +
                "  orders.start_date,\n" +
                "  orders.pdescription,\n" +
                "  orders.rdescription,\n" +
                "  orders.status,\n" +
                "  orders.vehicle_id,\n" +
                "  orders.employee_id,\n" +
                "  orders.rcost,\n" +
                "  orders.mcost,\n" +
                "  orders.total_hours\n" +
                "FROM crm.orders\n" +
                "WHERE orders.employee_id = ? AND orders.vehicle_id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, vehicleId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static List<Order> loadAllByEmployeeId(int employeeId) throws SQLException {
        String sql =
                "SELECT\n" +
                "  orders.id,\n" +
                "  orders.order_date,\n" +
                "  orders.pstart_date,\n" +
                "  orders.start_date,\n" +
                "  orders.pdescription,\n" +
                "  orders.rdescription,\n" +
                "  orders.status,\n" +
                "  orders.vehicle_id,\n" +
                "  orders.employee_id,\n" +
                "  orders.rcost,\n" +
                "  orders.mcost,\n" +
                "  orders.total_hours\n" +
                "FROM crm.orders\n" +
                "WHERE orders.employee_id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return makeListFromResultSet(resultSet, preparedStatement);
    }

    public static void saveToDB(Order order) throws SQLException {
        String sql = "INSERT INTO crm.orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setNull(1, order.getId());
        preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
        preparedStatement.setDate(3, Date.valueOf(order.getEstimatedStartDate()));
        preparedStatement.setDate(4, Date.valueOf(order.getStartDate()));
        preparedStatement.setString(5, order.getProblemDescription());
        preparedStatement.setString(6, order.getRepairDescription());
        preparedStatement.setInt(7, order.getStatus());
        preparedStatement.setInt(8, order.getVehicleId());
        preparedStatement.setBigDecimal(9, order.getRepairCost());
        preparedStatement.setBigDecimal(10, order.getMaterialCost());
        preparedStatement.setInt(11, order.getTotalHours());
        preparedStatement.setInt(12, order.getEmployeeId());
        preparedStatement.executeUpdate();
    }

    public static void delete(Order order) throws SQLException {
        String sql = "DELETE FROM crm.orders WHERE id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, order.getId());
        preparedStatement.executeUpdate();
    }

    public static void update(Order order) throws SQLException {
        String sql =
                "UPDATE crm.orders\n" +
                "SET\n" +
                "  orders.order_date = ?,\n" +
                "  orders.pstart_date = ?,\n" +
                "  orders.start_date = ?,\n" +
                "  orders.pdescription = ?,\n" +
                "  orders.rdescription = ?,\n" +
                "  orders.status = ?,\n" +
                "  orders.vehicle_id = ?,\n" +
                "  orders.rcost = ?,\n" +
                "  orders.mcost = ?,\n" +
                "  orders.employee_id = ?,\n" +
                "  orders.total_hours = ?\n" +
                "WHERE orders.id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(order.getOrderDate()));
        preparedStatement.setDate(2, Date.valueOf(order.getEstimatedStartDate()));
        preparedStatement.setDate(3, Date.valueOf(order.getStartDate()));
        preparedStatement.setString(4, order.getProblemDescription());
        preparedStatement.setString(5, order.getRepairDescription());
        preparedStatement.setInt(6, order.getStatus());
        preparedStatement.setInt(7, order.getVehicleId());
        preparedStatement.setBigDecimal(8, order.getRepairCost());
        preparedStatement.setBigDecimal(9, order.getMaterialCost());
        preparedStatement.setInt(10, order.getEmployeeId());
        preparedStatement.setInt(11, order.getTotalHours());
        preparedStatement.setInt(12, order.getId());
        preparedStatement.executeUpdate();
    }
    public static void updateStatus(Order order) throws SQLException {
        String sql =
                "UPDATE crm.orders SET orders.status = ? WHERE orders.id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, order.getStatus());
        preparedStatement.setInt(2, order.getId());
        preparedStatement.executeUpdate();
    }

    public static void assignVehicleToEmployee(Order order) throws SQLException {
        String sql = "UPDATE crm.order SET vehicle_id = ? WHERE employee_id = ?;";
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, order.getVehicleId());
        preparedStatement.setInt(2, order.getEmployeeId());
        preparedStatement.executeUpdate();
    }

    private static List<Order> makeListFromResultSet(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException{
        ArrayList<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            order = new Order(
                    resultSet.getInt("id"),
                    LocalDate.parse(resultSet.getString("order_date"), formatter),
                    LocalDate.parse(resultSet.getString("pstart_date"), formatter),
                    LocalDate.parse(resultSet.getString("start_date"), formatter),
                    resultSet.getString("pdescription"),
                    resultSet.getString("rdescription"),
                    resultSet.getInt("status"),
                    resultSet.getInt("vehicle_id"),
                    resultSet.getInt("employee_id"),
                    resultSet.getInt("total_hours"),
                    resultSet.getBigDecimal("rcost"),
                    resultSet.getBigDecimal("mcost")

            );
            orders.add(order);
        }
        return orders;
    }
}
