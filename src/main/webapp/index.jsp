<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
	<%-- <%
		session.setAttribute("user", request.getAttribute("user"));
	%> --%>
		<h2>Trang chủ</h2>
        <a href="<%=request.getContextPath()%>/login.jsp">Đăng nhập</a> <br>
        <a href="<%=request.getContextPath()%>/register.jsp">Đăng ký</a> <br>
		<a href="<%=request.getContextPath()%>/listProduct.jsp">Xem danh sách điện thoại</a> <br>
		<a href="<%=request.getContextPath()%>/search.jsp">Tìm kiếm</a> <br>
	<%-- <%
		if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
	%>
		<h2>Chào mừng </h2>
        <h2><%= user %></h2>
		<a href="<%=request.getContextPath()%>/AdminGetProduct">Xem sản phẩm (admin)</a> <br>
		<a href="<%=request.getContextPath()%>/GetProduct">Xem sản phẩm</a> <br>
	<% } %> --%>

</body>
</html>