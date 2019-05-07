package com.nysit.stay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nysit.stay.dao.UserDao;
import com.nysit.stay.domain.Menu;
import com.nysit.stay.domain.User;
import com.nysit.stay.service.UserService;
import com.nysit.stay.utils.Datas;
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Menu> getMenus() {		
		return userDao.getMenus();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getCount() {
		return userDao.getCount();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)	
	public List<User> findUserByPage(Integer page, Integer rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitno", rows);
		List<User> users=userDao.findUserByPage(map);
		for(User user : users){
			String qx=userDao.findQxByUserid(user.getId());
			user.setQx(qx);
		}
		return users;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void deleteUser(Datas data) {
		userDao.deleteMenuQx(data);
		userDao.deleteUser(data);
	}

	@Override
	public void editUser(User user) {
		userDao.editUser(user);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)	
	public List<Menu> getMenusByUid(Integer uid) {
		return userDao.getMenusByUid(uid);
	}

	@Override
	public void updateqx(User user, String[] qxs) {
		userDao.deleteMenuQxByUserId(user.getId());
		Map<Integer, String> map=new HashMap<Integer, String>();
		for(String qx : qxs){	
			Menu menu=userDao.findMenuByid(qx);
			map.put(menu.getPid(), "");
			user.setQx(qx);
			userDao.updateqx(user);
		}
		map.put(1, "");
		Set<Integer> key=map.keySet();
		for(Integer i :key){
			user.setQx(i.toString());
			userDao.updateqx(user);
		}
	}

	@Override
	public void editPswd(User user) {
		userDao.editPswd(user);
	}

	@Override
	public int getCountByQuery(String queryValue) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("queryValue", queryValue);
		return userDao.getCountByQuery(map);
	}

	@Override
	public List<User> findUserByQuery(String queryValue, Integer page, Integer rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startIndex", (page-1)*rows);
		map.put("limitno", rows);
		map.put("queryValue", queryValue);
		List<User> users=userDao.findUserByQuery(map);
		return users;
	}
	

}
