<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Our near by finder</title>
</head>
<body>
<%
Finder c = new Finder(request.getParameter("PARAMS"));

if (request.getParameter("OPCODE").equals("DATA")){
	out.print(c.getDATA());
}else if (request.getParameter("OPCODE").equals("GETQUOTE")){
	out.print(c.getDATA());
}
%>
</body>
</html>