<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<form action="./UploadBookServlet" method="post" enctype="multipart/form-data" >
		<div align="center">
				<table align="center" border="0" >
					<tr>
						<td>Book Title</td>
						<td> <input type="text" name="title"  /> </td>
					</tr>	
					<tr>
						<td>Author</td>
						<td> <input type="text" name="author"  /> </td>
					</tr>	
					<tr>
						<td>Genre</td>
						<td> <select name="genre" >
								<option value="0">Select</option>
								<option value="Story">Story</option>
								<option value="Article">Article </option>
								<option value="Java">Java</option>
								<option value="Journals">Journals</option>
								<option value="Comics">Comics</option> 
						
							</select>
						 </td>
					</tr>	
					<tr>
						<td>Publisher Name</td>
						<td><input type="text" name="publisher" /></td>
					</tr>					
					<tr>
						<td>Brief Description</td>
						<td><textarea name="description" ></textarea></td>
					</tr>	
					
					<tr>
						<td>Upload PDF</td>
						<td><input type="file" name="bookfile"></input> </td>
					</tr>						
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Upload Book" />
							
						</td>
					</tr>						
						
				</table>
		
		
		</div>
</form>		
<FOOTER>
		<%@include file="./footer.jsp" %>
</FOOTER>
	</body>
</html>
