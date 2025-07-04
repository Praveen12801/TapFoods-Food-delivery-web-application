<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
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
                    <li><a href="Sign_up.jsp">Sign Up</a></li>
                    <li><a href="#Help">Help</a></li>
                    <li><a href="#Contact">Contact</a></li>
                </ul>    
            </div>
        </nav>
    </header>

    <section id="full-section">
        <div class="box-container">   
            <div class="signin__pic-conatiner">
                <img src="assets/Logo.png" alt="logo image"/>
            </div>

            <div class="form-container">
                <form class="forms" action="callingSignIn" method="post">
                    <h1>Sign In</h1>

                    <div class="errormsg" id="msgBox" style="color:red; font-weight:bold;">
                        <%
                            if (request.getAttribute("successMsg") != null) {
                                out.print("<span style='color:green'>" + request.getAttribute("successMsg") + "</span>");
                            } else if (request.getAttribute("errorMsg") != null) {
                                out.print(request.getAttribute("errorMsg"));
                            }
                        %>
                    </div>

                    <div class="input-box">
                        <label>Email</label>
                        <input type="email" class="input-field" placeholder="Enter email ID" name="email" required/>
                    </div>

                    <div class="input-box">
                        <label>Password</label>
                        <input type="password" class="input-field" placeholder="Enter password" name="password" required/>
                    </div>

                    <button type="submit">Submit</button>

                    <div class="text">
                        <h4>Don't have an account? <a class="link" href="Sign_up.jsp">Create account</a></h4>
                    </div>
                </form>
            </div>
        </div>     
    </section>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const emailInput = document.querySelector("input[name='email']");
            const msgBox = document.getElementById("msgBox");

            if (emailInput && msgBox) {
                emailInput.addEventListener("focus", function () {
                    msgBox.textContent = "";
                });
            }
        });
    </script>

</body>
</html>
