<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Screen</title>
</head>
<body>
<div style="background-color: blue;vertical-align: middle; " align="center" >
			<div style="width: 70%">
			<h1 ><font color="white"> Halder Coaching Institute&copy;</font></h1>
			</div>
	
	</div>
<hr>
<form action="RegistrationServlet" method="post" enctype="multipart/form-data">
		<div align="right">
		Already Registered?&nbsp;&nbsp; <a href="./index.jsp" >login here</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;	
		
		</div>
		<br>
		

		<div align="center">
				<table align="center" border="0" >
					<tr>
						<td>User Id</td>
						<td> <input type="text" name="userId"  /> </td>
					</tr>	
					<tr>
						<td>Password</td>
						<td> <input type="text" name="password"  /> </td>
					</tr>	
					<tr>
						<td>Display Name</td>
						<td> <input type="text" name="name"  /> </td>
					</tr>	
					<tr> 
						<td>Phone No</td>
						<td><input type="text" name="phone" /></td>
					</tr>					
					<tr>
						<td>Address</td>
						<td><textarea name="address" ></textarea></td>
					</tr>	
					<tr>
						<td>Gender</td>
						<td>
								<input type="radio" name="gender" value="M"/> Male 
								<input type="radio" name="gender" value="F" /> Female   
							
						</td>
					</tr>	
					
					<tr>
						<td>Photo</td>
						<td><input type="file" name="file"></input> </td>
					</tr>						
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Register" />
							
						</td>
					</tr>						
						
				</table>
		
		
		</div>
</form>		
	<footer>
		<%@include file="./footer.jsp" %>
	</footer>
</body>
</html>
