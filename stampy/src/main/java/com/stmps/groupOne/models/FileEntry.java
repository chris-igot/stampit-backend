package com.stmps.groupOne.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "files")
public class FileEntry {
	@Id
	private String id;
	@JsonIgnore
	private String path;
	private String fileName;
	@JsonIgnore
	private String type;
	@JsonIgnore
	private String category;
	@OneToOne(mappedBy = "image")
	@JsonIgnore
	private Profile profile;
	@OneToOne(mappedBy = "image")
	@JsonIgnore
	private Post post;
	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Stamp> stamps;
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	
	public FileEntry() {}
	
	public FileEntry(String fileName, String path, String type, String usage) {
		this.fileName = fileName;
		this.path = path;
		this.type = type;
		this.category = usage;
	}

	public FileEntry(String id, String fileName, String path, String type, String usage) {
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.type = type;
		this.category = usage;
	}
	
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
