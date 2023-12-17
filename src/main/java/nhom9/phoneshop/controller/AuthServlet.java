package nhom9.phoneshop.controller;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import nhom9.phoneshop.model.bean.BillBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.CustomerBean;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.UserBean;
import nhom9.phoneshop.model.bean.tables.Manufacturers;
import nhom9.phoneshop.model.bo.BillBo;
import nhom9.phoneshop.model.bo.ManufacturerBo;
import nhom9.phoneshop.model.bo.ProductBo;
import nhom9.phoneshop.model.bo.UserBo;

@MultipartConfig(fileSizeThreshold=102410242, 
maxFileSize=1024102410, 
maxRequestSize=1024102450)
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
			case "AdminGetProduct":
                listProduct(request, response);
                break;
			case "AdminGetCustomer":
				listCustomer(request, response);
				break;
			case "AdminAddProduct": 
				addProduct(request, response);
				break;
			case "AddProduct":
				handleAddProduct(request, response);
				break;
			case "EditProduct":
				editProduct(request, response);
				break;
			case "handleEditProduct":
				handleEditProduct(request, response);
				break;
            case "RemoveProduct":
                deleteProduct(request, response);
				break;
			case "GetBill":
				listBill(request, response);
				break;
			case "GetBillDetail":
				getBillDetail(request, response);
				break;
			case "SearchProduct":
				searchProduct(request, response);
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

	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		request.setAttribute("pdList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListPhone.jsp");
		rd.forward(request, response);	
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<CustomerBean> list;
		list = new UserBo().getAllCustomers();
		request.setAttribute("ctList", list);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListCustomer.jsp");
		rd.forward(request, response);	
	}

	private void listBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BillBo billBo = new BillBo();
		ArrayList<BillBean> bb = billBo.getBillsOfCustomer(id);
		request.setAttribute("bb", bb);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListBill.jsp");
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
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/BillDetail.jsp");
		rd.forward(request, response);
	}	

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<ProductBean> list;
		list = new ProductBo().getAllProducts();
		ArrayList<Manufacturers> mfList = new ManufacturerBo().getAllManufacturers();
		request.setAttribute("pdList", list);
		request.setAttribute("mfList", mfList);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/AddPhone.jsp");
		rd.forward(request, response);	
	}

    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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
		int Quantity = Integer.parseInt(request.getParameter("txtQuantity"));
		String Color = request.getParameter("txtColor");
		Part part = request.getPart("txtImage");
		
		ProductBo productBo = new ProductBo();
		if (productBo.registerProduct(ProductName, Price, ManufacturerName, CPU, RAM, DisplaySize, DisplayWidth, DisplayHeight, OS, Battery, Capacity, part, Quantity, Color)) {
            ArrayList<ProductBean> list;
			list = new ProductBo().getAllProducts();
			request.setAttribute("pdList", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListPhone.jsp");
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
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/EditPhone.jsp");
		rd.forward(request, response);
	}

	private void handleEditProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int ProductID = Integer.parseInt(request.getParameter("txtProductID"));
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
		int Quantity = Integer.parseInt(request.getParameter("txtQuantity"));
		String Color = request.getParameter("txtColor");
		ProductBo productBo = new ProductBo();
		if (productBo.updateProduct(ProductID, ProductName, Price, ManufacturerName, CPU, RAM, DisplaySize, DisplayWidth, DisplayHeight, OS, Battery, Capacity, part, Quantity, Color)) {
			ArrayList<ProductBean> list;
			list = new ProductBo().getAllProducts();
			request.setAttribute("pdList", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListPhone.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/Error.jsp");
			rd.forward(request, response);
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int ProductID = Integer.parseInt(request.getParameter("id"));
        ProductBo productBo = new ProductBo();
        productBo.deleteProduct(ProductID);
		ArrayList<ProductBean> list;
			list = new ProductBo().getAllProducts();
			request.setAttribute("pdList", list);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListPhone.jsp");
		rd.forward(request, response);	
	}

	

	private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String key = request.getParameter("txtSearch");
		ProductBo productBo = new ProductBo();
		ArrayList<ProductBean> result = productBo.search(key);
		request.setAttribute("pdList", result);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListPhone.jsp");
		rd.forward(request, response);
	}
}