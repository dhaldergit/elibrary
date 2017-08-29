<%@page import="java.util.Iterator"%>
<%@page import="com.fiem.elibrary.model.Books"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplication Name</title>
</head>
<body>
	<%@include file="./menu.jsp" %>	
	
	<div align="center">
	
			<%
			
				ArrayList<Books> booksList = (ArrayList<Books>) request.getAttribute("BOOKS_LIST");
	
				Iterator<Books> iter = booksList.iterator();
			
			%>
	
<form action="">	
			<table width="100%">
					<caption >Books Available</caption>
				<thead>
					<tr style="background-color:brown;">
							<th  width="30%" >Title</th>
							<th>Author</th>
							<th>Genre</th>
							<th>Publisher</th>
					</tr>
					
					<%
					int i=0;
					if(booksList.size()>0){
						while(iter.hasNext()){
							
								Books books = iter.next();
					if(i%2==0){
					%>		
						<tr  >
						
						<%} else { %>
						<tr style="background-color:grey;">
						<%} %>
								
							<td width="30"><a href="./SearchBookServlet?id=<%=books.getId() %>>"> 
									<%=books.getTitle() %> 
								</a>
							</td>
							<td width="30%" align="center" > <%=books.getAuthor() %></td>
							<td width="10%" align="center"><%=books.getGenre() %></td> 
							<td width="30%" align="center"><%=books.getPublisher() %></td>
							
						</tr>
					
					
					
					
					<%	
						i++;
						}
					}else{	
					
					%>
					
						<tr align="center"> <td align="center"><font style="font-size: large;a"> <b> No books found</b></font></td> </tr>
				
					<%
					}
					%>
				</thead>
			
			</table>
</form>	
	</div>
	
	<%@include file="./footer.jsp" %>	
</body>
</html>