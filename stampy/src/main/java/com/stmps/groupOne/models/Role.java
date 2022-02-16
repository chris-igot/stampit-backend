package com.stmps.groupOne.models;

import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	private String id;
	
	@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users;
	
	public Role() {}
	
	public Role(String role) {
		this.id = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String role) {
		this.id = role;
	}

	public Set<User> getUsers() {
		Set<User> users;
		
		if(this.users == null) {
			users = Collections.emptySet();
		} else {
			users = this.users;
		}
		
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
