<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
	<%
		if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
	%>
		<h2>Chào mừng </h2>
        <h2><%= user %></h2>
		<a href="<%=request.getContextPath()%>/SearchProduct">Tìm kiếm điện thoại</a> <br>
		<a href="<%=request.getContextPath()%>/GetProduct">Xem điện thoại</a> <br>
	<% } %>
</body>
</html>