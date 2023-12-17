package nhom9.phoneshop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "GetItems":
					getAllProducts(request, response);
					break;
				case "GetCartItems":
					getCartItems(request, response);
					break;
				case "UpdateItemFromCart":
					updateItemFromCart(request, response);
					break;
				case "IncreaseAmountOfItem":
					increaseAmountOfItem(request, response);
					break;
				case "DecreaseAmountOfItem":
					decreaseAmountOfItem(request, response);
					break;
				case "RemoveItemFromCart":
					removeItemFromCart(request, response);
					break;
				case "Checkout":
					Checkout(request, response);
					break;
				default:
					getCartItems(request, response);
					break;
			}
		} catch (ServletException ex) {
			throw new ServletException(ex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void decreaseAmountOfItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		int initialAmount = Integer.parseInt(request.getParameter("initialAmount"));
		new CartBo().updateProductAmountByUsername(username, Integer.parseInt(id), initialAmount - 1);
		CartBean cartBean = new CartBo().getCartByUsername(username);
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void increaseAmountOfItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		int initialAmount = Integer.parseInt(request.getParameter("initialAmount"));
		new CartBo().updateProductAmountByUsername(username, Integer.parseInt(id), initialAmount + 1);
		CartBean cartBean = new CartBo().getCartByUsername(username);
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void Checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		new CartBo().onPaid(username);
		CartBean cartBean = new CartBean();
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void getAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("ItemList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart-test.jsp");
		rd.forward(request, response);
	}

	private void getCartItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		CartBean cartBean = new CartBo().getCartByUsername(username);
		// ProductBean product1 = new ProductBean( 1, "Iphone 11", 10000000, 1, "Apple", "A13 Bionic", "4GB", "6.1 inch", 828, 1792, "IOS 13", "3110 mAh", 64, "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-red-400x460.png", 1, "Red");
		// ProductBean product2 = new ProductBean( 2, "Iphone 12", 10000000, 1, "Apple", "A13 Bionic", "4GB", "6.1 inch", 828, 1792, "IOS 13", "3110 mAh", 64, "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-red-400x460.png", 1, "Red");
		// CartItem cartItem1 = new CartItem(product1, 3, false);
		// CartItem cartItem2 = new CartItem(product2, 2, false);
		// ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		// cartItems.add(cartItem1);
		// cartItems.add(cartItem2);
		// CartBean cartBean = new CartBean(1, 1, cartItems);
		// cartBean.getItems().get(0).getProduct().getProductName();
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void updateItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
		new CartBo().updateCartItemsByUsername(username, cartItems);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		new CartBo().removeProductByUsername(username, Integer.parseInt(id)); //TODO: Check Product ID pls.
		CartBean cartBean = new CartBo().getCartByUsername(username);
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer/Cart.jsp");
		rd.forward(request, response);
	}
}
