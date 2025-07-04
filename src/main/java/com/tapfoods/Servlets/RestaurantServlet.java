package com.tapfoods.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tapfoods.DAO.RestaurantDAO;
import com.tapfoods.DAOImple.RestaurantDAOImple;
import com.tapfoods.Model.Restaurant;

@WebServlet("/Restaurant")
public class RestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAO restaurantDAO = new RestaurantDAOImple();

		List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

		req.setAttribute("restaurants", restaurants);
		req.getRequestDispatcher("Restaurant.jsp").forward(req, resp);
	}
}
