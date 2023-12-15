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
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.tables.Cart;
import nhom9.phoneshop.model.bean.tables.CartsData;
import nhom9.phoneshop.model.bo.MainBo;
import nhom9.phoneshop.model.bo.ProductBo;
import nhom9.phoneshop.model.bo.UserBo;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
				case "/GetItemServlet":
					getAllProducts(request, response);
					break;
				case "/GetCartItemServlet":
					getCartItems(request, response);
				case "/UpdateItemFromCartServlet":
					updateItemFromCart(request, response);
					break;
				case "/RemoveItemFromCartServlet":
					removeItemFromCart(request, response);
					break;
				default:
					getAllProducts(request, response);
					break;
			}
		} catch (ServletException ex) {
			throw new ServletException(ex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<ProductBean> getAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ProductBean> list = new ArrayList<>();
		list = new ProductBo().getAllProducts();
		request.setAttribute("ItemList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListItem.jsp");
		rd.forward(request, response);
		return list;
	}

	private void getCartItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		CartBean cartBean = new CartBean();
		Cart cart = new MainBo().getCartItems(username);
		cartBean.setCart(cart);
		request.setAttribute("cartBean", cartBean);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void updateItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		String[] items = (String[]) session.getAttribute("items");
		String[] amounts = (String[]) session.getAttribute("amounts");
		new MainBo().UpdateItemFromCart(username, Items, amounts);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/Cart.jsp");
		rd.forward(request, response);
	}

	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		new MainBo().removeItemFromCart(id);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("customer/Cart.jsp");
		rd.forward(request, response);
	}
}
