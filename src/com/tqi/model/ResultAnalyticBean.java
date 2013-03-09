package com.tqi.model;

import java.io.Serializable;

public class ResultAnalyticBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long mascotID;
	
	private String mascotName;
	
	private long qtdVotes;
	
	private double percent;

	public long getMascotID() {
		return mascotID;
	}

	public void setMascotID(long mascotID) {
		this.mascotID = mascotID;
	}

	public String getMascotName() {
		return mascotName;
	}
	
	public void setMascotName(String mascotName) {
		this.mascotName = mascotName;
	}

	public long getQtdVotes() {
		return qtdVotes;
	}

	public void setQtdVotes(long qtdVotes) {
		this.qtdVotes = qtdVotes;
	}
	
	public double getPercent() {
		return percent;
	}
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
}
