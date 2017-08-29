<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Book Page</title>
</head>
<body>

	<div align="center" style="height: 300px;width: 500px;">

				<%							
							byte[] pdfcontent=(byte[])request.getAttribute("pdfContent");
							String viewordownload =  (String)request.getAttribute("viewordownload");
   						    response.setContentType("application/pdf");
							
   						    if(viewordownload!=null && "inline".equalsIgnoreCase(viewordownload)){
							 response.setHeader("Content-Disposition", "inline; filename=\"Test.pdf\"");
							}else{
								response.setHeader("Content-Disposition", "saveas; filename=\"Test.pdf\"");
							}
							
   						    response.getOutputStream().write(pdfcontent);
							
							%>
		
		</div>

</body>
</html>