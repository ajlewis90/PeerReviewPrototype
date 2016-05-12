package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.ProblemService;

/**
 * Servlet implementation class AddNewProblem
 */
@WebServlet("/AddNewProblem")
public class AddNewProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProblem() {
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
		String problemNumber, problemName, problemDescription;
		
		response.setContentType("text/html");
		
		
		problemNumber = request.getParameter("problemNumber");	
		problemName = request.getParameter("problemName");
		problemDescription = request.getParameter("problemDescription");
		
		ProblemService psr = new ProblemService();
		//call method to fire insert query into database
		psr.addNewProblem(problemNumber, problemName, problemDescription);
		
		response.sendRedirect("viewProblemsList.jsp");
	}

}
