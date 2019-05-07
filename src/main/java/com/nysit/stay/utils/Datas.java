package com.nysit.stay.utils;

import java.util.List;

import com.nysit.stay.domain.SheTuan;
import com.nysit.stay.domain.StayTicket;
import com.nysit.stay.domain.Student;
import com.nysit.stay.domain.User;

public class Datas {

	private List<User> users;
	private List<String> pdids;
	private List<String> deployids;
	private List<StayTicket> stays;
	private List<Student> students;
	private List<SheTuan> sts;
	
	
	public List<SheTuan> getSts() {
		return sts;
	}

	public void setSts(List<SheTuan> sts) {
		this.sts = sts;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<StayTicket> getStays() {
		return stays;
	}

	public void setStays(List<StayTicket> stays) {
		this.stays = stays;
	}

	public List<String> getDeployids() {
		return deployids;
	}

	public void setDeployids(List<String> deployids) {
		this.deployids = deployids;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<String> getPdids() {
		return pdids;
	}

	public void setPdids(List<String> pdids) {
		this.pdids = pdids;
	}
	
	
}
