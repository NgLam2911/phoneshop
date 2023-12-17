<%@page language="java" import="nhom9.phoneshop.model.bean.ProductBean"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
DecimalFormat df = new DecimalFormat("#0");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xem danh sách sản phẩm</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        img {
            max-width: 100px;
            max-height: 100px;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
        }
        .edit-button, .remove-button {
            padding: 6px 12px;
            text-decoration: none;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }
        .edit-button {
            background-color: #007bff;
        }
        .remove-button {
            background-color: #dc3545;
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
        <td>Màu sắc</td>
        <td>Số lượng</td>
        <%-- <td>Sửa</td> --%>
        <td>Thao tác</td>
	</tr>
	<%
		ArrayList<ProductBean> pdList = (ArrayList<ProductBean>)request.getAttribute("pdList");
		for (int i = 0; i < pdList.size(); i++) {
	%>
		<tr>
			<td><%= pdList.get(i).getProductName() %></td>
			<td><%= df.format(pdList.get(i).getPrice()) %> đ</td>
			<td><%= pdList.get(i).getManufacturerName() %></td>
			<td><%= pdList.get(i).getCPU() %></td>
            <td><%= pdList.get(i).getRAM() %> GB</td>
			<td><%= pdList.get(i).getDisplaySize() %></td>
			<td><%= pdList.get(i).getOS() %></td>
			<td><%= pdList.get(i).getBattery() %> mAh</td>
            <td><%= pdList.get(i).getCapacity() %> GB</td>
			<td><img src="<%= pdList.get(i).getImage() %>" alt="<%= pdList.get(i).getProductName() %>" ></td>
            <td><%= pdList.get(i).getColor() %></td>
            <td><%= pdList.get(i).getQuantity() %></td>
            <td class="action-buttons">
                    <a href="<%=request.getContextPath()%>/authServlet?action=EditProduct&id=<%= pdList.get(i).getProductID() %>"
                        class="edit-button">Sửa</a>
                    <a href="<%=request.getContextPath()%>/authServlet?action=RemoveProduct&id=<%= pdList.get(i).getProductID() %>"
                        class="remove-button">Xóa</a>
            </td>
		</tr>
	<% } %>
	</table>
    <a href="javascript:history.back()">Quay lại</a>
</body>
</html>