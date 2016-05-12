package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.LoginService;

/**
 * Servlet implementation class AddNewUser
 */
@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName, password, upi, email, quesDescription, answer;
		int quesOID = 0;
		
		response.setContentType("text/html");
		
		
		userName = request.getParameter("userName");
		password = request.getParameter("password");
    	upi = request.getParameter("upi");
    	email = request.getParameter("email");
    	quesDescription = request.getParameter("securityQuestion");
    	answer = request.getParameter("securityAnswer");
		
		LoginService ls = new LoginService();
		quesOID = ls.mapQuestionDescriptionToOID(quesDescription);
		
		ls.addNewUser(userName, password, upi, email, quesOID, answer);
		
		response.sendRedirect("registrationSuccess.jsp");
	}

}
