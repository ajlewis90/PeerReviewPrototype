package org.arthur.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arthur.review.model.SampleSolution;
import org.arthur.review.service.AdminRankingService;

/**
 * Servlet implementation class FirstSolutionListSelection
 */
@WebServlet("/FirstSolutionListSelection")
public class FirstSolutionListSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstSolutionListSelection() {
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

		String problemNumber = request.getParameter("problemNumber");
		AdminRankingService adminRanker = new AdminRankingService();
		List<SampleSolution> sampleSolutionList = adminRanker.getProblemSpecificSampleSolutions(problemNumber);
		List<String> sampleNumberList = new ArrayList<String>();
		//sampleNumberList.add(" ");

		for (SampleSolution sampleSolution : sampleSolutionList) {
			//System.out.println(sampleSolution.getSolution_Number());
			sampleNumberList.add(sampleSolution.getSolution_Number());
		}

		int totalSampleSolutions = sampleNumberList.size();
		int countProblemSamplePairOccurences = 0;

		List<String> problemSampleNumberList = new ArrayList<String>();

		for (String sampleSolutionNumber : sampleNumberList){
			problemSampleNumberList.add(problemNumber + sampleSolutionNumber);
		}

		List<String> availableSampleSolutions = new ArrayList<String>();

		for (String problemSampleNumber : problemSampleNumberList){

			//run query to return count of ps occurences in table
			countProblemSamplePairOccurences = adminRanker.getNumberOfProblemSolutionPairOccurrences(problemSampleNumber);
			//if count <= size-1
			//write code to add that ProblemSamplePair 
			//on to the list of actually availableSampleSolutions
			System.out.println("countProblemSamplePairOccurences " + countProblemSamplePairOccurences);
			if (countProblemSamplePairOccurences < (totalSampleSolutions - 1)){
				availableSampleSolutions.add(problemSampleNumber);    		
			}

		}

		System.out.println("Available Solutions to review are: " + availableSampleSolutions);
		
		if (availableSampleSolutions.size() > 0){
			
			List<SampleSolution> firstSampleSolutionSelection = new ArrayList<SampleSolution>();
			
			for (String availableSampleSolution : availableSampleSolutions){
				firstSampleSolutionSelection
					.add(adminRanker.getSampleSolutionDetails(availableSampleSolution));
			}
			
			request.setAttribute("Problem Number", problemNumber);
			request.setAttribute("Available Solutions to review", firstSampleSolutionSelection);
			request.getRequestDispatcher("rankSampleSolutions1.jsp").forward(request, response);
		}
		

		//check for size of available samplecolutions
		//if size is 0 redirect to viewproblemspage??

		//else if size greater than 0
		//set requestatribute as the List  or a String Array??
		//send redirect to rankSampleSolutions 1

		//request.setAttribute(name, o);


		//response.sendRedirect("rankSampleSolutions1.jsp");

	}

}
