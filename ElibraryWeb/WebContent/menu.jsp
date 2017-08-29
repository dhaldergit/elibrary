<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/menu.css"  />

</head>
<body>

<div style="background-color: blue;vertical-align: middle; " align="center" >
			<div style="width: 70%">
			<h1 ><font color="white"> Halder Coaching Institute&copy;</font></h1>
			</div>
	
	</div>
<hr>
 <div class="dropdown">
  <button class="dropbtn">Browse</button>
  <div class="dropdown-content">
    <a href="./SearchBookServlet">Any Books</a>
    <a href="./SearchBookServlet?type=Story" >Story Books</a>
    <a href="./SearchBookServlet?type=Article">Articles</a>
    <a href="./SearchBookServlet?type=Java">Java Books</a>
    <a href="./SearchBookServlet?type=Journals">Journals</a>
    <a href="./SearchBookServlet?type=Comics">Comics</a>
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">Adminstration</button>
  <div class="dropdown-content">
    <a href="./uploadbook.jsp">Upload Book</a>
   
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">Manage Cab </button>
  <div class="dropdown-content">
    <a href="#">New Booking</a>
    <a href="#">Revise Booking</a>
    <a href="#">Complete Booking</a>
  </div>
</div>
<div class="dropdown">
  <button class="dropbtn">Manage Students</button>
  <div class="dropdown-content">
    <a href="./addStudent.jsp">Add New Student</a>
    <a href="./DisplayServlet">Display List</a>
  </div>
</div>
<br>


<br>
</body>
</html>