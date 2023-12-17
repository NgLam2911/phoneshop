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

import nhom9.phoneshop.model.bean.BillBean;
import nhom9.phoneshop.model.bean.CartBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.CustomerBean;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bo.BillBo;
import nhom9.phoneshop.model.bo.CartBo;
import nhom9.phoneshop.model.bo.ProductBo;
import nhom9.phoneshop.model.bo.UserBo;

@MultipartConfig(fileSizeThreshold=102410242, 
maxFileSize=1024102410, 
maxRequestSize=1024102450)
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
				case "GetProduct":
					getAllProducts(request, response);
					break;
				case "GetCartItems":
					getCartItems(request, response);
					break;
				case "AddProductToCart":
					addProductToCart(request, response);
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
				case "GetBill":
					listBill(request, response);
					break;
				case "GetBillDetail":
					getBillDetail(request, response);
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
	private void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("pdList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	private void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String id = request.getParameter("id");
		
		CartBo cartBo = new CartBo();
		CartBean cartBean = cartBo.getCartByUsername(username);
		
		// Check if the item ID already exists in the cart
		boolean itemExists = false;
		for (CartItem item : cartBean.getItems()) {
			if (item.getProduct().getProductID() == Integer.parseInt(id)) {
				// Increase the amount of the existing item by 1
				cartBo.updateProductAmountByUsername(username, Integer.parseInt(id), item.getAmount() + 1);
				itemExists = true;
				break;
			}
		}
		
		if (!itemExists) {
			// Add a new item to the cart with an initial amount of 1
			cartBo.addProductByUsername(username, Integer.parseInt(id), 1);
		}
		
		request.setAttribute("user", username);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
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
		(new CartBo()).onPaid(username);
		request.setAttribute("user", username);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
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

	private void listBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		BillBo billBo = new BillBo();
		ArrayList<BillBean> bb = billBo.getBillsOfCustomerByUsername(username);
		request.setAttribute("bb", bb);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListBill.jsp");
		rd.forward(request, response);
	}

	private void getBillDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BillBo billBo = new BillBo();
		UserBo userBo = new UserBo();
		
		BillBean bb = billBo.getBill(id);
		CustomerBean customerBean = userBo.getCustomer(bb.getCustomerID());
		request.setAttribute("cb", customerBean);
		ArrayList<CartItem> ci = bb.getBillItems();
		request.setAttribute("bb", bb);
		request.setAttribute("ci", ci);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/BillDetail.jsp");
		rd.forward(request, response);
	}
}
