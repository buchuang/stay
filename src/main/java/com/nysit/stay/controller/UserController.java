package com.nysit.stay.controller;



import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nysit.stay.domain.Menu;
import com.nysit.stay.domain.User;
import com.nysit.stay.service.UserService;
import com.nysit.stay.utils.Datas;
import com.nysit.stay.utils.Result;


@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/loadByQuery")
	public Object loadByQuery(String queryValue,Integer page,Integer rows){
		int total=userService.getCountByQuery(queryValue);
		List<User> datas=userService.findUserByQuery(queryValue,page,rows);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
	
	@RequestMapping("/editPswd")
	public Object editPswd(String password,HttpSession session){
		Result result=new Result();
		try {
			User user=(User) session.getAttribute("user");
			user.setPassword(password);
			userService.editPswd(user);
			result.setSuccess(true);
			session.removeAttribute("user");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/validatePswd")
	public Object validatePswd(String password,HttpSession session){
		Result result=new Result();
		try {
			User user=(User) session.getAttribute("user");
			if(!user.getPassword().equals(password)){
				result.setSuccess(false);
			}else{
				result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/editUser")
	public Object editUser(User user,String qx){
		Result result=new Result();
		try {
			String[] qxs=qx.split("#");
			userService.updateqx(user,qxs);
			userService.editUser(user);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	

	@RequestMapping("/deleteUser")
	public Object deleteUser(Datas data){
		Result result=new Result();
		try {
			userService.deleteUser(data);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/addUser")
	public Object addUser(User user,String qx){
		Result result=new Result();
		try {
			User user2=userService.findUserByName(user.getUsercode());
			if(user2==null){
				userService.saveUser(user);
				String[] qxs=qx.split("#");
				userService.updateqx(user,qxs);
			}else {
				result.setSuccess(false);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	

	@RequestMapping("/menuList")
	public Object menuList(HttpSession session){
		User user=(User) session.getAttribute("user");
		List<Menu> menus=userService.getMenusByUid(user.getId());
		Map<Integer, Menu> map=new HashMap<Integer, Menu>();
		List<Menu> userList=new ArrayList<Menu>();
		for(Menu menu:menus){
			map.put(menu.getId(), menu);
		}
		for(Menu menu:menus){
			Menu child=menu;
			Integer pid=child.getPid();
			if (pid==0) {
				userList.add(menu);
			} else {
				Menu parent=map.get(pid);
				parent.getChildren().add(child);
			}
		}
		return userList;
	}

	@RequestMapping(value="/login")
	public Object login(String usercode,String password,HttpSession session){
		Result result=new Result();
		try {
			User user=userService.findUserByName(usercode);
			if(user!=null&&password.equals(user.getPassword())){
				session.setAttribute("user", user);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping(value="/userList")
	public Object userList(Integer page,Integer rows,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		int total=userService.getCount();
		List<User> datas=userService.findUserByPage(page,rows);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", datas);	
		return map;
	}
}
