package com.tapfoods.Servlets;

import com.tapfoods.Model.Menu;
import com.tapfoods.Model.Restaurant;
import com.tapfoods.DAO.MenuDAO;
import com.tapfoods.DAO.RestaurantDAO;
import com.tapfoods.DAOImple.MenuDAOImple;
import com.tapfoods.DAOImple.RestaurantDAOImple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));

            // ✅ FIXED: initialize session
            HttpSession session = req.getSession();
            session.setAttribute("restaurantId", restaurantId);

            RestaurantDAO restaurantDAO = new RestaurantDAOImple();
            Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

            MenuDAO menuDAO = new MenuDAOImple();
            List<Menu> menuList = menuDAO.getMenuByResId(restaurantId);

            req.setAttribute("restaurant", restaurant);
            req.setAttribute("menuList", menuList);

            req.getRequestDispatcher("Menu.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "Failed to load menu.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
