<%@page language="java" import="nhom9.phoneshop.model.bean.BillBean"%>
<%@page language="java" import="nhom9.phoneshop.model.bean.CustomerBean"%>
<%@page language="java" import="nhom9.phoneshop.model.bean.CartItem"%>
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
	<%@ include file="common/header.jsp" %>
	<h3 align="center">Bảng sản phẩm</h3>
    <%
        CustomerBean cb = (CustomerBean)request.getAttribute("cb");
    %>
    <h2>Người tiêu dùng: <%= cb.getCustomerName() %></h2>
	<table border="1" width="100%">
	<tr>
		<td>Tên sản phẩm</td>
        <td>Số lượng</td>
	</tr>
	<%
		BillBean bb = (BillBean)request.getAttribute("bb");
        ArrayList<CartItem> ci = (ArrayList<CartItem>)request.getAttribute("ci");
		for (int i = 0; i < ci.size(); i++) {
	%>
		<tr>
			<td><%= ci.get(i).getProduct().getProductName() %></td>
			<td><%= ci.get(i).getAmount() %></td>
		</tr>
	<% } %>
	</table>
    <h2> Đơn giá: <%= df.format(bb.getBillTotalPrice()) %></h2>
    <a href="javascript:history.back()">Quay lại</a>
    <%@ include file="common/footer.jsp" %>
</body>
</html>