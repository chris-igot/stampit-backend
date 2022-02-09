package com.stmps.groupOne.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "follows")
@IdClass(FollowCompositeKey.class)
public class Follow {
	@Id
	String youId;
	@Id
	String themId;
	Boolean followGranted;
	
	public Follow() {}
	
	public Follow(String ownId, String otherId, Boolean followGranted) {
		this.youId = ownId;
		this.themId = otherId;
		this.followGranted = followGranted;
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
	public Boolean getFollowGranted() {
		return followGranted;
	}
	public void setFollowGranted(Boolean followGranted) {
		this.followGranted = followGranted;
	}
}
