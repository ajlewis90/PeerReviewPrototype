package org.arthur.review.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.arthur.review.database.DataConnection;
import org.arthur.review.model.StudentReview;

public class StudentRankingService {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Returns the number of occurrences where
	 * a given sample solution specific to a problem exists
	 * in the StudentReview table for a speified user
	 * @param problemSampleNumber
	 * @param userName
	 * @return
	 */
	public int getNumberOfProblemSolutionPairOccurrences(String problemSampleNumber, String userName) {
		int rowCount = 0;
		try {
			con = DataConnection.getConnection();

			String query = "select count(*) from StudentReview"
					+ " where review_pair_key like ?"
					+ " and username = ?";


			ps = con.prepareStatement(query);
			ps.setString(1, "%" + problemSampleNumber + "%");
			ps.setString(2, userName);

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
	 * Checks if a given pair of sample solutions
	 * is already existing in the StudentReview table
	 * for a specified user
	 * @param firstProblemSolutionKey
	 * @param availableSampleSolutionKey
	 * @param userName
	 * @return
	 */
	public boolean getCheckForPairNonExistence(String firstProblemSolutionKey, String availableSampleSolutionKey,
			String userName) {
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

			String query = "select review_pair_key from StudentReview"
					+ " where review_pair_key = ?"
					+ " and username = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, reviewPairKey);
			ps.setString(2, userName);

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
	 * that have been reviewed by 
	 * a specific student user
	 * @return
	 */
	public List<StudentReview> getAllgetAllPairsReviewedByStudent(String userName){
		
		List <StudentReview> studentPairsReviewed = new ArrayList <StudentReview>();

		try {
			con = DataConnection.getConnection();

			String query = "select review_pair_key, lhs_solution_key, rhs_solution_key,"
					+ " ranking_judgement"
					+ " from StudentReview"
					+ " where username = ?"
					+ " order by review_pair_key";

			ps = con.prepareStatement(query);
			ps.setString(1, userName);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				StudentReview studentReview = new StudentReview();
				studentReview.setReviewPairKey(rs.getString(1));
				studentReview.setLhsSolutionKey(rs.getString(2));
				studentReview.setRhsSolutionKey(rs.getString(3));
				studentReview.setRankingJudgement(rs.getString(4));
								
				studentPairsReviewed.add(studentReview);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return studentPairsReviewed;
	}
	
	/**
	 * Fetches all details of
	 * a specific pair of solutions
	 * reviewed by a particular student user
	 * @param reviewPairKey
	 * @return
	 */
	public StudentReview getReviewDetails(String reviewPairKey, String userName){
		StudentReview studentReview = new StudentReview();
		
		try {
			con = DataConnection.getConnection();

			String query = "select review_pair_key, lhs_solution_key, rhs_solution_key,"
					+ " ranking_judgement, ranking_justification,"
					+ " lhs_good_aspects, rhs_good_aspects,"
					+ " lhs_bad_aspects, rhs_bad_aspects"
					+ " from StudentReview"
					+ " where review_pair_key =? "
					+ "	and username = ?"
					+ " order by review_pair_key";

			ps = con.prepareStatement(query);
			ps.setString(1, reviewPairKey);
			ps.setString(2, userName);
			
			rs = ps.executeQuery();

			while(rs.next()) {
				
				studentReview.setReviewPairKey(rs.getString(1));
				studentReview.setLhsSolutionKey(rs.getString(2));
				studentReview.setRhsSolutionKey(rs.getString(3));
				studentReview.setRankingJudgement(rs.getString(4));
				studentReview.setRankingJustification(rs.getString(5));
				studentReview.setLhsSolutionGoodAspects(rs.getString(6));
				studentReview.setRhsSolutionGoodAspects(rs.getString(7));
				studentReview.setLhsSolutionBadAspects(rs.getString(8));
				studentReview.setRhSolutionBadAspects(rs.getString(9));
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		
		return studentReview;
	}

	/**
	 * Generates a new review record
	 * for a unique pair of solutions
	 * to a specific problem after a particular student
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
	 * @param userName
	 */
	public void addStudentReviewRecord(String lhsSampleKey, String rhsSampleKey, String pairSampleKey,
			String leftSolutionUploadPath, String rightSolutionUploadPath, String rankingJudgement,
			String rankingJustification, String lhsSolutionGoodAspects, String rhsSolutionGoodAspects,
			String lhsSolutionBadAspects, String rhsSolutionBadAspects, String userName) {
		
		try{

			con = DataConnection.getConnection();
			String query = "INSERT into StudentReview (lhs_solution_key, rhs_solution_key, review_pair_key,"
					+ " ranking_judgement, ranking_justification,"
					+ " leftSolutionUploadPath, rightSolutionUploadPath,"
					+ " lhs_good_aspects, rhs_good_aspects,"
					+ " lhs_bad_aspects, rhs_bad_aspects, username)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";					

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
			ps.setString(12, userName);
			
			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		
	}

	public int getProblemReviewsForUser(String userName, String problemNumber) {
		int rowCount = 0;
		
		try {
			con = DataConnection.getConnection();

			String query = "select count(*) from StudentReview"
					+ " where review_pair_key like ?"
					+ " and username = ?";


			ps = con.prepareStatement(query);
			ps.setString(1, "%" + problemNumber + "%");
			ps.setString(2, userName);

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
}
