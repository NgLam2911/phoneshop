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

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bo.ProductBo;
import nhom9.phoneshop.model.bo.UserBo;

@WebServlet("/")
public class AuthServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/LoginServlet": 
				checkLogin(request, response);
				break;
			case "/RegisterServlet":
				checkRegister(request, response);
				break;
			case "/GetProductServlet":
				getAllProducts(request, response);
                break;
            case "/GetCartServlet":
                getCartProducts(request, response);
			case "/AddProductToCartServlet":
				addProductToCart(request, response);
                break;
            case "/RemoveProductFromCartServlet":
                addProductToCart(request, response);
                break;
			default:
				checkLogin(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("txtusername");
		String password = request.getParameter("txtpassword");
		
		UserBo userBo = new UserBo();
		if (userBo.login(username, password)) {
			request.setAttribute("user", username);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

	private void checkRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customername = request.getParameter("txtCustomerName");
		String username = request.getParameter("txtUsername");
		String email = request.getParameter("txtEmail");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String password = request.getParameter("txtPassword");

		UserBo userBo = new UserBo();
		if (userBo.registerGuest(customername, username, email, phone, password, address)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
		}
	}

	private void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductBean> list = new ArrayList<>();
		list = (new ProductBo()).getAllProducts();
		request.setAttribute("pdList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listProduct.jsp");
		rd.forward(request, response);
	}

	private void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		//new MainBo().addProductToCart(id);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Cart.jsp");
		rd.forward(request, response);
	}

    private void removeProductFromCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        //new MainBo().removeProductFromCart(id);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Cart.jsp");
        rd.forward(request, response);
    }

    private void getCartProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ProductBean> list = new ArrayList<>();
        //list = new MainBo().getCartProducts();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Cart.jsp");
        rd.forward(request, response);
    }
}
