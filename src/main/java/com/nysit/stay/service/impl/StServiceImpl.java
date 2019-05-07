package com.nysit.stay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nysit.stay.dao.StDao;
import com.nysit.stay.domain.SheTuan;
import com.nysit.stay.domain.Student;
import com.nysit.stay.service.StService;
import com.nysit.stay.utils.Datas;

@Service
@Transactional
public class StServiceImpl implements StService {

	@Resource
	private StDao stDao;

	@Override
	public List<SheTuan> findAllStName() {
		return stDao.findAllStName();
	}

	@Override
	public List<Student> findStudentsByStId(Integer page, Integer rows, Integer stid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		map.put("stid", stid);
		return stDao.findStudentsByStId(map);
	}

	@Override
	public int getCount(Integer stid) {
		return stDao.getCount(stid);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void addStudent(Student student) {
		stDao.addStudent(student);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void deleteStudent(Datas ds) {
		stDao.deleteStudent(ds);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void editStudent(Student student) {
		stDao.editStudent(student);
	}

	@Override
	public List<SheTuan> findsts(Integer page, Integer rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		return stDao.findsts(map);
	}

	@Override
	public int getCount() {
		return stDao.getStCount();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void addStInfo(SheTuan st) {
		stDao.addStInfo(st);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void deleteStInfo(Datas ds) {
		List<SheTuan> sts=ds.getSts();
		for(SheTuan st: sts){
			Integer id=st.getId();
			stDao.deleteStudentByStId(id);
		}
		
		stDao.deleteStInfo(ds);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void editStInfo(SheTuan st) {
		stDao.editStInfo(st);
	}

	@Override
	public List<Student> findStudentsByStIdNoPage(String stid) {
		return stDao.findStudentsByStIdNoPage(stid);
	}

	@Override
	public SheTuan findStByid(String stid) {
		return stDao.findStByid(stid);
	}
}
