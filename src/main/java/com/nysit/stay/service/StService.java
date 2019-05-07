package com.nysit.stay.service;

import java.util.List;

import com.nysit.stay.domain.SheTuan;
import com.nysit.stay.domain.Student;
import com.nysit.stay.utils.Datas;

public interface StService {

	List<SheTuan> findAllStName();

	List<Student> findStudentsByStId(Integer page, Integer rows, Integer stid);

	int getCount(Integer stid);

	void addStudent(Student student);

	void deleteStudent(Datas ds);

	void editStudent(Student student);

	List<SheTuan> findsts(Integer page, Integer rows);

	int getCount();

	void addStInfo(SheTuan st);

	void deleteStInfo(Datas ds);

	void editStInfo(SheTuan st);

	List<Student> findStudentsByStIdNoPage(String stid);

	SheTuan findStByid(String stid);

}