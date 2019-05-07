package com.nysit.stay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nysit.stay.dao.AuthHistoryDao;
import com.nysit.stay.domain.AppHistory;
import com.nysit.stay.service.AuthHistoryService;
@Transactional
@Service
public class AuthHistoryServiceImpl implements AuthHistoryService {

	@Resource
	private AuthHistoryDao authHistoryDao;
	@Override
	public void addHistory(AppHistory ah) {
		authHistoryDao.addHistory(ah);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<AppHistory> findHistoryById(Integer id) {
		return authHistoryDao.findHistoryById(id);
	}

	@Override
	public int getSplsCount(Integer id) {
		return authHistoryDao.getSplsCount();
	}

	@Override
	public List<AppHistory> findSplsByPage(Integer page, Integer rows, Integer userid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitIndex", rows);
		map.put("userid", userid);
		return authHistoryDao.findSplsByPage(map);
	}

}
