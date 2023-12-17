<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="css/bootstrap.min.css">
<header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">Phone Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Xem sản phẩm</a>
          </li>
          <% if (request.getAttribute("user") != null) { %>
          <% session.setAttribute("user", request.getAttribute("user"));%>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/customerServlet?action=GetCartItems">Xem giỏ hàng</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/customerServlet?action=GetBill">Xem hóa đơn</a>
          </li>
          <% }else if (session.getAttribute("user") != null){ %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/customerServlet?action=GetCartItems">Xem giỏ hàng</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/customerServlet?action=GetBill">Xem hóa đơn</a>
          </li>
          <% } %>
      </ul>
      <div class="ml-auto navbar-nav">
        <% if (request.getAttribute("user") == null && session.getAttribute("user") == null) { %>
          <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Đăng nhập</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/register.jsp">Đăng ký</a>
          </li>
          <% } else {%>
          <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/authServlet?action=LogoutServlet">Đăng xuất</a>
          </li>
          <% } %>
      </div>
    </div>
  </nav>
</header>