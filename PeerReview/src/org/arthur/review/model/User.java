package org.arthur.review.model;

public class User {
	
	private int user_oid;
	private String userName;
	private String password;
	private boolean isAdmin;
	private String upi;
	private String Email;
	private int security_question_id;
	private String security_answer;
	
	public int getUser_oid() {
		return user_oid;
	}
	
	public void setUser_oid(int user_oid) {
		this.user_oid = user_oid;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getUpi() {
		return upi;
	}
	
	public void setUpi(String upi) {
		this.upi = upi;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public int getSecurity_question_id() {
		return security_question_id;
	}
	
	public void setSecurity_question_id(int security_question_id) {
		this.security_question_id = security_question_id;
	}
	
	public String getSecurity_answer() {
		return security_answer;
	}
	
	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}
	
	
	
}
