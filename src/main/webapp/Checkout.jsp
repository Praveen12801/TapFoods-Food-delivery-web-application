<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // 🔐 Check login
    String userEmail = (String) session.getAttribute("userEmail");
    if (userEmail == null) {
        response.sendRedirect("Sign_in.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Check Out Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- CSS -->
    <link rel="stylesheet" href="css/checkout.css" />
    <link rel="stylesheet" href="mediaquries.css" />
</head>
<body>
    <!-- ✅ Header -->
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

    <!-- ✅ Checkout Form -->
    <section class="checkout-section" role="main" aria-label="Checkout Form">
        <div class="checkout-container">
            <h2>Checkout</h2>
            <form action="checkout" id="checkoutForm" method="post">

                <label for="address">Delivery Address</label>
                <textarea id="address" name="address" placeholder="Enter your delivery address" required></textarea>

                <label for="paymentMode">Payment Mode</label>
				<select name="paymentMode" required>
				  <option value="" disabled selected>Select Payment Mode</option>
				  <option value="CASH_ON_DELIVERY">Cash on Delivery</option>
				  <option value="UPI">UPI</option>
				  <option value="CARD">Card</option>
				</select>


                <button type="submit">Place Order</button>
            </form>
            <div class="message" id="message"></div>
        </div>
    </section>

    <!-- Optional JS -->
    <script src="js/checkout.js"></script>
</body>
</html>
