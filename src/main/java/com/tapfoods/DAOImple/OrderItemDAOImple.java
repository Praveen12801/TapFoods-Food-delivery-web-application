package com.tapfoods.DAOImple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.DAO.OrderItemDAO;
import com.tapfoods.Model.OrderItem;
import com.tapfoods.Utility.DBConnection;

public class OrderItemDAOImple implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String INSERT = "INSERT INTO order_item (order_id, menu_id, quantity, total_amount) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getTotalAmount());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row(s) inserted into order_item.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        String SELECT = "SELECT * FROM order_item WHERE order_item_id = ?";
        OrderItem orderItem = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {

            preparedStatement.setInt(1, orderItemId);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                orderItem = new OrderItem();
                orderItem.setOrderItemId(res.getInt("order_item_id"));
                orderItem.setOrderId(res.getInt("order_id"));
                orderItem.setMenuId(res.getInt("menu_id"));
                orderItem.setQuantity(res.getInt("quantity"));
                orderItem.setTotalAmount(res.getDouble("total_amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        String UPDATE = "UPDATE order_item SET quantity = ?, total_amount = ? WHERE order_item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setDouble(2, orderItem.getTotalAmount());
            preparedStatement.setInt(3, orderItem.getOrderItemId());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row(s) updated in order_item.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        String DELETE = "DELETE FROM order_item WHERE order_item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, orderItemId);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row(s) deleted from order_item.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        String SELECT_ALL = "SELECT * FROM order_item";
        List<OrderItem> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet res = stmt.executeQuery(SELECT_ALL)) {

            while (res.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(res.getInt("order_item_id"));
                orderItem.setOrderId(res.getInt("order_id"));
                orderItem.setMenuId(res.getInt("menu_id"));
                orderItem.setQuantity(res.getInt("quantity"));
                orderItem.setTotalAmount(res.getDouble("total_amount"));
                list.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
