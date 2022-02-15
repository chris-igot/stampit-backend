package com.stmps.groupOne.models;

import java.util.Collections;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stmps.groupOne.utilities.serialization.FollowSerializer;
import com.stmps.groupOne.utilities.serialization.ImageSerializer;
import com.stmps.groupOne.utilities.serialization.UserSerializer;

@Entity
@Table(name = "profiles")
public class Profile {
	@Id
	@GeneratedValue(generator = "id-generator")
	@GenericGenerator(name = "id-generator",
    strategy = "com.stmps.groupOne.utilities.generators.UrlSafeIdGenerator")
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
	private Boolean isPrivate;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonSerialize(using = UserSerializer.class)
	private User user;
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> posts;
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Stamp> stamps;

	@ManyToMany(mappedBy = "amFollowing",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonSerialize(using = FollowSerializer.class)
	private Set<Profile> followers;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		  name = "follows", 
		  joinColumns = @JoinColumn(name = "youId"), 
		  inverseJoinColumns = @JoinColumn(name = "themId"))
	@JsonSerialize(using = FollowSerializer.class)
	private Set<Profile> amFollowing;
	
	@Transient
	private Integer currentlyFollowing;
	
	public Profile() {}
	public Profile(String title, String bio) {
		this.title = title;
		this.bio = bio;
	}
	
	public Profile(String name, String title, String bio, Boolean isPrivate, User user) {
		this.name = name;
		this.title = title;
		this.bio = bio;
		this.isPrivate = isPrivate;
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
	
	public Boolean beingFollowedBy(String other_id) {
		for (Profile profile : this.getFollowers()) {
			if(profile.getId().equals(other_id)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean checkIfFollowing(String other_id) {
		for (Profile profile : this.getAmFollowing()) {
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
		for (Profile profile : this.getAmFollowing()) {
			if(profile.getId().equals(other_id)) {
				this.amFollowing.remove(profile);
				return;
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
		List<Post> posts;

		if(this.posts == null) {
			posts = Collections.emptyList();
		} else {
			posts = this.posts;
		}

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
		Set<Profile> followers;

		if(this.followers == null) {
			followers = Collections.emptySet();
		} else {
			followers = this.followers;
		}

		return followers;
	}
	public void setFollowers(Set<Profile> followers) {
		this.followers = followers;
	}
	public Set<Profile> getAmFollowing() {
		Set<Profile> amFollowing;

		if(this.amFollowing == null) {
			amFollowing = Collections.emptySet();
		} else {
			amFollowing = this.amFollowing;
		}

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
	public List<Stamp> getStamps() {
		List<Stamp> stamps;

		if(this.stamps == null) {
			stamps = Collections.emptyList();
		} else {
			stamps = this.stamps;
		}

		return stamps;
	}
	public void setStamps(List<Stamp> stamps) {
		this.stamps = stamps;
	}
	public Integer getCurrentlyFollowing() {
		return currentlyFollowing;
	}
	public void setCurrentlyFollowing(Integer currentlyFollowing) {
		this.currentlyFollowing = currentlyFollowing;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}	
}
