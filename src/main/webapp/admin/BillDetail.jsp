<%@page language="java" import="nhom9.phoneshop.model.bean.BillBean"%>
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
		<td>Tên người dùng</td>
		<td>Tên tài khoản</td>
        <td>Email</td>
		<td>Số điện thoại</td>
		<td>Địa chỉ</td>
        <td>Thao tác</td>
	</tr>
	<%
		BillBean bb = (BillBean)request.getAttribute("bb");
		for (int i = 0; i < bb.size(); i++) {
	%>
		<tr>
			<td><%= bb.get(i).getCustomerName() %></td>
			<td><%= bb.get(i).getUsername() %></td>
			<td><%= bb.get(i).getEmail() %></td>
			<td><%= bb.get(i).getPhoneNumber() %></td>
            <td><%= bb.get(i).getAddress() %></td>
            <td class="action-buttons">
                    <a href="<%=request.getContextPath()%>/authServlet?action=GetBill&id=<%= bb.get(i).getCustomerID() %>"
                        class="edit-button">Xem hóa đơn</a>
            </td>
		</tr>
	<% } %>
        <a href="javascript:history.back()">Quay lại</a>
	</table>
</body>
</html>