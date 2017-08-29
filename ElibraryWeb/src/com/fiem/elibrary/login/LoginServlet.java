package com.fiem.elibrary.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	
	Connection con=null;
	Statement statement=null;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// STEP 1 - REGISTER THE DRIVER
		
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				
				// STEP 2 - CREATE THE CONNECTION
					
					String url= config.getServletContext().getInitParameter("DB_URL");
					String user= config.getServletContext().getInitParameter("DB_USER");
					String password= config.getServletContext().getInitParameter("DB_PASSWORD");
					
					System.out.println("Url : " + url + " user " + user + " Pass:" + password); ;
					try {
						 con = DriverManager.getConnection(url, user, password);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				// STEP 3 - CREATE THE STATEMENT
					
					try {
						statement = con.createStatement();
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
	}
	public void destroy() {
			try {
				con.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userName");
		String password = request.getParameter("password");
		
		
		String selectSql = "SELECT * FROM USER WHERE USER_ID = '"+userid+ "'";
		
		ResultSet rs;
		try {
			rs = statement.executeQuery(selectSql);
			
			String message = "";
			
			if(rs.next()){
				
				String passwordInDb = rs.getString("PASSWORD");
				
				
				
				if(password!=null && password.equals(passwordInDb)){
					
					String userDisplayName = rs.getString("DISPLAYNAME");
					message = "Welcome "+ userDisplayName;
				
					
					request.setAttribute("message", message);
					RequestDispatcher rd = request.getRequestDispatcher("/WelcomeServlet");
					rd.forward(request, response);
					
				}else{
					message = "Password is incorrect. Please try again.";
					request.setAttribute("message", message);
					
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				}
				
			}else{
				message = "User ID does not exist . Please, <a href='register.jsp'>register</a> here.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
