package com.stmps.groupOne.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stmps.groupOne.utilities.serialization.ImageSerializer;

@Entity
@Table(name = "stamps")
public class Stamp {
	@Id
	@GeneratedValue(generator = "id-generator")
	@GenericGenerator(name = "id-generator",
    strategy = "com.stmps.groupOne.utilities.generators.UrlSafeIdGenerator")
	private String id;
	private Integer x;
	private Integer y;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	@JsonSerialize(using = ImageSerializer.class)
	private FileEntry image;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	@JsonIgnore
	private Profile profile;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	@JsonIgnore
	private Post post;
	
	private Date createdAt;
	private Date updatedAt;
	
	public Stamp() {};
	
	public Stamp(Profile profile, FileEntry image, Post post, Integer x, Integer y) {
		this.profile = profile;
		this.image = image;
		this.post = post;
		this.x = x;
		this.y = y;
	};
	
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
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public FileEntry getImage() {
		return image;
	}
	public void setImage(FileEntry image) {
		this.image = image;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
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
	
}
