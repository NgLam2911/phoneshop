<%@page import="java.util.ArrayList"%>
<%@page import="nhom9.phoneshop.model.bean.CartBean"%>
<%@page import="nhom9.phoneshop.model.bean.CartItem"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat df = new DecimalFormat("#.00");
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


	<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Manufacturer</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Remove</th>
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
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="id" class="form-input">
							<div class="form-group d-flex justify-content-between">
								 
								<input type="text" name="quantity" class="form-control"  value="<%=cartItem.getAmount()%>" > 
								
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
				<%
				}}%>
			</tbody>
		</table>
	</div>
</body>
</html>