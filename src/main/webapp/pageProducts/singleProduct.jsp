<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<%
    HttpSession session = request.getSession();
    // session.setAttribute("id", "");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../css/query.css"/>
    <link rel="stylesheet" href="../css/singleproduct.css"/>
    <script src="../js/bootstrap.min.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <title>Document</title>
</head>

<body>
    <script defer src="../js/script.js"></script>
    <%@ include file="../config.jsp" %>
    <%@ include file="../modules/singlePageHeader/header.jsp" %>

    <%
        String idproduct = request.getParameter("id");
        if (idproduct != null) {
            // Assuming you have already imported necessary database connection and statement objects
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                connection = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");
                statement = connection.createStatement();

                String sql_product = "SELECT * FROM sanpham WHERE idsanpham = " + idproduct;
                resultSet = statement.executeQuery(sql_product);

                if (resultSet.next()) {
                    String hinhanhsanpham = resultSet.getString("hinhanhsanpham");
                    String tensanpham = resultSet.getString("tensanpham");
                    int gia = resultSet.getInt("gia");
                    double giatang = gia + (gia * 0.3);
    %>

    <div class="product-wrap">
        <div class="single-product">
            <div class="single-product-img">
                <img src="../admin/modules/sanpham/upload/<%= hinhanhsanpham %>" alt="" srcset=""/>
            </div>

            <div class="single-product-title">
                <h2 class="product-title">
                    <span class="like-stamp">Yêu thích</span><%= tensanpham %>
                </h2>

                <!-- Remaining HTML content, continue using <%= %> for Java expressions -->
                <!-- Note: You need to handle other parts of your PHP code similarly -->
            </div>
        </div>
    </div>

    <section class="main-content">
        <%@ include file="productRecommend.jsp" %>
    </section>

    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources in the finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    %>
</body>

</html>
