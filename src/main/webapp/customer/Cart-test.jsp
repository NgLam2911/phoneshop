<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/all.min.css" />
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
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
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>Name</td>
					<td>Category></td>
					<td>Price</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="id" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href=""><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="" readonly> 
								<a class="btn btn-sm btn-decre" href=""><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

			</tbody>
		</table>
	</div>
</body>
</html>