package org.arthur.review.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.arthur.review.model.User;
import org.arthur.review.database.DataConnection;
import org.arthur.review.model.SecurityQuestion;

public class LoginService {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/** User authentication while logging in
	 * to the system
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String username, String password){

		Boolean result = true;
		try{
			con = DataConnection.getConnection();
			String query = "SELECT * FROM users where username = ? and passwd = ?";

			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if(!rs.next()){
				System.out.println("This user does not exist");
				result = false;
			}
		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		return result;
	}

	/** Retrieve all user details
	 * @param username
	 * @return
	 */
	public User getUserInformation(String username) {
		User user = null;

		try {
			con = DataConnection.getConnection();

			String query = "SELECT * FROM users where username = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUser_oid(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setAdmin(rs.getBoolean(4));
				user.setUpi(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setSecurity_question_id(rs.getInt(7));
				user.setSecurity_answer(rs.getString(8));

			}
		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return user;
	}

	/** Maps security question's oid to
	 * the security question's actual description
	 * @param questionOID
	 * @return
	 */
	public String mapQuestionOIDToDescription(int questionOID){
		String questionDescription = null;
		try{

			con = DataConnection.getConnection();
			String query = "select ques_description from Security_question "
					+ "where ques_oid = ?" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, questionOID);

			rs = ps.executeQuery();

			while(rs.next()){
				SecurityQuestion sq = new SecurityQuestion();

				sq.setQues_description(rs.getString(1));
				questionDescription = sq.getQues_description();

			}
		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return questionDescription;

	}

	/** Maps a security question to it's
	 * corresponding oid
	 * @param quesDescription
	 * @return
	 */
	public int mapQuestionDescriptionToOID(String quesDescription) {
		int quesOID = 0;
		try{

			con = DataConnection.getConnection();
			String query = "select ques_oid from Security_question "
					+ "where ques_description = ?" ;

			ps = con.prepareStatement(query);
			ps.setString(1, quesDescription);

			rs = ps.executeQuery();

			while(rs.next()){
				SecurityQuestion sq = new SecurityQuestion();

				sq.setQues_oid(rs.getInt(1));
				quesOID = sq.getQues_oid();
			}
		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}
		return quesOID;
	}

	/** Retrieves list of all available security questions
	 * 
	 * @return
	 */
	public List<SecurityQuestion> getAllSecurityQuestions(){
		List<SecurityQuestion> sqList = new ArrayList<>();

		try {
			con = DataConnection.getConnection();

			String query = "SELECT * FROM Security_Question ORDER BY ques_oid";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				SecurityQuestion sq = new SecurityQuestion();
				sq.setQues_oid(rs.getInt(1));
				sq.setQues_description(rs.getString(2));
				sqList.add(sq);

			}
		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		return sqList;
	}

	/** Creates a new user in the system
	 * 
	 * @param userName
	 * @param password
	 * @param upi
	 * @param email
	 * @param quesDescription
	 * @param answer
	 */
	public void addNewUser(String userName, String password, String upi, String email, int quesOID,
			String answer) {

		try{

			con = DataConnection.getConnection();
			String query = "INSERT into users (username, passwd, upi, email, Security_Question, Security_Answer)"
					+ "VALUES (?,?,?,?,?,?)";					

			ps = con.prepareStatement(query);

			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, upi);
			ps.setString(4, email);
			ps.setInt(5, quesOID);
			ps.setString(6, answer);			

			rs = ps.executeQuery();

		}catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

	}

	/**Retrieves list of all users
	 * existing in the system
	 * @return
	 */
	public List<User> getAllUsers(){

		List<User> usersList = new ArrayList<>();

		try {
			con = DataConnection.getConnection();

			String query = "SELECT username, isAdmin, upi, email FROM Users";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setAdmin(rs.getBoolean(2));
				user.setUpi(rs.getString(3));
				user.setEmail(rs.getString(4));
				usersList.add(user);
			}
			
		} catch (Exception ex) {

		} finally {
			DataConnection.closeConnection(ps, con);
		}

		return usersList;
	}

}
