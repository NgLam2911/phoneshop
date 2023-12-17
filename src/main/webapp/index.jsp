<%@page language="java" import="nhom9.phoneshop.model.bean.ProductBean"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if (session.getAttribute("user") == null){
	session.setAttribute("user", request.getAttribute("user"));
	}
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file="common/header.jsp" %>

    <% if (request.getAttribute("pdList") == null){%>
        <jsp:include page="/customerServlet" >
            <jsp:param name="action" value="GetProduct"/>
        </jsp:include>
    <% }%>

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
		ArrayList<ProductBean> pdList = (ArrayList<ProductBean>)request.getAttribute("pdList");%>
<div class="row">
<% for (int i = 0; i < pdList.size(); i++) { %>
    <div class="col-md-4 mb-4">
        <div class="card h-100">
            <img class="card-img-top" src="img/apple-iphone-15-pro-max-1.jpg">
            <div class="card-body">
                <h5 class="card-title"><%= pdList.get(i).getProductName() %></h5>
                <p class="card-text">
                    Price: <%= pdList.get(i).getPrice() %><br>
                    Manufacturer: <%= pdList.get(i).getManufacturerName() %><br>
                    CPU: <%= pdList.get(i).getCPU() %><br>
                    RAM: <%= pdList.get(i).getRAM() %><br>
                    Display Size: <%= pdList.get(i).getDisplaySize() %><br>
                    OS: <%= pdList.get(i).getOS() %><br>
                    Battery: <%= pdList.get(i).getBattery() %><br>
                    Capacity: <%= pdList.get(i).getCapacity() %>
                </p>
            </div>
            <% if (session.getAttribute("user") != null){%>
                <div class="card-footer">
                    <a href="<%=request.getContextPath()%>/customerServlet?action=AddProductToCart&id=<%= pdList.get(i).getProductID() %>" class="btn btn-primary">Thêm vào giỏ hàng</a>
                </div>
            <% }%>
        </div>
    </div>
    <% if ((i + 1) % 3 == 0) { %>
        </div><div class="row">
    <% } %>
<% } %>
</div>
<%@ include file="common/footer.jsp" %>
</body>
</html>