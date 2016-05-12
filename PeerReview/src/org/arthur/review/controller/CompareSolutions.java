package org.arthur.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.service.AdminRankingService;

/**
 * Servlet implementation class CompareSolutions
 */
@WebServlet("/CompareSolutions")
public class CompareSolutions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareSolutions() {
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
		
		AdminRankingService adminRanker = new AdminRankingService();
		
		//run first query to add review/ranking record to AdminReviewTable
		adminRanker.addAdminReviewRecord(lhsSampleKey, rhsSampleKey, pairSampleKey,
				leftSolutionUploadPath, rightSolutionUploadPath, 
				rankingJudgement, rankingJustification,
				lhsSolutionGoodAspects, rhsSolutionGoodAspects,
				lhsSolutionBadAspects, rhsSolutionBadAspects);
		
		//run second query to update good and bad aspects of required samplesolutions 
		//in adminTable
		adminRanker.updateAspects(lhsSampleKey, lhsSolutionGoodAspects, lhsSolutionBadAspects);
		adminRanker.updateAspects(rhsSampleKey, rhsSolutionGoodAspects, rhsSolutionBadAspects);
		
		//redirect page to admin ranks already done which is a tabular view
		response.sendRedirect("viewAdminRankingList.jsp");
	}

}
