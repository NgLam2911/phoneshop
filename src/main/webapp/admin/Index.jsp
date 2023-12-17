<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.text.DecimalFormat"%>
<%
DecimalFormat df = new DecimalFormat("#0");
%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        h2 {
            color: #333;
        }
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 10px;
        }
        .button, #search-container {
            margin-bottom: 10px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #0056b3;
        }
        #search-container {
            display: flex;
            flex-direction: row;
            align-items: center;
        }
        #search-input {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-right: 10px;
        }
        #search-button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            text-align: center;
            outline: none;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: #fff;
            transition: background-color 0.3s;
        }
        #search-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <%
        session.setAttribute("user", request.getAttribute("user"));
    %>
    <h2>Admin Page</h2>
    <%
        if (session.getAttribute("user") != null) {
            String user = (String) request.getAttribute("user");
    %>
    <h2>Chào mừng quản trị viên <%= user %></h2><br><br>
    <div class="button-container">
        <a href="<%=request.getContextPath()%>/authServlet?action=AdminGetProduct" class="button">Xem sản phẩm</a>
        <a href="<%=request.getContextPath()%>/authServlet?action=AdminGetCustomer" class="button">Xem thông tin người dùng và hóa đơn</a>
        <a href="<%=request.getContextPath()%>/authServlet?action=AdminAddProduct" class="button">Thêm sản phẩm</a>
        <a href="<%=request.getContextPath()%>/index.jsp" class="button">Đăng xuất</a>
        <!-- New input field and button -->
        <div id="search-container">
            <form name="searchProductForm" id="searchProductForm" action="<%=request.getContextPath()%>/authServlet?action=SearchProduct" enctype="multipart/form-data" method="post" >
            <input type="text" id="txtSearch" name="txtSearch" placeholder="Nhập điện thoại cần tìm kiếm">
            <input type="submit" name="Register" id="Register" value="Tìm kiếm">
            </form> 
        </div>
    </div>
    <% } %>
</body>
</html>