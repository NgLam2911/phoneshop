<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%session.setAttribute("user", request.getAttribute("user"));%>
<%session.setAttribute("role", request.getAttribute("role"));%>
<!DOCTYPE html>
<html>
<head>
    <% if (session.getAttribute("role") == "admin"){%>
        <title>Trang admin</title>
    <% }else{ %>
        <title>Trang chủ</title>
    <% } %>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="common/header.jsp" %>
    <% if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
    %>
        <h2>Chào mừng </h2>
        <h2><%= user %></h2>
        <a href="<%=request.getContextPath()%>/authServlet?action=AdminGetProduct">Xem sản phẩm (admin)</a> <br>
        <a href="<%=request.getContextPath()%>/authServlet?action=GetProduct">Xem sản phẩm</a> <br>
        <br>
        <a href="<%=request.getContextPath()%>/customerServlet?action=GetCartItems">Xem gio hang</a> <br>
    <% } %>

	<%@ include file="common/footer.jsp" %>
</body>
</html>