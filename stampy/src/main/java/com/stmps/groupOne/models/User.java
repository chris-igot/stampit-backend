package com.stmps.groupOne.models;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stmps.groupOne.utilities.serialization.RoleSerializer;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 2, message = "Name must have at least 2 characters")
	private String username;
	@Email(message = "Email must be valid")
	@NotBlank
	private String email;
	@Size(min = 8, message = "Password must at least be 8 characters!")
	@JsonIgnore
	private String password;
	@Transient
	@JsonIgnore
	private String passwordConfirm;
	@Transient
	@JsonIgnore
	private Boolean isPrivate;	//Used for profile privacy on registration
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Profile profile;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		  name = "user_has_roles", 
		  joinColumns = @JoinColumn(name = "user_id"), 
		  inverseJoinColumns = @JoinColumn(name = "role"))
	@JsonSerialize(using = RoleSerializer.class)
	private Set<Role> roles;
	
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
	
	public void addRole(Role role) {
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		this.roles.add(role);
	}
	
	public void removeRole(String rollId) {
		for (Iterator<Role> iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			if(role.getId().equals(rollId)) {
				this.roles.remove(role);
				break;
			}
		}
	}
	
	public Boolean hasRole(String rollId) {
		Boolean output = false;

		for (Iterator<Role> iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();

			if(role.getId().equals(rollId)) {
				output = true;
				break;
			}
		}
		
		return output;
	}
	
	public Role getRole(String rollId) {
		Role output = null;
		for (Iterator<Role> iterator = this.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			if(role.getId().equals(rollId)) {
				output = role;
				break;
			}
		}
		
		return output;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Set<Role> getRoles() {
		Set<Role> roles;
		
		if(this.roles == null) {
			roles = Collections.emptySet();
		} else {
			roles = this.roles;
		}

		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}
