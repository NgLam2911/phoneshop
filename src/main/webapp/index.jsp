<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a><br>
<%
		session.setAttribute("user", request.getAttribute("user"));
	%>
		<h2>Trang chủ</h2>
        <a href="<%=request.getContextPath()%>/login.jsp">Đăng nhập</a> <br>
        <a href="<%=request.getContextPath()%>/register.jsp">Đăng ký</a> <br>
	<%
		if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
	%>
		<h2>Chào mừng </h2>
        <h2><%= user %></h2>
		<a href="<%=request.getContextPath()%>/admin/ListProduct.jsp">Xem sản phẩm (admin)</a> <br>
		<a href="<%=request.getContextPath()%>/GetProductServlet">Xem sản phẩm</a> <br>
	<% } %>
</body>
</html>