package com.stmps.groupOne.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "follows")
@IdClass(FollowCompositeKey.class)
public class Follow {
	@Id
	String youId;
	@Id
	String themId;
	Boolean followVerified;
	
	public Follow() {}
	
	public Follow(String ownId, String otherId, Boolean followVerified) {
		this.youId = ownId;
		this.themId = otherId;
		this.followVerified = followVerified;
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
	public Boolean getFollowVerified() {
		return followVerified;
	}

	public void setFollowVerified(Boolean followVerified) {
		this.followVerified = followVerified;
	}
}
