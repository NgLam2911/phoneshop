<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        // Assuming you have already imported necessary database connection and statement objects
        connection = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");
        statement = connection.createStatement();

        String sql_product = "SELECT * FROM sanpham ORDER BY RAND() LIMIT 0,6";
        resultSet = statement.executeQuery(sql_product);
        connection.setCharset("UTF8");
%>

<div class="sales fix-sales">
    <div class="container sales-product">
        <h3 class="title-sale">Sản phẩm liên quan</h3>
        <div class="product-item">
            <%
                while (resultSet.next()) {
                    String idSanPham = resultSet.getString("idsanpham");
                    String hinhAnhSanPham = resultSet.getString("hinhanhsanpham");
                    String tenSanPham = resultSet.getString("tensanpham");
                    int gia = resultSet.getInt("gia");
                    int soLuongBan = resultSet.getInt("soluongban");
            %>
            <a class="item-sales" href="singleproduct.jsp?id=<%= idSanPham %>">
                <img class="product-image" src="../admin/modules/sanpham/upload/<%= hinhAnhSanPham %>" alt="" srcset="">
                <div class="product-detail">
                    <h4 class="product-tittle"><%= tenSanPham %></h4>
                    <div class="price">
                        <p class="price"><sub><u>đ</u></sub><%= gia %></p>
                        <p class="sold"><%= soLuongBan %></p>
                    </div>
                    <div class="find-product">Tìm Sản Phẩm Tương Tự</div>
                </div>
                <!-- <p class="discount">Yêu thích</p> -->
            </a>
            <%
                }
            %>
        </div>
    </div>
</div>

<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close database resources in the finally block
 
