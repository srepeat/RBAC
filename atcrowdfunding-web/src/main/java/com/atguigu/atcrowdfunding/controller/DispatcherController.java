package com.atguigu.atcrowdfunding.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AJAXResult;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	//跳转登录页面
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	//跳转错误页面
	@RequestMapping("/error")
	public String error() {
		
		return "/error";
	}
	
	//跳转主页面
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
	
	//退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//使用session清空session中的信息
		session.invalidate();
		return "redirect:login";
	}
	
	
	//提交表单
	@RequestMapping("/doAJAXLogin")
	public @ResponseBody Object doAJAXLogin(User user,HttpSession session) {
		
		User dbUser = userService.query4Login(user);
		
		//返回结果集
		AJAXResult result = new AJAXResult();
		if(dbUser != null) {
			//将session会话存到session域中
			session.setAttribute("loginUser", dbUser);
			// 获取用户权限信息
			List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
			//获取节点
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			
			Permission root = null;
			Set<String> uriSet = new HashSet<String>();
			for (Permission permission : permissions) {
				permissionMap.put(permission.getId(), permission);
				
				//判断是否用户这个权限
				if(permission.getUrl() != null && !"".equals(permission.getUrl()) ) {
					uriSet.add(session.getServletContext().getContextPath()+permission.getUrl());
				}
			}
			session.setAttribute("authUriSet", uriSet);
			
			for (Permission permission : permissions) {
				Permission child = permission;
				if (child.getPid() == 0) {
					root = permission;
				}else {
					Permission parent = permissionMap.get(child.getPid());
					parent.getChildren().add(child);
				}
			}
			session.setAttribute("rootPermission", root);
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			
		}
		return result;
	}
	
	
	/**
	 * 查询用户，实现登录
	 * 获取表单的三种方式
	 * 1、使用最原生的http的servlet==>request方式
	 * 2、使用参数传递给方法
	 * 3、讲表单中的name属性封装为一个bean
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(User user,Model model) {
		
		/**
		 * 对于输入字节码乱码，浏览器在输入时默认采用ISO8859-1的编码方式
		 * 而我们需要将它转换为UTF-8的方式就需要转换之前的字符集编码，然后转换为新的编码格式
		 */
		/*try {
			String loginacct = user.getLoginacct();
			System.out.println(loginacct);
			byte[] bs = loginacct.getBytes("ISO8859-1");
			//再次将编码返回给用户名
			loginacct= new String(bs, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//调用服务层查询
		User dbUser = userService.query4Login(user);
		//判断dbUser是否为空
		if(dbUser != null) {
			//登录成功
			//System.out.println(dbUser.getLoginacct()+"<==>"+dbUser.getUserpswd());
			return "main";
		}else {
			//登录失败，提示错误信息
			String message = "用户名或者密码错误";
			model.addAttribute("message", message);
			return "redirect:login";
		}
	}
}
