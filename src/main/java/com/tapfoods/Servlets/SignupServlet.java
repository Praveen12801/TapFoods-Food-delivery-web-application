package com.tapfoods.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tapfoods.Utility.DBConnection;

@WebServlet("/callingSignup")
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Collect user input from form
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");  // ⚠️ Consider hashing
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String role = req.getParameter("role");
		String address = req.getParameter("address");

		String INSERT_USER = "INSERT INTO user (name, username, password, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(INSERT_USER)) {

			pstmt.setString(1, name);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, address); // Add null if address not collected
			pstmt.setString(7, role);

			int i = pstmt.executeUpdate();
			PrintWriter out = resp.getWriter();

			if (i > 0) {
				req.setAttribute("successMsg", "Registration successful. Proceed to login.");
				req.getRequestDispatcher("Sign_in.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMsg", "Registration unsuccessful. Please try again.");
				req.getRequestDispatcher("Sign_up.jsp").forward(req, resp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "Something went wrong. Try again later.");
			req.getRequestDispatcher("Sign_up.jsp").forward(req, resp);
		}
	}
}
