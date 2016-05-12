package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.ProblemService;

/**
 * Servlet implementation class EditSampleSolution
 */
@WebServlet("/EditSampleSolution")
public class EditSampleSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSampleSolution() {
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
		
		String problemSolutionPair = request.getParameter("problemSolutionPair");
		System.out.println("problem solution pair:" + problemSolutionPair);
		
		String badAspects = request.getParameter("badAspects");
		System.out.println("bad aspects: " + badAspects);
		
		String goodAspects = request.getParameter("goodAspects");
		System.out.println("good aspects: " + goodAspects);
		
		ProblemService psr = new ProblemService();
		
		psr.UpdateSampleSolution(problemSolutionPair, badAspects, goodAspects);
		
		response.sendRedirect("viewSampleSolutionsList.jsp");
	}

}
