package com.nysit.stay.service;

import java.util.List;

import com.nysit.stay.domain.Menu;
import com.nysit.stay.domain.User;
import com.nysit.stay.utils.Datas;


public interface UserService {

	public List<User> findAll();

	public User findUserByName(String username);

	public List<Menu> getMenus();

	public int getCount();

	public List<User> findUserByPage(Integer page, Integer rows);

	public void saveUser(User user);

	public void deleteUser(Datas data);

	public void editUser(User user);

	public List<Menu> getMenusByUid(Integer id);

	public void updateqx(User user, String[] qxs);

	public void editPswd(User user);

	public int getCountByQuery(String queryValue);

	public List<User> findUserByQuery(String queryValue, Integer page, Integer rows);
}