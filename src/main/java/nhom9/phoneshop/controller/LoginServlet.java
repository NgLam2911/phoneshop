package nhom9.phoneshop.controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nhom9.phoneshop.model.bo.UserBo;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/LoginServlet": {
				checkLogin(request, response);
				break;
			}
			default:
				checkLogin(request, response);
				break;
			}
		} catch (ServletException ex) {
			throw new ServletException(ex);
		}
	}

    

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("txtusername");
		String password = request.getParameter("txtpassword");
		
		UserBo userBo = new UserBo();
		if (userBo.login(username, password)) {
			request.setAttribute("user", username);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(request, response);
		}
	}
}
