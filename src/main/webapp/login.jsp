<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        a {
            text-decoration: none;
            color: #3498db;
        }

        a:hover {
            color: #207db5;
        }

        form {
            margin-top: 20px;
        }
    </style>
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