package com.nysit.stay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.nysit.stay.domain.SheTuan;
import com.nysit.stay.domain.Student;
import com.nysit.stay.utils.Datas;

@Repository
@Mapper
public interface StDao {

	List<SheTuan> findAllStName();

	List<Student> findStudentsByStId(Map<String, Object> map);

	int getCount(Integer stid);

	void addStudent(Student student);

	void deleteStudent(Datas ds);

	void editStudent(Student student);

	List<SheTuan> findsts(Map<String, Object> map);

	int getStCount();

	void addStInfo(SheTuan st);

	void deleteStInfo(Datas ds);

	void deleteStudentByStId(Integer id);

	void editStInfo(SheTuan st);

	List<Student> findStudentsByStIdNoPage(String stid);

	SheTuan findStByid(String stid);

}
