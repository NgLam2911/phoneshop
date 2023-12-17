<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="css/bootstrap.min.css">
<header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
      <img src="webapp/img/phoneicon.png" width="30" height="30" class="d-inline-block align-top" alt="">
      Phone Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <% if (session.getAttribute("role") == "admin"){%>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=AdminGetProduct">Xem sản phẩm</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=AdminAddProduct">Thêm sản phẩm</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=AdminSearchProduct">Tìm kiếm sản phẩm (admin)</a>
        </li>
        <% }else if (session.getAttribute("role") == "customer"){ %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/customerServlet?action=GetCartItems">Xem giỏ hàng</a>
          </li>
        <% } %>
      </ul>
      <div class="ml-auto navbar-nav">
        <% if (request.getAttribute("user") == null) { %>
          <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Login</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/register.jsp">Register</a>
          </li>
      <% } %>
      </div>
    </div>
  </nav>
</header>