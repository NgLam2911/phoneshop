package nhom9.phoneshop.controller;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bo.ProductBo;
import nhom9.phoneshop.model.bo.UserBo;

@WebServlet("/authServlet")
public class AuthServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "LoginServlet": 
				checkLogin(request, response);
				break;
			case "RegisterServlet":
				checkRegister(request, response);
				break;
			case "GetProduct":
				getAllProducts(request, response);
                break;
            case "GetCart":
                getCartProducts(request, response);
			case "AddProductToCart":
				addProductToCart(request, response);
                break;
            case "RemoveProductFromCart":
                addProductToCart(request, response);
                break;
			case "AdminGetProduct":
                listProduct(request, response);
                break;
			case "AddProduct": 
				addProduct(request, response);
				break;
			case "EditProduct":
				editProduct(request, response);
				break;
            case "RemoveProduct":
                delete(request, response);
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
		if (userBo.login(username, password) != null) {
			request.setAttribute("user", username);
			if (userBo.login(username, password).getRoleID() == 1) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/Index.jsp");
				rd.forward(request, response);
			} else if (userBo.login(username, password).getRoleID() == 2) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("login", "Đăng nhập thất bại! Vui lòng thử lại hoặc đăng ký!");
			rd.forward(request, response);
		}
	}

	private void checkRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customer = request.getParameter("txtCustomerName");
		String username = request.getParameter("txtUsername");
		String email = request.getParameter("txtEmail");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String password = request.getParameter("txtPassword");

		UserBo userBo = new UserBo();
		if (userBo.registerGuest(customer, username, email, phone, password, address)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
			rd.forward(request, response);
		}
	}

	private void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("pdList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listPhone.jsp");
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

	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("pdList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListProduct.jsp");
		rd.forward(request, response);	
	}

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ProductName = request.getParameter("txtProductName");
		double Price = Double.parseDouble(request.getParameter("txtPrice"));
        String ManufacturerName = request.getParameter("txtManufacturerName");
        String CPU = request.getParameter("txtCPU");
        String RAM = request.getParameter("txtRAM");
        String DisplaySize = request.getParameter("txtDisplaySize");
        int DisplayWidth = Integer.parseInt(request.getParameter("txtDisplayWidth"));
        int DisplayHeight = Integer.parseInt(request.getParameter("txtDisplayHeight"));
        String OS = request.getParameter("txtOS");
        String Battery = request.getParameter("txtBattery");
        double Capacity = Double.parseDouble(request.getParameter("txtCapacity"));
        Part part = request.getPart("txtImage");
        Collection<Part> clt = request.getParts();
		String Color = request.getParameter("txtColor");
		
		ProductBo productBo = new ProductBo();
		if (productBo.registerProduct(ProductName, Price, ManufacturerName, CPU, RAM, DisplaySize, DisplayWidth, DisplayHeight, OS, Battery, Capacity, part, clt, DisplayHeight, Color)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListProduct.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/Error.jsp");
			rd.forward(request, response);
        }
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int ProductID = Integer.parseInt(request.getParameter("id"));
		ProductBo productBo = new ProductBo();
		ProductBean pd = productBo.getProduct(ProductID);
		request.setAttribute("pd", pd);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/EditProduct.jsp");
		rd.forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int ProductID = Integer.parseInt(request.getParameter("ProductID"));
        ProductBo productBo = new ProductBo();
        productBo.deleteProduct(ProductID);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListProduct.jsp");
		rd.forward(request, response);	
	}
}
