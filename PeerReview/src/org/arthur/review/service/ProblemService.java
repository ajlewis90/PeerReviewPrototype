package org.arthur.review.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.arthur.review.database.DataConnection;
import org.arthur.review.model.Problem;
import org.arthur.review.model.SampleSolution;

public class ProblemService {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**Adds a new problem to the system.
	 * However, without the sample solutions
	 * @param problemNumber
	 * @param problemName
	 * @param problemDescription
	 */
	public void addNewProblem(String problemNumber, String problemName, String problemDescription){

		try{

			con = DataConnection.getConnection();
			String query = "INSERT into Problem (Problem_Number, Problem_Name, Problem_Description)"
					+ "VALUES (?,?,?)";					

			ps = con.prepareStatement(query);

			ps.setString(1, problemNumber);
			ps.setString(2, problemName);
			ps.setString(3, problemDescription);					

			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

	}

	/**Retrieves a problem according to
	 * it's corresponding problem number which is supplied
	 * as a method paramenter
	 * @param problemNumber
	 * @return
	 */
	public Problem getProblem(String problemNumber){

		Problem problem = null;
		try {
			con = DataConnection.getConnection();

			String query = "SELECT problem_number, problem_name, problem_description FROM Problem"
					+ " where problem_number = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, problemNumber);
			rs = ps.executeQuery();

			while(rs.next()) {
				problem = new Problem();
				problem.setProblemNumber(rs.getString(1));
				problem.setProblemName(rs.getString(2));
				problem.setProblemDescription(rs.getString(3));
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		return problem;
	}

	/**Retrieves list of all 
	 * problems that have been added to the system
	 * @return
	 */
	public List<Problem> getAllProblems(){
		List<Problem> problemsList = new ArrayList<>();

		try {
			con = DataConnection.getConnection();

			String query = "SELECT problem_number, problem_name, problem_description FROM Problem";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Problem problem = new Problem();
				problem.setProblemNumber(rs.getString(1));
				problem.setProblemName(rs.getString(2));
				problem.setProblemDescription(rs.getString(3));
				problemsList.add(problem);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		System.out.println("Size of problems list: " + problemsList.size());
		return problemsList;
	}

	/**Adds a new sample solution for
	 * a given problem which is already added to the system
	 * @param solutionNumber
	 * @param problemNumber
	 * @param problemSolutionPair
	 * @param uploadFullPath
	 */
	public void addNewSampleSolution(String solutionNumber, String problemNumber, String problemSolutionPair, String uploadFullPath){

		try{

			con = DataConnection.getConnection();
			String query = "INSERT into SampleSolutions (Solution_Number, Problem_Number, Problem_Solution_Pair, upload_location)"
					+ "VALUES (?,?,?,?)";					

			ps = con.prepareStatement(query);

			ps.setString(1, solutionNumber);
			ps.setString(2, problemNumber);
			ps.setString(3, problemSolutionPair);
			ps.setString(4, uploadFullPath);					

			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

	}

	/**Retrieves list of all 
	 * sample solutions that have been added to the system
	 * @return
	 */
	public List<SampleSolution> getAllSampleSolutions(){
		List<SampleSolution> sampleSolutionList = new ArrayList<>();

		try {
			con = DataConnection.getConnection();

			String query = "SELECT problem_number, solution_number, problem_solution_Pair,"
					+ " Good_Aspects, Bad_Aspects, upload_location"
					+ " FROM SampleSolutions ORDER BY problem_number, solution_number";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				SampleSolution sampleSolution = new SampleSolution();
				sampleSolution.setProblem_Number(rs.getString(1));
				sampleSolution.setSolution_Number(rs.getString(2));
				sampleSolution.setProblem_Solution_Pair(rs.getString(3));
				sampleSolution.setGood_Apects(rs.getString(4));
				sampleSolution.setBad_Aspects(rs.getString(5));
				sampleSolution.setUpload_Location(rs.getString(6));
				sampleSolutionList.add(sampleSolution);
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		System.out.println("Size of sample solutions list: " + sampleSolutionList.size());
		return sampleSolutionList;
	}

	/**Retrieves a samplesolution as per it's
	 * corresponding problemSolutionPair which 
	 * is supplied as a method parameter
	 * @param problemSolutionPair
	 * @return
	 */
	public SampleSolution getSampleSolution(String problemSolutionPair){

		SampleSolution sampleSolution = null;
		try {
			con = DataConnection.getConnection();

			String query = "SELECT problem_number, solution_number, problem_solution_Pair,"
					+ " Good_Aspects, Bad_Aspects, upload_location"
					+ " FROM SampleSolutions where problem_solution_Pair = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, problemSolutionPair);
			rs = ps.executeQuery();

			while(rs.next()) {
				sampleSolution = new SampleSolution();
				sampleSolution.setProblem_Number(rs.getString(1));
				sampleSolution.setSolution_Number(rs.getString(2));
				sampleSolution.setProblem_Solution_Pair(rs.getString(3));
				sampleSolution.setGood_Apects(rs.getString(4));
				sampleSolution.setBad_Aspects(rs.getString(5));
				sampleSolution.setUpload_Location(rs.getString(6));
			}

		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		return sampleSolution;
	}

	/** Updates the good and bad aspects of Sample Solution
	 * as per the admin's norms
	 * @param problemSolutionPair
	 * @param badAspects
	 * @param goodAspects
	 */
	public void UpdateSampleSolution(String problemSolutionPair, String badAspects, String goodAspects){

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
	 * Returns the actual code of the 
	 * requested sample solution as a String
	 * @param problemSolutionPair
	 * @return
	 */
	public String getSampleSolutionCode(String problemSolutionPairKey){
		BufferedReader reader = null;
		String problemSolutionPair = problemSolutionPairKey;
		StringBuilder sBuilder = new StringBuilder();
		
		SampleSolution sampleSolution = getSampleSolution(problemSolutionPair);	
		//FileReader fileReader = new FileReader(sampleSolution.getUpload_Location());
		try{
			reader = new BufferedReader(new FileReader(sampleSolution.getUpload_Location()));

			
			String line;
			int lineNumber = 0;

			while((line = reader.readLine())!=null){
				lineNumber++;
				sBuilder.append(lineNumber + " " + line + "\n");
			}

			
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(reader != null){
					reader.close();
				}
			}catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		return sBuilder.toString();
	}

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

}
