package com.tapfoods.DAOImple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.DAO.MenuDAO;
import com.tapfoods.Model.Menu;
import com.tapfoods.Utility.DBConnection;

public class MenuDAOImple implements MenuDAO {

    @Override
    public void addMenu(Menu menu) {
        String INSERT_QUERY = "INSERT INTO menu (restaurant_id, item_name, description, price, is_available, ratings, image_path, is_veg) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, menu.getRestaurantId());
            preparedStatement.setString(2, menu.getItemName());
            preparedStatement.setString(3, menu.getDescription());
            preparedStatement.setDouble(4, menu.getPrice());
            preparedStatement.setString(5, menu.getIsAvailable());
            preparedStatement.setFloat(6, menu.getRatings());
            preparedStatement.setString(7, menu.getImagePath());
            preparedStatement.setBoolean(8, menu.getIsVeg());

            int i = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        String GET_BY_ID = "SELECT * FROM menu WHERE menu_id = ?";
        Menu menu = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {

            preparedStatement.setInt(1, menuId);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                menu = new Menu();
                menu.setMenuId(res.getInt("menu_id"));
                menu.setRestaurantId(res.getInt("restaurant_id"));
                menu.setItemName(res.getString("item_name"));
                menu.setDescription(res.getString("description"));
                menu.setPrice(res.getDouble("price"));
                menu.setIsAvailable(res.getString("is_available"));
                menu.setRatings(res.getFloat("ratings"));
                menu.setImagePath(res.getString("image_path"));
                menu.setIsVeg(res.getBoolean("is_veg"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        String UPDATE_QUERY = "UPDATE menu SET item_name = ?, description = ?, price = ?, is_available = ?, ratings = ?, image_path = ?, is_veg = ?, restaurant_id = ? WHERE menu_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, menu.getItemName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setDouble(3, menu.getPrice());
            preparedStatement.setString(4, menu.getIsAvailable());
            preparedStatement.setFloat(5, menu.getRatings());
            preparedStatement.setString(6, menu.getImagePath());
            preparedStatement.setBoolean(7, menu.getIsVeg());
            preparedStatement.setInt(8, menu.getRestaurantId());
            preparedStatement.setInt(9, menu.getMenuId());

            int i = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String DELETE_MENU = "DELETE FROM menu WHERE menu_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU)) {

            preparedStatement.setInt(1, menuId);

            int i = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menus = new ArrayList<>();
        String GET_ALL_MENU = "SELECT * FROM menu";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MENU);
             ResultSet res = preparedStatement.executeQuery()) {

            while (res.next()) {
                Menu menu = new Menu();
                menu.setMenuId(res.getInt("menu_id"));
                menu.setRestaurantId(res.getInt("restaurant_id"));
                menu.setItemName(res.getString("item_name"));
                menu.setDescription(res.getString("description"));
                menu.setPrice(res.getDouble("price"));
                menu.setIsAvailable(res.getString("is_available"));
                menu.setRatings(res.getFloat("ratings"));
                menu.setImagePath(res.getString("image_path"));
                menu.setIsVeg(res.getBoolean("is_veg"));

                menus.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menus;
    }

    @Override
    public List<Menu> getMenuByResId(int restaurantId) {
        List<Menu> menuByRestId = new ArrayList<>();
        String GET_BY_RESTID = "SELECT * FROM menu WHERE restaurant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_RESTID)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                Menu menu = new Menu();
                menu.setMenuId(res.getInt("menu_id"));
                menu.setRestaurantId(res.getInt("restaurant_id"));
                menu.setItemName(res.getString("item_name"));
                menu.setDescription(res.getString("description"));
                menu.setPrice(res.getDouble("price"));
                menu.setIsAvailable(res.getString("is_available"));
                menu.setRatings(res.getFloat("ratings"));
                menu.setImagePath(res.getString("image_path"));
                menu.setIsVeg(res.getBoolean("is_veg"));

                menuByRestId.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuByRestId;
    }
}
