package com.nysit.stay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nysit.stay.domain.User;

@Controller
@RequestMapping("/ui")
public class UIController {

	@RequestMapping("allsp")
	public String allsp(){
		return "jsp/stay/allsp";
	}
	
	@RequestMapping("/editpswd")
	public String editpswd(){
		return "jsp/user/editpswd";
	}
	
	@RequestMapping("/spls")
	public String spls(){
		return "jsp/stay/spls";
	}
	@RequestMapping("/yslxd")
	public String yslxd(){
		return "jsp/stay/yslxd";
	}
	@RequestMapping("/stgl")
	public String stgl(){
		return "jsp/student/stList";
	}
	@RequestMapping("/stList")
	public String stList(){
		return "jsp/student/studentList";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "jsp/main";
	}
	@RequestMapping("/userMessage")
	public String userMessage(){
		return "jsp/user/userlist";
	}
	@RequestMapping("/procList")
	public String procList(){
		return "jsp/process/procList";
	}
	@RequestMapping("/stayticket")
	public String stayticket(){
		return "jsp/stay/staylist";
	}
	@RequestMapping("/monitor")
	public String monitor(){
		return "jsp/process/monitor";
	}
	@RequestMapping("/auth")
	public String auth(){
		return "jsp/stay/auth";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "jsp/index";
	}
}
