package org.arthur.review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.model.User;
import org.arthur.review.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName, password;
		
		userName = request.getParameter("userName");
		password = request.getParameter("password");

		LoginService loginService = new LoginService();
		boolean result = loginService.authenticate(userName, password);
		
		if (result == true){
			User user = loginService.getUserInformation(userName);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("userName", userName);
			Boolean isAdmin = user.isAdmin();
			
			if (isAdmin){
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminhome.jsp");
				dispatcher.forward(request, response);
			}
			
			else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("studenthome.jsp");
				PrintWriter out= response.getWriter();
	            out.println("<font color=red>Incorrect combination of username and password</font>");
				dispatcher.forward(request, response);
			}
			
			// this dispatcher can be then used to transmit the control to the
			// value defined in request.dispatcher
			
		}
		
		else{
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
