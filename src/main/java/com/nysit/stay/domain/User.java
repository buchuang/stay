package com.nysit.stay.domain;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

public class User extends SerializableSerializer{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String usercode;
	private String password;
	private String username;
	private String phone;
	private String identify;
	
	private String qx;
	

	public String getQx() {
		return qx;
	}
	public void setQx(String qx) {
		this.qx = qx;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
