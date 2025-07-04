package com.tapfoods.DAOImple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.DAO.UserDAO;
import com.tapfoods.Model.User;
import com.tapfoods.Utility.DBConnection;

public class UserDAOImple implements UserDAO {

    @Override
    public void addUser(User user) {
        String INSERT = "INSERT INTO user (name, username, password, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int userId) {
        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            System.out.println("DB connection failed!");
            return null;
        }

        String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                int id = res.getInt("user_id");
                String name = res.getString("name");
                String username = res.getString("username");
                String password = res.getString("password");
                String email = res.getString("email");
                String phone = res.getString("phone");
                String address = res.getString("address");
                String role = res.getString("role");
                Date createdDate = res.getDate("created_date");
                Date lastLoginDate = res.getDate("last_login_date");

                user = new User(id, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Connection connection = DBConnection.getConnection();

        String UPDATE_USER = "UPDATE user SET name = ?, password = ?, phone = ?, address = ? WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getUserId());

            int i = preparedStatement.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        String DELETE_USERS = "DELETE FROM user WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS)) {

            preparedStatement.setInt(1, userId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User with ID " + userId + " was deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        String GET_ALL_USERS = "SELECT * FROM user";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GET_ALL_USERS)) {

            while (res.next()) {
                int userId = res.getInt("user_id");
                String name = res.getString("name");
                String username = res.getString("username");
                String password = res.getString("password");
                String email = res.getString("email");
                String phone = res.getString("phone");
                String address = res.getString("address");
                String role = res.getString("role");
                Date createdDate = res.getDate("created_date");
                Date lastLoginDate = res.getDate("last_login_date");

                User user = new User(userId, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
