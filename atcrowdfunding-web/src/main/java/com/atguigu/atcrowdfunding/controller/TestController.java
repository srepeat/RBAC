package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userService;
	
	//查询全部用户
	@RequestMapping("/findAll")
	public @ResponseBody Object findAll() {
		List<User> user = userService.findAll();
		return user;
	}
	
	//跳转首页
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}
	
	//打印对象字符串
	@RequestMapping("/json")
	public @ResponseBody Object json() {
		Map map = new HashMap();
		map.put("username", "zhangsan");
		return map;
	}
	
}
