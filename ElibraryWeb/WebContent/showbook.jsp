<%@page import="java.util.Iterator"%>
<%@page import="com.fiem.elibrary.model.Books"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

	function openImage(val,id){
		
		
		 
		var url = "./PDFServlet?open="+val+"&id="+id;
		
		window.open(url,200,200,false);
		
		
	}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Book</title>
</head>
<body>
<%@include file="./menu.jsp" %>
<br>
		
		<% 
			String displayMessage = (String) request.getAttribute("message");
			
		if(displayMessage!=null){
		
		%>
		<b>  <%=displayMessage %> </b>

	
		<% } %>
		
			<%
			
				ArrayList<Books> booksList = (ArrayList<Books>) request.getAttribute("BOOKS_LIST");
	
			
				Books book = booksList.get(0);
				
			
			%>
			
<form action="" method="post" enctype="multipart/form-data" >
		<div align="center">
		<input type="hidden" name="bookId" value="<%=book.getId() %>" />
				<table align="center" border="0" >
					
					<tr>
						<td>Book Title</td>
						<td> <%= book.getTitle() %> </td>
					</tr>	
					<tr>
						<td>Author</td>
						<td> <%= book.getAuthor() %> </td>
					</tr>	
					<tr>
						<td>Genre</td>
						<td> <%= book.getGenre() %> </td>
					</tr>	
					<tr>
						<td>Publisher Name</td>
						<td> <%= book.getPublisher() %> </td>
					</tr>					
					<tr>
						<td>Brief Description</td>
						<td> <%= book.getDescription() %> </td>
					</tr>	
					
					<tr>
						<td><input type="button" value="View PDF" onclick="openImage('inline',<%=book.getId() %>);"></input> </td>
						<td><input type="button" value="Download PDF" onclick="openImage('saveas',<%=book.getId() %>)"></input> </td>
					</tr>						
						
				</table>
		
		
		</div>
</form>		

<%@include file="./footer.jsp" %>
</body>
</html>