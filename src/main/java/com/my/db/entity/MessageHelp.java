package com.my.db.entity;

import com.my.db.Abstract.DBEntity;

public class MessageHelp extends DBEntity{
	
	public MessageHelpStatus status;
	public String email;
	public String message;
	//public User user;
	
	public MessageHelpStatus getStatus() {
		return status;
	}
	public void setStatus(MessageHelpStatus status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageHelp"+ getId()+" [status=" + status + ", email=" + email + ", message=" + message + "]";
	}
}
