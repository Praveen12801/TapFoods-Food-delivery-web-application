package com.tapfoods.Servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tapfoods.Model.User;
import com.tapfoods.Utility.DBConnection;

@WebServlet("/callingSignIn")
public class SigninServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private int count = 3;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inputEmail = req.getParameter("email");
        String inputPassword = req.getParameter("password");

        boolean isAuthenticated = false;
        String name = null;
        int userId = 0;
        String role = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, inputEmail);
            stmt.setString(2, inputPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isAuthenticated = true;
                    name = rs.getString("name");
                    userId = rs.getInt("user_id");
                    role = rs.getString("role");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "Database error occurred. Try again.");
            req.getRequestDispatcher("Sign_in.jsp").forward(req, resp);
            return;
        }

        if (isAuthenticated) {
            HttpSession session = req.getSession();

            User user = new User();
            user.setName(name);
            user.setEmail(inputEmail);
            user.setUserId(userId); // ✅ FIXED: Using extracted value

            session.setAttribute("loggedInUser", user);
            session.setAttribute("userName", name);
            session.setAttribute("userEmail", inputEmail);
            session.setAttribute("userId", userId);

            resp.sendRedirect("Restaurant"); // ✅ Forward to servlet that loads restaurant list

        } else if (count > 0) {
            req.setAttribute("errorMsg", "Wrong email or password. " + count + " attempts left.");
            count--;
            req.getRequestDispatcher("Sign_in.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMsg", "Too many failed attempts. Please contact admin.");
            req.getRequestDispatcher("Sign_in.jsp").forward(req, resp);
        }
    }
}
