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
    <title>Xem danh sách hóa đơn</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <%@ include file="common/header.jsp" %>
    <div class="container mt-4">
        <h3 class="text-center">Danh sách hóa đơn</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Hóa đơn</th>
                    <th>Xem chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<BillBean> bb = (ArrayList<BillBean>)request.getAttribute("bb");
                %>
                <% for (int i = 0; i < bb.size(); i++) { %>
                <tr>
                    <td><%= bb.get(i).getBillID() %></td>
                    <td class="action-buttons">
                        <a href="<%=request.getContextPath()%>/customerServlet?action=GetBillDetail&id=<%= bb.get(i).getBillID() %>"
                        class="btn btn-primary">Xem hóa đơn</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>


</body>
</html>