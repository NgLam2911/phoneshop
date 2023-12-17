<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="path_to_your_icon" width="30" height="30" class="d-inline-block align-top" alt="">
            Your Website
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Đăng nhập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/register.jsp">Đăng ký</a>
                </li>
            </ul>
        </div>
    </nav>

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
</body>
</html>