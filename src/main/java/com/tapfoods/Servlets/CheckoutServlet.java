package com.tapfoods.Servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tapfoods.DAO.OrderDAO;
import com.tapfoods.DAO.OrderItemDAO;
import com.tapfoods.DAOImple.OrderDAOImple;
import com.tapfoods.DAOImple.OrderItemDAOImple;
import com.tapfoods.Model.*;
import com.tapfoods.Model.Order.PaymentMode;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final OrderDAO orderDAO = new OrderDAOImple();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImple(); // ✅ Declare this too

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Optional: Session debug
        Enumeration<String> attrNames = session.getAttributeNames();
        while(attrNames.hasMoreElements()) {
            String attr = attrNames.nextElement();
            System.out.println("SESSION DEBUG → " + attr + ": " + session.getAttribute(attr));
        }

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (cart != null && user != null && restaurantId != null && !cart.getItems().isEmpty()) {

            try {
                String mode = req.getParameter("paymentMode"); // From form select
                String address = req.getParameter("address");

                if (mode == null || mode.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Payment mode is required.");
                    return;
                }

                // ✅ Create and populate order object
                Order order = new Order();
                order.setUserId(user.getUserId());
                order.setRestaurantId(restaurantId);
                order.setOrderDate(new Timestamp(new Date().getTime()));
                order.setPaymentMode(PaymentMode.valueOf(mode.toUpperCase()));
                order.setStatus("Pending");
                order.setTotalAmount(cart.getTotalPrice());

                // ✅ Add order and get generated order ID
                int generatedOrderId = orderDAO.addOrder(order);

                if (generatedOrderId > 0) {
                    for (CartItem item : cart.getItems().values()) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setOrderId(generatedOrderId);

                        // ❗ Use correct method to get menuId
                        orderItem.setMenuId(item.getMenuId()); // ← Adjust if needed
                        orderItem.setQuantity(item.getQuantity());
                        orderItem.setTotalAmount(item.getPrice() * item.getQuantity());

                        orderItemDAO.addOrderItem(orderItem); // ✅ insert order item
                    }

                    session.removeAttribute("cart");
                    session.setAttribute("order", order);
                    resp.sendRedirect("Orderconfirmed.jsp");
                } else {
                    req.setAttribute("errorMsg", "Order placement failed. Please try again.");
                    req.getRequestDispatcher("Checkout.jsp").forward(req, resp);
                }

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("errorMsg", "Invalid payment mode or system error.");
                req.getRequestDispatcher("Checkout.jsp").forward(req, resp);
            }

        } else {
            resp.sendRedirect("Cart.jsp");
        }
    }
}
