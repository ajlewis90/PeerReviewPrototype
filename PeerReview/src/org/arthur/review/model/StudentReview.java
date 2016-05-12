package org.arthur.review.model;

public class StudentReview {

	private int reviewID;
	private String userName;
	private String lhsSolutionKey;
	private String rhsSolutionKey;
	private String reviewPairKey;
	private String rankingJudgement;
	private String rankingJustification;
	private String lhsSolutionGoodAspects;
	private String rhsSolutionGoodAspects;
	private String lhsSolutionBadAspects;
	private String rhSolutionBadAspects;
	private String leftSolutionUploadPath;
	private String rightSolutionUploadPath;
	
	public int getReviewID() {
		return reviewID;
	}
	
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLhsSolutionKey() {
		return lhsSolutionKey;
	}
	
	public void setLhsSolutionKey(String lhsSolutionKey) {
		this.lhsSolutionKey = lhsSolutionKey;
	}
	
	public String getRhsSolutionKey() {
		return rhsSolutionKey;
	}
	
	public void setRhsSolutionKey(String rhsSolutionKey) {
		this.rhsSolutionKey = rhsSolutionKey;
	}
	
	public String getReviewPairKey() {
		return reviewPairKey;
	}
	
	public void setReviewPairKey(String reviewPairKey) {
		this.reviewPairKey = reviewPairKey;
	}
	
	public String getRankingJudgement() {
		return rankingJudgement;
	}
	
	public void setRankingJudgement(String rankingJudgement) {
		this.rankingJudgement = rankingJudgement;
	}
	
	public String getRankingJustification() {
		return rankingJustification;
	}
	
	public void setRankingJustification(String rankingJustification) {
		this.rankingJustification = rankingJustification;
	}
	
	public String getLhsSolutionGoodAspects() {
		return lhsSolutionGoodAspects;
	}
	
	public void setLhsSolutionGoodAspects(String lhsSolutionGoodAspects) {
		this.lhsSolutionGoodAspects = lhsSolutionGoodAspects;
	}
	
	public String getRhsSolutionGoodAspects() {
		return rhsSolutionGoodAspects;
	}
	
	public void setRhsSolutionGoodAspects(String rhsSolutionGoodAspects) {
		this.rhsSolutionGoodAspects = rhsSolutionGoodAspects;
	}
	
	public String getLhsSolutionBadAspects() {
		return lhsSolutionBadAspects;
	}
	
	public void setLhsSolutionBadAspects(String lhsSolutionBadAspects) {
		this.lhsSolutionBadAspects = lhsSolutionBadAspects;
	}
	
	public String getRhSolutionBadAspects() {
		return rhSolutionBadAspects;
	}
	
	public void setRhSolutionBadAspects(String rhSolutionBadAspects) {
		this.rhSolutionBadAspects = rhSolutionBadAspects;
	}

	public String getLeftSolutionUploadPath() {
		return leftSolutionUploadPath;
	}

	public void setLeftSolutionUploadPath(String leftSolutionUploadPath) {
		this.leftSolutionUploadPath = leftSolutionUploadPath;
	}

	public String getRightSolutionUploadPath() {
		return rightSolutionUploadPath;
	}

	public void setRightSolutionUploadPath(String rightSolutionUploadPath) {
		this.rightSolutionUploadPath = rightSolutionUploadPath;
	}
	
	
}
