package com.nysit.stay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.nysit.stay.domain.Menu;
import com.nysit.stay.domain.User;
import com.nysit.stay.utils.Datas;

@Repository
@Mapper
public interface UserDao {

	public List<User> findAll();

	public User findUserByName(String username);

	public List<Menu> getMenus();

	public int getCount();

	public List<User> findUserByPage(Map<String, Object> map);

	public void saveUser(User user);

	public void deleteUser(Datas data);

	public void editUser(User user);

	public List<Menu> getMenusByUid(Integer uid);

	public void updateqx(User user);

	public Menu findMenuByid(String qx);

	public void deleteMenuQx(Datas data);

	public String findQxByUserid(Integer id);

	public void deleteMenuQxByUserId(Integer id);

	public void editPswd(User user);

	public int getCountByQuery(Map<String, Object> map);

	public List<User> findUserByQuery(Map<String, Object> map);
}
