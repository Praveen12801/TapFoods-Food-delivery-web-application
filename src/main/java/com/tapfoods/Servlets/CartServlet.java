package com.tapfoods.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tapfoods.DAO.MenuDAO;
import com.tapfoods.DAOImple.MenuDAOImple;
import com.tapfoods.Model.Cart;
import com.tapfoods.Model.CartItem;
import com.tapfoods.Model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // ✅ Step 3: Fix restaurantId retrieval from either snake_case or camelCase
        Integer restaurantId = getRestaurantIdFromRequest(req);

        if (restaurantId != null) {
            Integer currentRestaurantId = (Integer) session.getAttribute("restaurantId");

            // Reset cart if restaurant changes or cart is null
            if (cart == null || currentRestaurantId == null || !currentRestaurantId.equals(restaurantId)) {
                cart = new Cart();
                session.setAttribute("cart", cart);
                session.setAttribute("restaurantId", restaurantId); // ✅ Store restaurantId in session
            }

            String action = req.getParameter("action");

            try {
                if (action != null) {
                    switch (action) {
                        case "add":
                            addItemToCart(req, cart);
                            break;
                        case "update":
                            updateItemToCart(req, cart);
                            break;
                        case "remove":
                            removeItemFromCart(req, cart);
                            break;
                        default:
                            System.out.println("Unknown cart action: " + action);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(); // Use logging in real apps
            }

            resp.sendRedirect("Cart.jsp");
        } else {
            resp.getWriter().println("Missing restaurantId. Cannot process cart.");
        }
    }

    // ✅ Handles both restaurantId and restaurant_id param names
    private Integer getRestaurantIdFromRequest(HttpServletRequest req) {
        String param = req.getParameter("restaurant_id"); // snake_case
        if (param == null || param.isEmpty()) {
            param = req.getParameter("restaurantId"); // camelCase fallback
        }
        try {
            return (param != null && !param.isEmpty()) ? Integer.parseInt(param) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void updateItemToCart(HttpServletRequest req, Cart cart) {
        try {
            int menuId = Integer.parseInt(req.getParameter("menuId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            cart.updateCartItem(menuId, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity or menuId for update.");
        }
    }

    private void removeItemFromCart(HttpServletRequest req, Cart cart) {
        try {
            int menuId = Integer.parseInt(req.getParameter("menuId"));
            cart.removeItem(menuId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid menuId for removal.");
        }
    }

    private void addItemToCart(HttpServletRequest req, Cart cart) throws SQLException {
        try {
            int menuId = Integer.parseInt(req.getParameter("menuId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            MenuDAO menuDAO = new MenuDAOImple();
            Menu menuItem = menuDAO.getMenu(menuId);

            if (menuItem != null) {
                CartItem item = new CartItem(
                    menuItem.getMenuId(),
                    menuItem.getRestaurantId(),
                    menuItem.getItemName(),
                    quantity,
                    menuItem.getPrice()
                );
                cart.addCartItem(item);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid menuId or quantity for adding to cart.");
        }
    }
}
