package com.nysit.stay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nysit.stay.dao.StayTicketDao;
import com.nysit.stay.domain.StayTicket;
import com.nysit.stay.domain.User;
import com.nysit.stay.service.StayTicketService;
import com.nysit.stay.utils.Datas;
import com.nysit.stay.utils.DateUtil;
@Transactional
@Service
public class StayTicketServiceImpl implements StayTicketService {

	@Resource
	private StayTicketDao stayTicketDao;

	@Override
	public int getCount(Integer userid) {
		return stayTicketDao.getCount(userid);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<User> findStayByPage(Integer page, Integer rows,Integer userid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		map.put("userid", userid);
		return stayTicketDao.findStayByPage(map);
	}

	@Override
	public void insertStayTicket(StayTicket st) {
		st.setCreatetime(DateUtil.getCurrentTime());
		stayTicketDao.insertStayTicket(st);
	}

	@Override
	public void deleteStayTicket(Datas ds) {
		stayTicketDao.deleteStayTicket(ds);
	}

	@Override
	public void editStayTicket(StayTicket st) {
		stayTicketDao.editStayTicket(st);
	}

	@Override
	public void editStayTicketStatus(StayTicket st) {
		stayTicketDao.editStayTicketStatus(st);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public StayTicket findBtByPiid(String piid) {
		return stayTicketDao.findBtByPiid(piid);
	}

	@Override
	public void updateAuthStatus(StayTicket st) {
		stayTicketDao.updateAuthStatus(st);
	}

	@Override
	public int getYslxdCount(Integer userid) {
		return stayTicketDao.getYslxdCount(userid);
	}

	@Override
	public List<User> findYslxdByPage(Integer page, Integer rows, Integer userid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		map.put("userid", userid);
		return stayTicketDao.findYslxdByPage(map);
	}

	@Override
	public int getAllSpCount() {
		return stayTicketDao.getAllSpCount();
	}

	@Override
	public List<StayTicket> findAllSpByPage(Integer page, Integer rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		return stayTicketDao.findAllSpByPage(map);
	}

	@Override
	public List<StayTicket> findAllSp() {
		return stayTicketDao.findAllSp();
	}

	@Override
	public List<StayTicket> findSuccessSp() {
		return stayTicketDao.findSuccessSp();
	}

	@Override
	public List<StayTicket> findErrorSp() {
		return stayTicketDao.findErrorSp();
	}

	@Override
	public int getCountByQuery(String queryValue) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("queryValue", queryValue);
		return stayTicketDao.getCountByQuery(map);
	}

	@Override
	public List<User> findStayByQuery(Integer page, Integer rows, String queryValue) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		map.put("queryValue", queryValue);
		return stayTicketDao.findStayByQuery(map);
	}

	@Override
	public List<User> findAllZdls() {
		return stayTicketDao.findAllZdls();
	}
}
