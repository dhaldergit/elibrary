package com.fiem.elibrary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class WelcomeServlet
 */
@MultipartConfig( fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con=null;
	PreparedStatement statement=null;
	
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
        // gets values of text fields
    	 String title = request.getParameter("title");
    	 String author = request.getParameter("author");
    	 String genre = request.getParameter("genre");
    	 String publisher = request.getParameter("publisher");
    	 String description = request.getParameter("description");

    	 System.out.println(title);
    	 System.out.println(author);
    	 System.out.println(genre);
    	 
    	 InputStream inputStream = null; // input stream of the upload file
         
        String insertBookSql = "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE, PUBLISHER, DESCRIPTION) " +
        		" values (?,?,?,?,?)";

        
         
        String message = null;  // message will be sent back to client
         
        try {
            
            statement = con.prepareStatement(insertBookSql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, genre);
            statement.setString(4, publisher);
            statement.setString(5, description);
             
          
            // sends the statement to the database server
            int row = statement.executeUpdate();
            
            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("bookfile");
            
            System.out.println(filePart.getName());
//            InputStream fileContent = filePart.getInputStream();
            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                 
                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
                
                if (inputStream != null) {
                    // fetches input stream of the upload file for the blob column
                
                	String idSelect = "select id from books where title = '"+title+"'";
                	
                	Statement st = con.createStatement();
                	ResultSet rs = st.executeQuery(idSelect);
                	int bookid = 0;
                	if(rs.next()){
                		bookid = rs.getInt(1);
                	}
                	
                	System.out.println(bookid);
                	String inserPDFSql = "INSERT INTO PDF_BOOKS" +
                						" values (?,?,?)";
                	
                	statement = con.prepareStatement(inserPDFSql);
                	statement.setInt(1, bookid);
                	statement.setBlob(2, inputStream);
                	statement.setString(3, filePart.getContentType());
                	
                	
                	row = statement.executeUpdate();
                	
                	
                	System.out.println("added PDF now" + row);
                }
     
                
            }
            
            if (row > 0) {
                message = "Book is now uploaded successfully..";
            }
            
         // sets the message in request scope
            request.setAttribute("message", message);
             
            // forwards to the message page
            request.getRequestDispatcher("/uploadbook.jsp").forward(request, response);
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            // sets the message in request scope
            request.setAttribute("message", message);
            request.getRequestDispatcher("/uploadbook.jsp").forward(request, response);
            ex.printStackTrace();
        } 
    
		
	}

}
