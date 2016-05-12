package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.LoginService;
import org.arthur.review.service.ProblemService;

/**
 * Servlet implementation class LeftRightOrderDecision
 */
@WebServlet("/LeftRightOrderDecision")
public class LeftRightOrderDecision extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeftRightOrderDecision() {
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
		
		String userName = request.getParameter("userName");
		LoginService loginService = new LoginService();
		boolean isAdminUser = loginService.getUserInformation(userName).isAdmin();
		
		String leftHandSideKey = null;
		String rightHandSideKey = null;
		
		String problemNumber = request.getParameter("problemNumber");
		System.out.println("LeftRight problemNumber: " + problemNumber);
		
		String firstSample = request.getParameter("firstSampleSolution");
		System.out.println("first sample key: " + firstSample);
		String secondSample = request.getParameter("secondSampleSolution");
		System.out.println("second sample key: " + secondSample);
		
		ProblemService probService = new ProblemService();
		String firstSamplePair = problemNumber + firstSample;
		String secondSamplePair = problemNumber + secondSample;
		
		//Decide LHS and RHS sample solutions
		int compare = firstSamplePair.compareTo(secondSamplePair);
		
		if(compare < 0){
			leftHandSideKey = firstSamplePair;
			rightHandSideKey = secondSamplePair;
		}
		
		else{
			leftHandSideKey = secondSamplePair;
			rightHandSideKey = firstSamplePair;
		}
		
		String leftHandSideSampleCode = probService.getSampleSolutionCode(leftHandSideKey);
		String rightHandSideSampleCode = probService.getSampleSolutionCode(rightHandSideKey);
		
		request.getSession().setAttribute("Problem Number", problemNumber);
		
		request.getSession().setAttribute("first sample", leftHandSideKey);
		request.getSession().setAttribute("second sample", rightHandSideKey);
		
		request.getSession().setAttribute("Code of LHS", leftHandSideSampleCode);
		request.getSession().setAttribute("Code of RHS", rightHandSideSampleCode);
		
		if(isAdminUser){
			response.sendRedirect("compareSolutions.jsp");
		}
		
		else {
			response.sendRedirect("compareSolutionsStudent.jsp");
		}
	
	}

}
