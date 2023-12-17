<%@page language="java" import="nhom9.phoneshop.model.bean.ProductBean"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.setAttribute("user", request.getAttribute("user"));%>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="common/header.jsp" %>

    <% if (request.getAttribute("pdList") == null){%>
        <jsp:include page="/authServlet" >
            <jsp:param name="action" value="GetProduct"/>
        </jsp:include>
    <% }%>

	<% out.println(request.getAttribute("check"));%>
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
        <% if (session.getAttribute("user") != null){%>
            <td>Thêm vào giỏ hàng</td>
        <% }%>   

	</tr>
	<%
		ArrayList<ProductBean> pdList = (ArrayList<ProductBean>)request.getAttribute("pdList");
		for (int i = 0; i < pdList.size(); i++) {
	%>
		<tr>
			<td><%= pdList.get(i).getProductName() %></td>
			<td><%= pdList.get(i).getPrice() %></td>
			<td><%= pdList.get(i).getManufacturerName() %></td>
			<td><%= pdList.get(i).getCPU() %></td>
            <td><%= pdList.get(i).getRAM() %></td>
			<td><%= pdList.get(i).getDisplaySize() %></td>
			<td><%= pdList.get(i).getOS() %></td>
			<td><%= pdList.get(i).getBattery() %></td>
            <td><%= pdList.get(i).getCapacity() %></td>
			<td><%= pdList.get(i).getImage() %></td>
            <% if (session.getAttribute("user") != null){%>
            <td><a href="<%=request.getContextPath()%>/customerServlet?action=AddProductToCart&id=<%= pdList.get(i).getProductID() %>">Thêm vào giỏ hàng</a></td>
            <% }%>   
		</tr>
	<% } %>
	</table>

	<%@ include file="common/footer.jsp" %>
</body>
</html>