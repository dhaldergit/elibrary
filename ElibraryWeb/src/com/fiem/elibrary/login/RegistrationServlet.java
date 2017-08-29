package com.fiem.elibrary.login;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class RegistrationServlet
 * 
 * This is required
 */
@MultipartConfig( fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/studentdb";
    private String dbUser = "root";
    private String dbPass = "root";
     
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	 String userId = request.getParameter("userId");
    	 String password = request.getParameter("password");
    	 String name = request.getParameter("name");
    	 String phone = request.getParameter("phone");
    	 String address = request.getParameter("address");
         String gender = request.getParameter("gender");
         
        System.out.println(":"+phone+":");
        System.out.println(address);
        InputStream inputStream = null; // input stream of the upload file
         
        String sql = "INSERT INTO USER (user_id , password, displayname, phone_no, address, gender) values (?,?, ?, ?,?,?)";
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        
        System.out.println(filePart.getName());
//        InputStream fileContent = filePart.getInputStream();
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            
            sql = "INSERT INTO USER (user_id , password, displayname, phone_no, address, gender,photo) values (?,?, ?, ?,?,?,?)";
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setLong(4, Long.parseLong(phone));
            statement.setString(5, address);
            statement.setString(6, gender);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(7, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "User is registered succesfully. Please, log in now.";
            }
            
         // sets the message in request scope
            request.setAttribute("message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            // sets the message in request scope
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            ex.printStackTrace();
        }
    }

}
