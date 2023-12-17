<%@page import="java.util.ArrayList"%>
<%@page import="nhom9.phoneshop.model.bean.CartBean"%>
<%@page import="nhom9.phoneshop.model.bean.CartItem"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat df = new DecimalFormat("#0");
%>
<%
CartBean cartBean = (CartBean) request.getAttribute("cartBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/all.min.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<title>E-Commerce Cart</title>
<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>

</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3"><h3>Tổng giá tiền: <%=df.format(cartBean.getTotalPrice())%> VND</h3> <a class="mx-3 btn btn-primary" href="<%=request.getContextPath()%>/customerServlet?action=Checkout">Check Out</a>
			<!-- <a class="mx-3 btn btn-primary" href="<%=request.getContextPath()%>/customerServlet?action=UpdateItemFromCart">Update</a> -->
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Tên sản phẩm</th>
					<th scope="col">Hãng sản xuất</th>
					<th scope="col">Đơn giá</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Xóa</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<CartItem> cartItems = cartBean.getItems();
				if (cartItems != null) {
					for (CartItem cartItem : cartItems) {
				%>
				<tr>
					<td><%=cartItem.getProduct().getProductName()%></td>
					<td><%=cartItem.getProduct().getManufacturerName()%></td>
					<td><%=df.format(cartItem.getProduct().getPrice())%></td>
					<td>
						<form action="" method="post" class="form-inline">
						<input type="hidden" name="id" value="id" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-success m-1" href="<%=request.getContextPath()%>/customerServlet?action=IncreaseAmountOfItem&id=<%=cartItem.getProduct().getProductID()%>&initialAmount=<%=cartItem.getAmount()%>">+</a>
								<input type="text" name="quantity" class="form-control"  value="<%=cartItem.getAmount()%>" readonly> 
								<a class="btn btn-sm btn-danger m-1" href="<%=request.getContextPath()%>/customerServlet?action=DecreaseAmountOfItem&id=<%=cartItem.getProduct().getProductID()%>&initialAmount=<%=cartItem.getAmount()%>">-</a>
							</div>
							<!-- <button type="submit" class="btn btn-primary btn-sm">Buy</button> -->
						</form>
					</td>
					<td><a href="<%=request.getContextPath()%>/customerServlet?action=RemoveItemFromCart&id=<%=cartItem.getProduct().getProductID()%>" class="btn btn-sm btn-danger">Xóa</a></td>
				</tr>
				<%
				}}%>
			</tbody>
		</table>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>