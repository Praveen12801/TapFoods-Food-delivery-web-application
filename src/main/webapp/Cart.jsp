<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tapfoods.Model.CartItem" %>
<%@ page import="com.tapfoods.Model.Cart" %>

<%
    String userEmail = (String) session.getAttribute("userEmail");
    if (userEmail == null) {
        response.sendRedirect("Sign_in.jsp");
        return;
    }

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
        session.setAttribute("cart", cart);
    }

    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    if (restaurantId == null) {
        response.getWriter().println("Missing restaurantId. Cannot process cart.");
        return;
    }


    Map<Integer, CartItem> items = cart.getItems();
    String userName = (String) session.getAttribute("userName");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/cart.css" />
    <link rel="stylesheet" href="mediaquries.css" />
</head>
<body>
    <header>
        <nav id="desktop-nav">
            <div class="logo" role="banner">
                <img src="assets/symbol.png" alt="TAP FOODS logo" />
                <p>TAP FOODS</p>
            </div>
            <ul class="nav-links" role="navigation" aria-label="Primary navigation">
                <li><a href="Sign_up.jsp">Sign Up</a></li>
                <li><a href="#Help">Help</a></li>
                <li><a href="#Contact">Contact</a></li>
            </ul>
        </nav>
    </header>

    <div class="cart-container">
        <h2 class="cart-title">Welcome, <%= userName != null ? userName : "Guest" %>! Here's your Cart Items</h2>

        <div class="cart-items">
            <%
                if (items != null && !items.isEmpty()) {
                    for (CartItem item : items.values()) {
            %>
                <div class="cart-item">
                    <div class="item-info">
                        <div class="item-name"><%= item.getName() %></div>
                        <div class="item-price">₹ <%= item.getPrice() %></div>
                    </div>

                    <div class="quantity-controls">
                        <!-- + button -->
                        <form action="cart" method="post" style="display: inline;">
                            <input type="hidden" name="restaurant_id" value="<%= restaurantId %>">
                            <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                            <button class="quantity-btn">+</button>
                        </form>

                        <span class="quantity-display"><%= item.getQuantity() %></span>

                        <!-- - button -->
                        <form action="cart" method="post" style="display: inline;">
                            <input type="hidden" name="restaurant_id" value="<%= restaurantId %>">
                            <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                            <button class="quantity-btn" <%= item.getQuantity() == 1 ? "disabled" : "" %>>-</button>
                        </form>
                    </div>

                    <div class="remove-btn">
                        <form action="cart" method="post" style="display: inline;">
                            <input type="hidden" name="restaurant_id" value="<%= restaurantId %>">
                            <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="action" value="remove">
                            <button class="remove-btn">Remove</button>
                        </form>
                    </div>
                </div>
            <% 
                    } 
            %>
                <div class="total-amount">
                    <div class="total-label">Total Amount:</div>
                    <div class="total-value">₹<%= cart.getTotalPrice() %></div>
                </div>
            <% 
                } else { 
            %>
                <div class="empty-cart">Your cart is empty.</div>
            <% } %>
        </div>

        <div class="cart-footer">
            <!-- Add More Items -->
            <form action="Menu" method="get" style="display:inline;">
                <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                <button class="cart-btn add-more-btn" type="submit">Add More Items</button>
            </form>

            <!-- Proceed to Checkout -->
            <form action="Checkout.jsp" method="post" style="display:inline;">
                <button class="cart-btn checkout-btn" type="submit">Proceed to Checkout</button>
            </form>
        </div>
    </div>

    <script>
        function updateTotal() {
            let total = 0.00;
            document.querySelectorAll('.cart-item').forEach(item => {
                const priceStr = item.querySelector('.item-price').textContent;
                const price = parseFloat(priceStr.replace(/[^\d.]/g, ''));
                const quantity = parseInt(item.querySelector('.quantity-display').textContent);
                total += price * quantity;
            });
            const totalDisplay = document.querySelector('.total-value');
            if (totalDisplay) {
                totalDisplay.textContent = '₹' + total.toFixed(2);
            }
        }

        document.addEventListener('DOMContentLoaded', updateTotal);
    </script>
</body>
</html>
