<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="common/header.html" %>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="path_to_your_icon" width="30" height="30" class="d-inline-block align-top" alt="">
            Your Website
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=Login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=Register">Register</a>
                </li>
            </ul>
        </div>
    </nav>

    <% if (session.getAttribute("user") != null ) {
        String user = (String)request.getAttribute("user");
    %>
        <h2>Chào mừng </h2>
        <h2><%= user %></h2>
        <a href="<%=request.getContextPath()%>/authServlet?action=SearchProduct">Tìm kiếm điện thoại</a> <br>
        <a href="<%=request.getContextPath()%>/authServlet?action=GetProduct">Xem điện thoại</a> <br>
    <% } %>
	<%@ include file="common/footer.html" %>
</body>
</html>