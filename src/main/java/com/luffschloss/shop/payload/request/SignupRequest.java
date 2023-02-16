package com.luffschloss.shop.payload.request;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignupRequest {
	public String UserName;
	public String Password;
	public String Phone;
	public String Email;
	//@JsonFormat(pattern = "yyyy-MM-dd")
	public Date createDate = new Date();
	public boolean userStatus = true;
	public Set<String> ListRoles;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isUserStatus() {
		return userStatus;
	}
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	public Set<String> getListRoles() {
		return ListRoles;
	}
	public void setListRoles(Set<String> listRoles) {
		ListRoles = listRoles;
	}
	public SignupRequest(String userName, String password, String phone, String email, Date createDate,
			boolean userStatus, Set<String> listRoles) {
		super();
		UserName = userName;
		Password = password;
		Phone = phone;
		Email = email;
		this.createDate = createDate;
		this.userStatus = userStatus;
		ListRoles = listRoles;
	}
	
}
