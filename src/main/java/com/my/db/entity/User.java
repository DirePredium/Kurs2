package com.my.db.entity;

import java.io.Serializable;

import com.my.db.Abstract.DBEntity;

public class User extends DBEntity implements Serializable{

	private UserRole role;
	public String email;
	public String password;
	
	public UserRole getRole() {
        return role;
    }
	public void setRole(UserRole userRole) {
        this.role = userRole;
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
	@Override
	public String toString() {
		return "User"+ getId()+" [role=" + role + ", id=" + getId() + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
