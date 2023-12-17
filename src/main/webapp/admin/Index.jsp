<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin page</title>
	</head>
<body>
<%
		session.setAttribute("user", request.getAttribute("user"));
	%>
		<h2>Admin Page</h2>
	<%
		if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
	%>
		<h2>Chào mừng quản trị viên</h2>
        <h2><%= user %></h2>
		<a href="<%=request.getContextPath()%>/authServlet?action=AdminGetProduct">Xem sản phẩm</a> <br>
		<a href="<%=request.getContextPath()%>/authServlet?action=AdminAddProduct">Thêm sản phẩm</a> <br>
		<a href="<%=request.getContextPath()%>/authServlet?action=AdminSearchProduct">Tìm kiếm sản phẩm (admin)</a> <br>
	<% } %>
</body>
</html>