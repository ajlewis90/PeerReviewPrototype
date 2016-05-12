package org.arthur.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.model.Problem;
import org.arthur.review.model.SampleSolution;
import org.arthur.review.service.ProblemService;
import org.arthur.review.service.StudentRankingService;

/**
 * Servlet implementation class ProblemsToReview
 */
@WebServlet("/ProblemsToReview")
public class ProblemsToReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemsToReview() {
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
		doGet(request, response);

		String userName = request.getSession().getAttribute("userName").toString();

		List<Problem> allProblems = new ArrayList<Problem>();
		ProblemService problemService = new ProblemService();
		
		StudentRankingService studentRanker = new StudentRankingService();
		
		allProblems = problemService.getAllProblems();
		
		int numberOfSolutionsPerProblem = 0;
		int problemReviewLimit = 0;
		int actualReviewedCombinationCount = 0;
		
		List<Problem> problemsToReview = new ArrayList<Problem>();
		
		for (Problem problem : allProblems){
			List<SampleSolution> sampleSolutions = new ArrayList<SampleSolution>();
			
			sampleSolutions = problemService.getProblemSpecificSampleSolutions(problem.getProblemNumber());
			numberOfSolutionsPerProblem = sampleSolutions.size();
			
			problemReviewLimit = (numberOfSolutionsPerProblem * (numberOfSolutionsPerProblem - 1))/2;
			System.out.println("problemReviewLimit count: " + problemReviewLimit);
			
			actualReviewedCombinationCount = studentRanker.getProblemReviewsForUser(userName, problem.getProblemNumber());
			System.out.println("actualReviewedCombinationCount is: "+actualReviewedCombinationCount);
			
			if (actualReviewedCombinationCount < problemReviewLimit){
				problemsToReview.add(problem);
			}
		}
		
		request.setAttribute("ProblemsToReview", problemsToReview);
		response.sendRedirect("viewProblemsToReview.jsp");
	}

}
