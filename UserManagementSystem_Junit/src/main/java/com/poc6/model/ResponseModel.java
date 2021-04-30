package com.poc6.model;

import java.util.List;

public class ResponseModel {
	private boolean isSuccess;
	private String status;
	
	private User user;
	private List<User> users;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public ResponseModel() {
		super();
	}
	public ResponseModel(boolean isSuccess, String status) {
		super();
		this.isSuccess = isSuccess;
		this.status = status;
	}
	
	
	

}
