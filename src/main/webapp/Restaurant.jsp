<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.tapfoods.Model.Restaurant" %>

<%
    // ✅ Redirect if user is not logged in
    String userEmail = (String) session.getAttribute("userEmail");
    String userName = (String) session.getAttribute("userName");

    if (userEmail == null) {
        response.sendRedirect("Sign_in.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Restaurant Page</title>
    <link rel="icon" href="assets/symbol.png"/>

    <!-- CSS -->
    <link rel="stylesheet" href="css/swiper-bundle.min.css">
    <link rel="stylesheet" href="css/style1.css?v=2" />
</head>

<body>
<header>
    <div class="image-container">
        <div class="gardient"></div>
        <img src="assets/header-back.png" alt="Header Background"/>
    </div>

    <div class="header-body-container">
        <nav>
            <div class="link-container">
                <ul>
                    <li><a href="Sign_in.jsp">Sign In</a></li>
                    <li><a href="Sign_up.jsp">Sign Up</a></li>
                    <% if (userName != null) { %>
                        <li class="welcome-msg">Welcome, <%= userName %></li>
                        <li><img class="account-icon" src="assets/user_profile_icon.png" alt="User Icon"/></li>
                    <% } %>
                </ul>
            </div>
        </nav>

        <div class="sy-txt-container">
            <div class="symbol-container">
                <img class="logo-symbol" src="assets/symbol.png" alt="Tap Foods Logo"/>
            </div>
            <div class="text-container">
                <div class="text-content-container">
                    <p>TAP FOODS</p>
                    <h1>TAP IT. TASTE IT. LOVE IT.</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Category Description -->
<div class="description">
    <h2>What's on your mind?</h2>
</div>

<!-- Category Slider -->
<div class="container-body">
    <div class="slide-container swiper">
        <div class="slide-content">
            <div class="card-wrapper swiper-wrapper">

                <%-- Static Cards: Ideally dynamic later --%>
                <% String[] images = {"Dosa.jpg", "idli.jpg", "medu-vada.jpg", "biryani.jpg", "coffee.jpg", "sandwhich.jpg", "cake.jpg", "salad.jpg"};
                   String[] names = {"Dosa", "Idli", "Vada", "Biryani", "Coffee", "Sandwich", "Cake", "Salad"};

                   for (int i = 0; i < images.length; i++) { %>
                <div class="card swiper-slide">
                    <div class="image-content">
                        <span class="overlay"></span>
                        <div class="card-image">
                            <img src="assets/slider-images/<%= images[i] %>" alt="<%= names[i] %>" class="card-img"/>
                            <p class="description"><%= names[i] %></p>
                        </div>
                    </div>
                </div>
                <% } %>

            </div>
        </div>
        <div class="swiper-button-next swiper-navbtn"></div>
        <div class="swiper-button-prev swiper-navbtn"></div>
        <div class="swiper-pagination"></div>
    </div>
</div>

<!-- Restaurant List -->
<div class="description">
    <h2>Top Food Delivery Restaurants For You</h2>
</div>

<section class="restaurant-body">
    <div class="restaurant-container">
        <%
            List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant r : restaurants) {
        %>
        <a href="Menu?restaurantId=<%= r.getRestaurantId() %>" class="restaurant-link">
            <div class="restaurant-card">
                <div class="card-image-container">
                    <img src="<%= r.getImagePath() %>" alt="Restaurant Image"/>
                    <span class="tag">Promoted</span>
                </div>
                <div class="restaurant-details">
                    <div class="top-row">
                        <h3 class="name"><%= r.getName() %></h3>
                        <div class="rating"><%= r.getRating() %>★</div>
                    </div>
                    <p class="cuisine"><%= r.getCusineType() %></p>
                    <div class="info-row">
                        <span class="location"><%= r.getAddress() %></span>
                        <span class="distance"><%= r.getTime() %></span>
                    </div>
                </div>
            </div>
        </a>
        <%  }
            } else { %>
            <p>No restaurants available.</p>
        <% } %>
    </div>
</section>

<!-- Scripts -->
<script src="js/swiper-bundle.min.js"></script>
<script src="js/script.js"></script>

</body>
</html>
