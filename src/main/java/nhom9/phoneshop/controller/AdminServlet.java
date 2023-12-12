package nhom9.phoneshop.controller;

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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
            case "/ListProduct":
                listProduct(request, response);
                break;
			case "/AddProduct": 
				addProduct(request, response);
				break;
            case "/RemoveProduct":
                delete(request, response);
			default:
				addProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ProductBo productBo = new ProductBo();
        ArrayList<ProductBean> productList = productBo.getAllProducts();
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
        Integer DisplayWidth = Integer.parseInt(request.getParameter("txtDisplayWidth"));
        Integer DisplayHeight = Integer.parseInt(request.getParameter("txtDisplayHeight"));
        String OS = request.getParameter("txtOS");
        String Battery = request.getParameter("txtBattery");
        Double Capacity = Double.parseDouble(request.getParameter("txtCapacity"));
        Part part = request.getPart("txtImage");
        Collection<Part> clt = request.getParts();
		
		ProductBo productBo = new ProductBo();
		if (productBo.registerProduct(ProductName, Price, ManufacturerName, CPU, RAM, DisplaySize, DisplayWidth, DisplayHeight, OS, Battery, Capacity, part, clt)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListProduct.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/Error.jsp");
			rd.forward(request, response);
        }
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Integer ProuductID = Integer.parseInt(request.getParameter("ProductID"));
        ProductBo productBo = new ProductBo();
        productBo.deleteProduct(ProuductID);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/ListProduct.jsp");
		rd.forward(request, response);	
	}

}
