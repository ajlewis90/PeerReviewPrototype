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
 * Servlet implementation class SecondSolutionListSelection
 */
@WebServlet("/SecondSolutionListSelection")
public class SecondSolutionListSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondSolutionListSelection() {
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
		String firstSampleSolution = request.getParameter("firstSampleSolution");
		String firstProblemSolutionKey = problemNumber + firstSampleSolution;
		
		AdminRankingService adminRanker = new AdminRankingService();
		List<SampleSolution> remainingSampleSolutions = adminRanker.getRemainingSolutionsOfProblem(problemNumber, firstSampleSolution);
		
		//two checks
		//from the remaining list check if count in table is less than size-1
		//add it to another list
		//from this new list check if each problemsolution does not have a pair with the other guy
		//if this is true add it to third list which is to be returned
		
		//List<SampleSolution> sampleSolutionList = adminRanker.getProblemSpecificSampleSolutions(problemNumber);
		List<String> sampleNumberList = new ArrayList<String>();
		//sampleNumberList.add(" ");

		for (SampleSolution sampleSolution : remainingSampleSolutions) {
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

			if (countProblemSamplePairOccurences <= (totalSampleSolutions - 1)){
				availableSampleSolutions.add(problemSampleNumber);    		
			}

		}

		System.out.println("Available Solutions to review are: " + availableSampleSolutions);
		
		if (availableSampleSolutions.size() > 0){
			//if samples to review are greater than 0 do the below
			//first write code to find out the samples to review
			List<SampleSolution> secondSampleSolutionSelection = new ArrayList<SampleSolution>();
			
			
			for (String availableSampleSolution : availableSampleSolutions){
					if(adminRanker.getCheckForPairNonExistence(firstProblemSolutionKey, availableSampleSolution)){
						secondSampleSolutionSelection
						.add(adminRanker.getSampleSolutionDetails(availableSampleSolution));
					}
				
			}
			System.out.println("Available Solutions to review are: " + secondSampleSolutionSelection);
			//if samples to review are greater than 0 do the below
			//else add another part for redirection
			/*List<SampleSolution> firstSampleSolutionSelection = new ArrayList<SampleSolution>();
			
			for (String availableSampleSolution : availableSampleSolutions){
				firstSampleSolutionSelection
					.add(adminRanker.getSampleSolutionDetails(availableSampleSolution));
			}*/
			
			request.setAttribute("Problem Number", problemNumber);
			request.setAttribute("Available Solutions to review", secondSampleSolutionSelection);
			request.setAttribute("First Sample Solution Number", firstSampleSolution);
			request.getRequestDispatcher("rankSampleSolutions2.jsp").forward(request, response);
		}
	}

}
