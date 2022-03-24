package com.shopmall.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name=Role.ROLE)
public class Role {

	/**
	 *
	 */
	static final String ROLE = "role";

	@Id
	@GeneratedValue
	private long id;
	
	private String nameRole;

	@JsonIgnore
	@ManyToMany(mappedBy = ROLE)
	private Set<User> User;

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public Set<User> getUser() {
		return User;
	}

	public void setUser(Set<User> User) {
		this.User = User;
	}
	
	public Role(String nameRole) {
		this.nameRole = nameRole;
	}
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
}
