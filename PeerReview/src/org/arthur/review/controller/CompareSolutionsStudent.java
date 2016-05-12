package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.StudentRankingService;

/**
 * Servlet implementation class CompareSolutions
 */
@WebServlet("/CompareSolutionsStudent")
public class CompareSolutionsStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareSolutionsStudent() {
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
		
		String lhsSampleKey = request.getParameter("leftProblemSolutionPair");
		String rhsSampleKey = request.getParameter("rightProblemSolutionPair");
		
		String pairSampleKey = lhsSampleKey + "-" + rhsSampleKey;
		
		String lhsSolutionGoodAspects = request.getParameter("leftGoodAspects");
		String rhsSolutionGoodAspects = request.getParameter("rightGoodAspects");
		String lhsSolutionBadAspects =  request.getParameter("leftBadAspects");
		String rhsSolutionBadAspects =  request.getParameter("rightBadAspects");
		
		String leftSolutionUploadPath = request.getParameter("leftUploadLocation");
		String rightSolutionUploadPath = request.getParameter("rightUploadLocation");
		
		String rankingJudgement = request.getParameter("betterSolution");
		String rankingJustification = request.getParameter("justifyBetterSolution");
		
		StudentRankingService studentRanker = new StudentRankingService();
		//AdminRankingService adminRanker = new AdminRankingService();
		
		//run first query to add review/ranking record to StudentReviewTable
		studentRanker.addStudentReviewRecord(lhsSampleKey, rhsSampleKey, pairSampleKey,
				leftSolutionUploadPath, rightSolutionUploadPath, 
				rankingJudgement, rankingJustification,
				lhsSolutionGoodAspects, rhsSolutionGoodAspects,
				lhsSolutionBadAspects, rhsSolutionBadAspects, userName);
		
		//write query to retrieve the new added StudentReview record
		//StudentReview studentReview = studentRanker.getReviewDetails(pairSampleKey, userName);
		//write query to retrieve corresponsing AdminReview record
		//AdminReview adminReview = adminRanker.getReviewDetails(pairSampleKey);
		
		//redirect to review feedback page
		response.sendRedirect("viewStudentRankingList.jsp");
	}

}
