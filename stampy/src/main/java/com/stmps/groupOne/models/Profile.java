package com.stmps.groupOne.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stmps.groupOne.utilities.serialization.ImageSerializer;

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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	@JsonSerialize(using = ImageSerializer.class)
	private FileEntry image;
	@Size(max = 50)
	private String title;
	@Size(max = 250)
	private String bio;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	private List<Post> posts;

	@ManyToMany(mappedBy = "amFollowing",cascade = CascadeType.ALL)
	
	@JsonIgnore
	private Set<Profile> followers;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		  name = "follows", 
		  joinColumns = @JoinColumn(name = "you_id"), 
		  inverseJoinColumns = @JoinColumn(name = "them_id"))
	@JsonIgnore
	private Set<Profile> amFollowing;
	
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
	
	public Boolean checkIfBeingFollowed(String other_id) {
		for (Profile profile : this.followers) {
			if(profile.getId().equals(other_id)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean checkIfFollowing(String other_id) {
		for (Profile profile : this.amFollowing) {
			if(profile.getId().equals(other_id)) {
				return true;
			}
		}
		return false;
	}
	public Boolean checkIfFollowing(Profile otherProfile) {
		return this.amFollowing.contains(otherProfile);
	}
	
	public void follow(Profile otherProfile) {
		this.amFollowing.add(otherProfile);
	}
	public void unfollow(String other_id) {
		for (Profile profile : this.amFollowing) {
			if(profile.getId().equals(other_id)) {
				this.amFollowing.remove(profile);
			}
		}
	}
	public void unfollow(Profile otherProfile) {
		if(checkIfFollowing(otherProfile.getId())) {
			this.amFollowing.remove(otherProfile);
		}
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
	public Set<Profile> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<Profile> followers) {
		this.followers = followers;
	}
	public Set<Profile> getAmFollowing() {
		return amFollowing;
	}
	public void setAmFollowing(Set<Profile> amFollowing) {
		this.amFollowing = amFollowing;
	}
	public FileEntry getImage() {
		return image;
	}
	public void setImage(FileEntry image) {
		this.image = image;
	}
	
}