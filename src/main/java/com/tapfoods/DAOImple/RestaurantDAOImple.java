package com.tapfoods.DAOImple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.DAO.RestaurantDAO;
import com.tapfoods.Model.Restaurant;
import com.tapfoods.Utility.DBConnection;

public class RestaurantDAOImple implements RestaurantDAO {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String INSERT_QUERY = "INSERT INTO restaurant (name, cuisine_type, time, address, rating, is_active, img_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getCusineType());
            preparedStatement.setString(3, restaurant.getTime());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setFloat(5, restaurant.getRating());
            preparedStatement.setString(6, restaurant.getIsActive());
            preparedStatement.setString(7, restaurant.getImagePath());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        String GET_BY_ID = "SELECT * FROM restaurant WHERE restaurant_id = ?";
        Restaurant restaurant = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCusineType(rs.getString("cuisine_type"));
                restaurant.setTime(rs.getString("time"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRating(rs.getFloat("rating"));
                restaurant.setIsActive(rs.getString("is_active"));
                restaurant.setImagePath(rs.getString("img_path"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        String UPDATE_QUERY = "UPDATE restaurant SET name = ?, cuisine_type = ?, time = ?, address = ?, rating = ?, is_active = ?, img_path = ? WHERE restaurant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getCusineType());
            preparedStatement.setString(3, restaurant.getTime());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setFloat(5, restaurant.getRating());
            preparedStatement.setString(6, restaurant.getIsActive());
            preparedStatement.setString(7, restaurant.getImagePath());
            preparedStatement.setInt(8, restaurant.getRestaurantId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Updated rows: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, restaurantId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        String SELECT_ALL = "SELECT * FROM restaurant";
        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCusineType(rs.getString("cuisine_type"));
                restaurant.setTime(rs.getString("time"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRating(rs.getFloat("rating"));
                restaurant.setIsActive(rs.getString("is_active"));
                restaurant.setImagePath(rs.getString("img_path"));

                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }
}
