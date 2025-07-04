<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="mediaquries.css">
    <script src="js/cart.js"></script>
</head>
<body style="background: url('assets/back.jpg'); background-size: cover; background-position: center;">

    <header>
        <nav id="desktop-nav">
            <div class="logo" role="banner">
                <img src="assets/symbol.png" alt="TAP FOODS logo" />
                <p>TAP FOODS</p>
            </div>
            <div>
                <ul class="nav-links" role="navigation" aria-label="Primary navigation">
                    <li><a href="Restaurant">Restaurant</a></li>
                    <li><a href="Sign_in.jsp">Sign In</a></li>
                    <li><a href="#Help">Help</a></li>
                    <li><a href="#Contact">Contact</a></li>
                </ul>    
            </div>
        </nav>
    </header>

    <section id="full-section">
        <div class="box-container">   
            <div class="signup__pic-conatiner">
                <img src="assets/Logo.png" alt="logo image">
            </div>

            <div class="signup-form-container">
                <form class="forms" action="callingSignup" method="post">
                    <h1>Sign Up</h1>

                    <%-- Display success or error messages --%>
                    <%
                        String successMsg = (String) request.getAttribute("successMsg");
                        String errorMsg = (String) request.getAttribute("errorMsg");
                        if (successMsg != null) {
                    %>
                        <p class="success-msg"><%= successMsg %></p>
                    <% } else if (errorMsg != null) { %>
                        <p class="error-msg"><%= errorMsg %></p>
                    <% } %>

                    <div class="input-box">
                        <input type="text" class="input-field" placeholder="Enter your name" name="name" required />
                    </div>

                    <div class="input-box">
                        <input type="text" class="input-field" placeholder="Enter your username" name="username" required />
                    </div>

                    <div class="input-box">
                        <input type="password" class="input-field" placeholder="Enter password" name="password" required />
                    </div>

                    <div class="input-box">
                        <input type="email" class="input-field" placeholder="Enter email ID" name="email" required />
                    </div>

                    <div class="input-box">
                        <input type="text" class="input-field" placeholder="Enter phone number" name="phone" required />
                    </div>

                    <div class="input-box">
                        <select required name="role">
                            <option value="" disabled selected>Select Role</option>
                            <option value="Customer">Customer</option>
                            <option value="Admin">Admin</option>
                        </select>
                    </div>

                    <button type="submit">Register</button>

                    <div class="text">
                        <h5>Already have an account? <a class="link" href="Sign_in.jsp">Sign in</a></h5>
                    </div>
                </form>
            </div>
        </div>     
    </section>

</body>
</html>
