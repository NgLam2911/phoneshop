<%@page language="java" import="model.bean.ProductBean"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xem danh sách sản phẩm</title>
    <style>
        /* Your existing styles here */
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
	<h3 align="center">Bảng sản phẩm</h3>
	<table border="1" width="100%">
	<tr>
		<td>Tên sản phẩm</td>
		<td>Giá sản phẩm</td>
        <td>Tên hãng</td>
		<td>CPU</td>
		<td>RAM</td>
        <td>Kích thước màn hình</td>
        <td>Hệ điều hành</td>
        <td>Dung lượng pin</td>
        <td>Dung lượng bộ nhớ</td>
        <td>Hình ảnh sản phẩm</td>
        <td>Sửa</td>
        <td>Xóa</td>
	</tr>
	<%
		ArrayList<ProductBean> productList = (ArrayList<ProductBean>)request.getAttribute("pdList");
		for (int i = 0; i < productList.size(); i++) {
	%>
		<tr>
			<td><%= productList.get(i).getProductName() %></td>
			<td><%= productList.get(i).getPrice() %></td>
			<td><%= productList.get(i).getManufacturerName() %></td>
			<td><%= productList.get(i).getCPU() %></td>
            <td><%= productList.get(i).getRAM() %></td>
			<td><%= productList.get(i).getDisplaySize() %></td>
			<td><%= productList.get(i).getOS() %></td>
			<td><%= productList.get(i).getBattery() %></td>
            <td><%= productList.get(i).getCapacity() %></td>
			<td><%= productList.get(i).getImage() %></td>
            <td><a href="<%=request.getContextPath()%>/editProduct?id=<%= productList.get(i).getProductID() %>">Sửa</a></td>
            <td><a href="<%=request.getContextPath()%>/removeProduct?id=<%= productList.get(i).getProductID() %>">Xóa</a></td>
		</tr>
	<% } %>
	</table>
</body>
</html>