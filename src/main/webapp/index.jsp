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
	@@ -34,6 +23,7 @@
        <br>
        <a href="<%=request.getContextPath()%>/customerServlet?action=GetCartItems">Xem gio hang</a> <br>
    <% } %>
	<%@ include file="common/footer.html" %>
</body>
</html>