package com.stmps.groupOne.models;

import java.io.Serializable;

public class FollowCompositeKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4019621076908227921L;
	private String youId;
	private String themId;
	
	public FollowCompositeKey()	{}
	
	public FollowCompositeKey(String youId, String themId) {
		this.youId = youId;
		this.themId = themId;
	}

	public String getYouId() {
		return youId;
	}
	public void setYouId(String youId) {
		this.youId = youId;
	}
	public String getThemId() {
		return themId;
	}
	public void setThemId(String themId) {
		this.themId = themId;
	}
}
