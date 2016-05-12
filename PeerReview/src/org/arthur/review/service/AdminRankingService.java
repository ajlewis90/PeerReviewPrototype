package org.arthur.review.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.arthur.review.database.DataConnection;
import org.arthur.review.model.AdminReview;
import org.arthur.review.model.SampleSolution;

public class AdminRankingService {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;


	/**Retrieves all sample solutions
	 * that belong to specific problem
	 * @param ProblemNumber
	 * @return
	 */
	public List<SampleSolution> getProblemSpecificSampleSolutions(String problemNumber){

		List<SampleSolution> sampleSolutionList = new ArrayList<SampleSolution>();

		System.out.println("Value of problem_number = " + problemNumber);

		try {
			con = DataConnection.getConnection();

			/*String query = "select distinct problem_number,"
					+ " (select problem_solution_pair"
					+ "	from SampleSolutions"
					+ "	where solution_number = 'S1'"
					+ " and problem_number = ?) as Sample1,"
					+ " (select problem_solution_pair"
					+ "	from SampleSolutions"
					+ "	where solution_number = 'S2'"
					+ " and problem_number = ?) as Sample2,"
					+ " (select problem_solution_pair"
					+ "	from SampleSolutions"
					+ "	where solution_number = 'S3'"
					+ " and problem_number = ?) as Sample3"
					+ " from SampleSolutions"
					+ " Where problem_number = ?";*/

			String query = "select problem_solution_pair, Problem_Number, Solution_Number, upload_location,"
					+ " good_aspects, bad_aspects"
					+ " from SampleSolutions"
					+ " where Problem_Number = ?"
					+ " order by problem_solution_pair";

			ps = con.prepareStatement(query);
			ps.setString(1, problemNumber);

			rs = ps.executeQuery();

			while(rs.next()) {
				SampleSolution sampleSolution = new SampleSolution();

				sampleSolution.setProblem_Solution_Pair(rs.getString(1));
				sampleSolution.setProblem_Number(rs.getString(2));
				sampleSolution.setSolution_Number(rs.getString(3));
				sampleSolution.setUpload_Location(rs.getString(5));
				sampleSolution.setGood_Apects(rs.getString(5));
				sampleSolution.setBad_Aspects(rs.getString(6));

				sampleSolutionList.add(sampleSolution);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return sampleSolutionList;
	}

	/**
	 * Returns remaining sample solutions of a
	 * given problem by excluding the ones
	 * specified within the second parameter 
	 * of the method
	 * @param problemNumber
	 * @param exemptSampleSolution
	 * @return
	 */
	public List<SampleSolution> getRemainingSolutionsOfProblem(String problemNumber, String exemptSampleSolution){
		
		System.out.println("Exempted sample solution is: " + exemptSampleSolution);
		List<SampleSolution> sampleSolutionList = new ArrayList<SampleSolution>();

		System.out.println("Value of problem_number = " + problemNumber);

		try {
			con = DataConnection.getConnection();


			String query = "select problem_solution_pair, Problem_Number, Solution_Number, upload_location,"
					+ " good_aspects, bad_aspects"
					+ " from SampleSolutions"
					+ " where Problem_Number = ?"
					+ " and Solution_Number NOT IN"
					+ " (select solution_number from SampleSolutions"
					+ " where solution_number = ?)"
					+ " order by problem_solution_pair";

			ps = con.prepareStatement(query);
			ps.setString(1, problemNumber);
			ps.setString(2, exemptSampleSolution);

			rs = ps.executeQuery();

			while(rs.next()) {
				SampleSolution sampleSolution = new SampleSolution();

				sampleSolution.setProblem_Solution_Pair(rs.getString(1));
				sampleSolution.setProblem_Number(rs.getString(2));
				sampleSolution.setSolution_Number(rs.getString(3));
				sampleSolution.setUpload_Location(rs.getString(4));
				sampleSolution.setGood_Apects(rs.getString(5));
				sampleSolution.setBad_Aspects(rs.getString(6));

				sampleSolutionList.add(sampleSolution);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return sampleSolutionList;
	}

	/**
	 * Generates a new review record
	 * for a unique pair of solutions
	 * to a specific problem after the administrator
	 * has completed their review following a
	 * comparison of the two solutions in the pair
	 * @param lhsSampleKey
	 * @param rhsSampleKey
	 * @param pairSampleKey
	 * @param leftSolutionUploadPath
	 * @param rightSolutionUploadPath
	 * @param rankingJudgement
	 * @param rankingJustification
	 * @param lhsSolutionGoodAspects
	 * @param rhsSolutionGoodAspects
	 * @param lhsSolutionBadAspects
	 * @param rhsSolutionBadAspects
	 */
	public void addAdminReviewRecord(String lhsSampleKey, String rhsSampleKey, String pairSampleKey,
			String leftSolutionUploadPath, String rightSolutionUploadPath, String rankingJudgement,
			String rankingJustification, String lhsSolutionGoodAspects, String rhsSolutionGoodAspects,
			String lhsSolutionBadAspects, String rhsSolutionBadAspects) {
		
		try{

			con = DataConnection.getConnection();
			String query = "INSERT into AdminReview (lhs_solution_key, rhs_solution_key, review_pair_key,"
					+ " ranking_judgement, ranking_justification,"
					+ " leftSolutionUploadPath, rightSolutionUploadPath,"
					+ " lhs_good_aspects, rhs_good_aspects,"
					+ " lhs_bad_aspects, rhs_bad_aspects)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";					

			ps = con.prepareStatement(query);

			ps.setString(1, lhsSampleKey);
			ps.setString(2, rhsSampleKey);
			ps.setString(3, pairSampleKey);
			ps.setString(4, rankingJudgement);
			ps.setString(5, rankingJustification);
			ps.setString(6, leftSolutionUploadPath);
			ps.setString(7, rightSolutionUploadPath);
			ps.setString(8, lhsSolutionGoodAspects);
			ps.setString(9, rhsSolutionGoodAspects);
			ps.setString(10, lhsSolutionBadAspects);
			ps.setString(11, rhsSolutionBadAspects);
			
			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		
	}

	/**
	 * Updates the corresponding good and bad aspects of 
	 * one of two solutions just ranked by the administrator
	 * The record in the SampleSolutionsTable will be updated.
	 * This happens immediately after the administration has reviewed and ranked a 
	 * pair of solutions to a problem
	 * @param lhsSampleKey
	 * @param lhsSolutionGoodAspects
	 * @param lhsSolutionBadAspects
	 */
	public void updateAspects(String problemSolutionPair, String goodAspects, String badAspects) {
		try{
			con = DataConnection.getConnection();


			String query = "Update SampleSolutions"
					+ " Set good_aspects = ?,"
					+ " bad_aspects = ? "
					+  " where problem_solution_pair = ?";

			ps = con.prepareStatement(query);

			ps.setString(1, goodAspects);
			ps.setString(2, badAspects);
			ps.setString(3, problemSolutionPair);

			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		
	}

	/**
	 * Returns the number of occurrences where
	 * a given sample solution specific to a problem exists
	 * in the AdminReview table
	 * @param problemSampleNumber
	 * @return
	 */
	public int getNumberOfProblemSolutionPairOccurrences(String problemSampleNumber) {
		
		int rowCount = 0;
		try {
			con = DataConnection.getConnection();

			String query = "select count(*) from AdminReview"
					+ " where review_pair_key like ?";


			ps = con.prepareStatement(query);
			ps.setString(1, "%" + problemSampleNumber + "%");

			rs = ps.executeQuery();

			rs.next();
		    rowCount = rs.getInt(1);
		    System.out.println(rowCount);

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return rowCount;
	}

	/**
	 * 
	 * @param availableSampleSolution
	 * @return
	 */
	public SampleSolution getSampleSolutionDetails(String availableSampleSolution) {
		
		SampleSolution sampleSolution = new SampleSolution();
		try {
			con = DataConnection.getConnection();

			String query = "select problem_solution_pair, Problem_Number, Solution_Number, upload_location,"
					+ " good_aspects, bad_aspects"
					+ " from SampleSolutions"
					+ " where problem_solution_pair = ?";


			ps = con.prepareStatement(query);
			ps.setString(1, availableSampleSolution);

			rs = ps.executeQuery();

			while(rs.next()) {
				sampleSolution.setProblem_Solution_Pair(rs.getString(1));
				sampleSolution.setProblem_Number(rs.getString(2));
				sampleSolution.setSolution_Number(rs.getString(3));
				sampleSolution.setUpload_Location(rs.getString(4));
				sampleSolution.setGood_Apects(rs.getString(5));
				sampleSolution.setBad_Aspects(rs.getString(6));
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return sampleSolution;
		
	}

	/**
	 * Checks if a given pair of sample solutions
	 * is already existing in the AdminReview table
	 * @param firstProblemSolutionKey
	 * @param availableSampleSolution
	 * @return
	 */
	public boolean getCheckForPairNonExistence(String firstProblemSolutionKey,
			String availableSampleSolutionKey) {
		
		boolean result = true;
		String leftHandSideKey = null;
		String rightHandSideKey = null;
		
		//String secondProblemSolutionKey = availableSampleSolutionKey;
		
		int compare = firstProblemSolutionKey.compareTo(availableSampleSolutionKey);
		
		if(compare < 0){
			leftHandSideKey = firstProblemSolutionKey;
			rightHandSideKey = availableSampleSolutionKey;
		}
		
		else{
			leftHandSideKey = availableSampleSolutionKey;
			rightHandSideKey = firstProblemSolutionKey;
		}
		
		
		String reviewPairKey = leftHandSideKey + "-" + rightHandSideKey;
		
		try {
			con = DataConnection.getConnection();

			String query = "select review_pair_key from AdminReview"
					+ " where review_pair_key = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, reviewPairKey);

			rs = ps.executeQuery();

			while(rs.next()) {
				result = false;
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
			
		return result;
		
	}

	/**
	 * Returns the list of all solution pairs 
	 * that have been reviewed by the administrator
	 * @return
	 */
	public List<AdminReview> getAllgetAllPairsReviewedByAdmin(){
		
		List <AdminReview>	adminPairsReviewed = new ArrayList <AdminReview>();

		try {
			con = DataConnection.getConnection();

			String query = "select review_pair_key, lhs_solution_key, rhs_solution_key,"
					+ " ranking_judgement"
					+ " from AdminReview"
					+ " order by review_pair_key";

			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				AdminReview adminReview = new AdminReview();
				adminReview.setReviewPairKey(rs.getString(1));
				adminReview.setLhsSolutionKey(rs.getString(2));
				adminReview.setRhsSolutionKey(rs.getString(3));
				adminReview.setRankingJudgement(rs.getString(4));
								
				adminPairsReviewed.add(adminReview);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return adminPairsReviewed;
	}
	
	/**
	 * Fetches all details of
	 * a specific pair of solutions
	 * reviewed by the administrator
	 * @param reviewPairKey
	 * @return
	 */
	public AdminReview getReviewDetails(String reviewPairKey){
		AdminReview adminReview = new AdminReview();
		
		try {
			con = DataConnection.getConnection();

			String query = "select review_pair_key, lhs_solution_key, rhs_solution_key,"
					+ " ranking_judgement, ranking_justification,"
					+ " lhs_good_aspects, rhs_good_aspects,"
					+ " lhs_bad_aspects, rhs_bad_aspects"
					+ " from AdminReview"
					+ " where review_pair_key = ? "
					+ " order by review_pair_key";

			ps = con.prepareStatement(query);
			ps.setString(1, reviewPairKey);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				
				adminReview.setReviewPairKey(rs.getString(1));
				adminReview.setLhsSolutionKey(rs.getString(2));
				adminReview.setRhsSolutionKey(rs.getString(3));
				adminReview.setRankingJudgement(rs.getString(4));
				adminReview.setRankingJustification(rs.getString(5));
				adminReview.setLhsSolutionGoodAspects(rs.getString(6));
				adminReview.setRhsSolutionGoodAspects(rs.getString(7));
				adminReview.setLhsSolutionBadAspects(rs.getString(8));
				adminReview.setRhSolutionBadAspects(rs.getString(9));
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		
		return adminReview;
	}
}
