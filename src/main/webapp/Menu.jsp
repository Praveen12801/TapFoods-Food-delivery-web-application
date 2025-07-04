<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.Model.Restaurant, com.tapfoods.Model.Menu" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Menu</title>
  <link rel="stylesheet" href="css/menu.css" />
  <link rel="stylesheet" href="mediaquries.css" />
</head>
<body>

  <!-- Header -->
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

  <!-- Restaurant Info -->
  <%
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
    String status = Boolean.parseBoolean(restaurant.getIsActive()) ? "YES" : "NO";
  %>

  <section class="content-container" role="main">
    <div class="info-container">
      <h2>Restaurant Info</h2>
      <div class="content-row">
        <div class="image-container">
          <img src="<%= restaurant.getImagePath() %>" alt="Restaurant image" />
          <div class="rating-tag"><%= restaurant.getRating() %></div>
        </div>
        <div class="details-container">
          <h3><%= restaurant.getName() %></h3>
          <p><%= restaurant.getCusineType() %></p>
          <p><%= restaurant.getAddress() %></p>
          <p><%= restaurant.getTime() %></p>
          <p>Status: <%= status %></p>
        </div>
      </div>
    </div>
  </section>

  <!-- Menu Items -->
  <section class="menu-container">
    <%
      if (menuList != null && !menuList.isEmpty()) {
        for (Menu item : menuList) {
    %>
      <div class="menu-content" data-item="<%= item.getItemName() %>">
        <div class="menu-info-container">
          <div class="icon-wrapper" title="<%= item.getIsVeg() ? "Vegetarian" : "Non-Vegetarian" %>">
            <span class="<%= item.getIsVeg() ? "veg-icon" : "non-veg-icon" %>" aria-label="<%= item.getIsVeg() ? "Vegetarian" : "Non-Vegetarian" %>"></span>
          </div>
          <h2 class="title"><%= item.getItemName() %></h2>
          <p class="price">₹ <%= item.getPrice() %></p>
          <p class="description"><%= item.getDescription() %></p>
        </div>

        <!-- Cart Form -->
		<form action="cart" method="post" class="images-container">
		  <img src="<%= item.getImagePath() %>" alt="<%= item.getItemName() %>" />
		  <input type="hidden" name="action" value="add" />
		  <input type="hidden" name="menuId" value="<%= item.getMenuId() %>" />
		  <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>" /> <!-- ✅ FIXED -->
		  <input type="hidden" name="quantity" class="quantity-input" value="1" />
		
		  <div class="quantity-controls">
		    <button type="button" class="decrease-button" aria-label="Decrease quantity">-</button>
		    <span class="quantity">1</span>
		    <button type="button" class="increase-button" aria-label="Increase quantity">+</button>
		  </div>
		
		  <button type="submit" class="add-button">Add to Cart</button>
		</form>

      </div>
    <%
        }
      } else {
    %>
      <p>No menu items available.</p>
    <%
      }
    %>
  </section>

  <!-- Quantity Sync Script -->
  <script>
    document.addEventListener('DOMContentLoaded', () => {
      const controls = document.querySelectorAll('.quantity-controls');

      controls.forEach(control => {
        const increaseBtn = control.querySelector('.increase-button');
        const decreaseBtn = control.querySelector('.decrease-button');
        const quantitySpan = control.querySelector('.quantity');
        const hiddenInput = control.parentElement.querySelector('.quantity-input');

        increaseBtn.addEventListener('click', () => {
          let current = parseInt(quantitySpan.textContent);
          quantitySpan.textContent = current + 1;
          hiddenInput.value = current + 1;
        });

        decreaseBtn.addEventListener('click', () => {
          let current = parseInt(quantitySpan.textContent);
          if (current > 1) {
            quantitySpan.textContent = current - 1;
            hiddenInput.value = current - 1;
          }
        });
      });
    });
  </script>
</body>
</html>
