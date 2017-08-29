<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eLibrary Store Application</title>
</head>
<body>
<div style="background-color: blue;vertical-align: middle; " align="center" >
			<div style="width: 70%">
			<h1 ><font color="white"> Halder Coaching Institute&copy;</font></h1>
			</div>
	
	</div>
<hr>

		
		<% 
			String displayMessage = (String) request.getAttribute("message");
			
		if(displayMessage!=null){
		
		%>
		<b>  <%=displayMessage %> </b>

	
		<% } %>	
		

<form action="LoginServlet" method="post" onsubmit="validate();" >
		<div align="right">
		New User?&nbsp;&nbsp; <a href="./register.jsp" >Register here</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;	
		
		</div>
		<br><br>

		<div align="center" style="height: 600">
				<table align="center" border="0" height="100%">
					<tr>
						<td> User Id</td>
						<td> <input type="text" name="userName"  /> </td>
					</tr>						
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" /></td>
					</tr>						
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Login" />
							
						</td>
					</tr>						
						
				</table>
		
		
		</div>
</form>	
<%@include file="./footer.jsp" %>	
</body>
</html>