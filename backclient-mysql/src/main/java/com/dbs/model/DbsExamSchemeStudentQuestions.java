package com.dbs.model;

public class DbsExamSchemeStudentQuestions {
	
	private String schemeStudentId;
	private String questionsId;
	private String singleScore;
	private String userId;
	private String studentAnswer;
	public String getSchemeStudentId() {
		return schemeStudentId;
	}
	public void setSchemeStudentId(String schemeStudentId) {
		this.schemeStudentId = schemeStudentId;
	}
	public String getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(String questionsId) {
		this.questionsId = questionsId;
	}
	public String getSingleScore() {
		return singleScore;
	}
	public void setSingleScore(String singleScore) {
		this.singleScore = singleScore;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	@Override
	public String toString() {
		return "DbsExamSchemeStudentQuestions [schemeStudentId=" + schemeStudentId + ", questionsId=" + questionsId
				+ ", singleScore=" + singleScore + ", userId=" + userId + ", studentAnswer=" + studentAnswer + "]";
	}
	
}
