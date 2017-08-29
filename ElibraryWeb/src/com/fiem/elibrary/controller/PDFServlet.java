package com.fiem.elibrary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiem.elibrary.model.Books;

/**
 * Servlet implementation class WelcomeServlet
 */
public class PDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request, response);
	
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String returnPage = "/home.jsp";
		String bookid = request.getParameter("id");
		String selectQuery = "SELECT * FROM PDF_BOOKS";
		if(bookid!=null){
			selectQuery += " WHERE PDF_ID='"+ bookid +"'";
		}
		
		String openorDownload = request.getParameter("open");
		if(openorDownload!=null){
			returnPage = "/openordownload.jsp";
			request.setAttribute("viewordownload", openorDownload);
		}
	
	String message = "";
	try {
			ResultSet rs = statement.executeQuery(selectQuery);
			ArrayList<Books> booksList = new ArrayList<Books>();
			if(rs.next()){

				byte[] pdfContent = (byte[])rs.getBytes(2);
			
				request.setAttribute("pdfContent", pdfContent);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}catch (Exception e) {
		
			e.printStackTrace();
		}
	

	
		
		RequestDispatcher rd = request.getRequestDispatcher(returnPage);
		rd.forward(request, response);
	
	

	}

}
