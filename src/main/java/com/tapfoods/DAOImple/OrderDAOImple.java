package com.tapfoods.DAOImple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.DAO.OrderDAO;
import com.tapfoods.Model.Order;
import com.tapfoods.Model.Order.PaymentMode;
import com.tapfoods.Utility.DBConnection;

public class OrderDAOImple implements OrderDAO {

	// In OrderDAO and OrderDAOImple
	public int addOrder(Order order) {
	    int orderId = 0;
	    String sql = "INSERT INTO orders (restaurant_id, user_id, order_date, total_amount, status, payment_mode) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setInt(1, order.getRestaurantId());
	        stmt.setInt(2, order.getUserId());
	        stmt.setTimestamp(3, order.getOrderDate());
	        stmt.setDouble(4, order.getTotalAmount());
	        stmt.setString(5, order.getStatus());
	        stmt.setString(6, order.getPaymentMode().toString());

	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            orderId = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return orderId;
	}



    @Override
    public Order getOrder(int orderId) {
        String GET_ORDER = "SELECT * FROM orders WHERE order_id = ?";
        Order order = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {

            preparedStatement.setInt(1, orderId);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                order = new Order();
                order.setOrderId(res.getInt("order_id"));
                order.setRestaurantId(res.getInt("restaurant_id"));
                order.setUserId(res.getInt("user_id")); // Fixed typo from usesr_id
                order.setOrderDate(res.getTimestamp("order_date"));
                order.setTotalAmount(res.getDouble("total_amount"));
                order.setStatus(res.getString("status"));
                order.setPaymentMode(PaymentMode.valueOf(res.getString("payment_mode")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void updateOrder(Order order) {
        String UPDATE_ORDER = "UPDATE orders SET total_amount = ?, status = ?, payment_mode = ? WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {

            preparedStatement.setDouble(1, order.getTotalAmount());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setString(3, order.getPaymentMode().toString());
            preparedStatement.setInt(4, order.getOrderId());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setInt(1, orderId);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String GET_ALL_ORDERS = "SELECT * FROM orders";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
             ResultSet res = preparedStatement.executeQuery()) {

            while (res.next()) {
                Order order = new Order();
                order.setOrderId(res.getInt("order_id"));
                order.setRestaurantId(res.getInt("restaurant_id"));
                order.setUserId(res.getInt("user_id"));
                order.setOrderDate(res.getTimestamp("order_date"));
                order.setTotalAmount(res.getDouble("total_amount"));
                order.setStatus(res.getString("status"));
                order.setPaymentMode(PaymentMode.valueOf(res.getString("payment_mode")));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrdersByUser(int userId) {
        List<Order> orderByUser = new ArrayList<>();
        String GET_ORDER_BY_USER = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER)) {

            preparedStatement.setInt(1, userId);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                Order order = new Order();
                order.setOrderId(res.getInt("order_id"));
                order.setRestaurantId(res.getInt("restaurant_id"));
                order.setUserId(res.getInt("user_id"));
                order.setOrderDate(res.getTimestamp("order_date"));
                order.setTotalAmount(res.getDouble("total_amount"));
                order.setStatus(res.getString("status"));
                order.setPaymentMode(PaymentMode.valueOf(res.getString("payment_mode")));

                orderByUser.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderByUser;
    }
}
