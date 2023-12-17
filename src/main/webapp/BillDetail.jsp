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
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <%@ include file="common/header.jsp" %>
    <div class="container mt-4">
        <%
            CustomerBean cb = (CustomerBean)request.getAttribute("cb");
        %>
        <h2>Người tiêu dùng: <%= cb.getCustomerName() %></h2>
        <h3 class="text-center">Chi tiết hóa đơn</h3>
        <table class="table table-bordered">
            <thead class="thead-light">
        <thead class="thead-light">
            <tr>
                <th>ID Sản phẩm</th>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Giá</th>
            </tr>
        </thead>
        <tbody>
            <%
                BillBean bb = (BillBean)request.getAttribute("bb");
                ArrayList<CartItem> ci = (ArrayList<CartItem>)request.getAttribute("ci");
                for (int i = 0; i < ci.size(); i++) {
            %>
            <tr>
                <td><%= ci.get(i).getProduct().getProductName() %></td>
                <td><%= ci.get(i).getAmount() %></td>
                <td><%= df.format(ci.get(i).getProduct().getPrice()) %></td>
            </tr>
            <%
                }
            %>
        </tbody>
        </table>
        <h2> Ngày mua hàng: <%= bb.getPurchaseDate() %></h2>
        <h2> Đơn giá: <%= df.format(bb.getBillTotalPrice()) %></h2>
        </div>
<%@ include file="common/footer.jsp" %>
</body>
</html>