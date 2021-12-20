package com.stmps.groupOne.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profiles")
public class Profile {
	@Id
	@GeneratedValue(generator = "id-generator")
	@GenericGenerator(name = "id-generator",
    strategy = "com.stmps.groupOne.utilities.generators.UrlSafeIdGenerator")
	@JsonIgnore
	private String id;
	private String name;
	private String title;
	private String bio;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	private List<Post> posts;
	
	public Profile() {}
	public Profile(String title, String bio) {
		this.title = title;
		this.bio = bio;
	}
	public Profile(String name, String title, String bio, User user) {
		this.name = name;
		this.title = title;
		this.bio = bio;
		this.user = user;
	}
	
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		Date date = new Date();
		this.createdAt = date;
		this.updatedAt = date;
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
