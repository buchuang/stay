package com.nysit.stay.domain;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

public class Menu extends SerializableSerializer{

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private Integer pid;
	private String text;
	private String url;
	private List<Menu> children=new ArrayList<Menu>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
