<%@page import="java.util.Iterator"%>
<%@page import="com.fiem.elibrary.model.Books"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplication Name</title>
</head>
<body>
	<header>
		<%@include file="./menu.jsp"%>
	</header>
	<div align="center">

		<%
			ArrayList<Books> booksList = (ArrayList<Books>) request
					.getAttribute("BOOKS_LIST");

			Iterator<Books> iter = booksList.iterator();
		%>

		<form action="">
			<table width="100%">
				<caption>Books Available</caption>
				<thead>
					<tr style="background-color: brown;">
						<th width="30%">Title</th>
						<th>Author</th>
						<th>Genre</th>
						<th>Publisher</th>
					</tr>

					<%
						int i = 0;
						if (booksList.size() > 0) {
							while (iter.hasNext()) {

								Books books = iter.next();
								if (i % 2 == 0) {
					%>
					<tr>

						<%
							} else {
						%>
					
					<tr style="background-color: grey;">
						<%
							}
						%>

						<td width="30"><a
							href="./SearchBookServlet?id=<%=books.getId()%>>"> <%=books.getTitle()%>
						</a></td>
						<td width="30%" align="center"><%=books.getAuthor()%></td>
						<td width="10%" align="center"><%=books.getGenre()%></td>
						<td width="30%" align="center"><%=books.getPublisher()%></td>

					</tr>




					<%
						i++;
							}
						} else {
					%>

					<tr align="center">
						<td align="center"><font style="font-size: large;"> <b>
									No books found</b></font></td>
					</tr>

					<%
						}
					%>
				</thead>

			</table>
		</form>
	</div>
	<footer>
		<%@include file="./footer.jsp"%>
	</footer>
</body>
</html>