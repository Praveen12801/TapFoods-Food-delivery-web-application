<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Enumeration" %>
<%
    Enumeration<String> attrNames = session.getAttributeNames();
    while(attrNames.hasMoreElements()) {
        String attr = attrNames.nextElement();
        System.out.println("SESSION DEBUG → " + attr + ": " + session.getAttribute(attr));
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Order Confirmed</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <!-- CSS -->
    <link rel="stylesheet" href="css/menu.css">

    <!-- Lottie Animation -->
    <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
</head>

<body style="background-color: #f5f5f5;">
    <!-- ✅ Header -->
    <header>
        <nav id="desktop-nav">
            <div class="logo" role="banner">
                <img src="assets/symbol.png" alt="TAP FOODS logo" />
                <p>TAP FOODS</p>
            </div>
            <div>
                <ul class="nav-links" role="navigation" aria-label="Primary navigation">
                    <li><a href="Restaurant.jsp">Home</a></li>
                    <li><a href="Sign_up.jsp">Sign Up</a></li>
                    <li><a href="#Help">Help</a></li>
                    <li><a href="#Contact">Contact</a></li>
                </ul>    
            </div>
        </nav>
    </header>

    <!-- ✅ Order Confirmation Animation -->
    <section class="anime-container" role="main" aria-label="Order confirmed message">
        <div class="confirm-container">
            <dotlottie-player
                src="https://lottie.host/87a66d82-26ce-458b-bb90-de38120d9261/d4hxsPuSU5.lottie"
                background="transparent"
                speed="1"
                style="width: 280px; height: 280px;"
                loop
                autoplay>
            </dotlottie-player>
            <h1 class="confirm-title">Your order is confirmed!</h1>
            <p class="subtitle">Your delicious food is on the way 🛵</p>
        </div>
    </section>    
</body>
</html>
