package org.arthur.review.model;

public class Problem {
	
	int PID;
	String problemNumber;
	String problemName;
	String problemDescription;

	public int getPID() {
		return PID;
	}
	
	public void setPID(int PID) {
		this.PID = PID;
	}
	
	public String getProblemNumber() {
		return problemNumber;
	}
	
	public void setProblemNumber(String problemNumber) {
		this.problemNumber = problemNumber;
	}
	
	public String getProblemName() {
		return problemName;
	}
	
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	
	public String getProblemDescription() {
		return problemDescription;
	}
	
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	
}
