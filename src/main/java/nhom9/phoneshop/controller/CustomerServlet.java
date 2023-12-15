package nhom9.phoneshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nhom9.phoneshop.model.bean.CartBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bo.CartBo;
import nhom9.phoneshop.model.bo.ProductBo;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
				case "GetItemServlet":
					getAllProducts(request, response);
					break;
				case "GetCartItemServlet":
					getCartItems(request, response);
				case "UpdateItemFromCartServlet":
					updateItemFromCart(request, response);
					break;
				case "RemoveItemFromCartServlet":
					removeItemFromCart(request, response);
					break;
				default:
					updateItemFromCart(request, response);
					break;
			}
		} catch (ServletException ex) {
			throw new ServletException(ex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("ItemList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listProduct.jsp");
		rd.forward(request, response);
	}

	private void getCartItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		CartBean cartBean = new CartBo().getCartByUsername(username);
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/test.jsp");
		rd.forward(request, response);
	}

	private void updateItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
		new CartBo().updateCartItemsByUsername(username, cartItems);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		new CartBo().removeProductByUsername(username, Integer.parseInt(id)); //TODO: Check Product ID pls.
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/Cart.jsp");
		rd.forward(request, response);
	}
}
