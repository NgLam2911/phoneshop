<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">

</head>

<body>
    <%
        session.setAttribute("login", request.getAttribute("login"));
    %>
    <%
		if (session.getAttribute("login") != null ) {
        String login = (String)request.getAttribute("login");
	%>
        <h2><%= login %></h2>
    <% } %>
    <h2>Đăng nhập</h2>
    <form name="loginForm" id="loginForm" action="auth/LoginServlet" method="post">
        <table id="loginTable">
            <tr>
                <td>Tên tài khoản</td>
                <td><label for="txtusername"></label><input type="text" name="txtusername" id="txtusername" value="">
                </td>
            </tr>
            <tr>
                <td>Mật khẩu</td>
                <td><label for="txtpassword"></label><input type="password" name="txtpassword" id="txtpassword"
                        value=""></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="Login" id="Login" value="Đăng nhập"></td>
            </tr>
        </table>
    </form>
</body>

</html>