package com.atguigu.atcrowdfunding.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 1、获取这个路径是否存在
	 * 2、getContextPath相对路径得地址 ==>  项目名称+映射路径
	 * 3、使用set集合去重,防止重复出来的映射地址
	 * 4、遍历这个集合中的对象主要是获取url
	 *      判断这个路径是否为null或者空字符串，如果不存在就set.add(路径+获取这个路径)
	 * 5、讲login中session存储得key获取到，来判断是否存在这个路径     
	 * 6、再次判断这个路径中是否包含这个映射地址
	 *     判断如果存在就返回  ，如果没有就重定向一个错误页面     
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//获得请求路径
		String uri = request.getRequestURI();
		String path = request.getSession().getServletContext().getContextPath();
		
		//判断当前路径是否需要权限验证
		//查询所有的路径集合
		List<Permission> permissions = permissionService.queryAll();
		Set<String> uriSet = new HashSet<String>();
		
		for (Permission permission : permissions) {
			if(permission.getUrl() != null && !"".equals(permission.getUrl())) {
				uriSet.add(path + permission.getUrl());
			}
		}
		
		if (uriSet.contains(uri)) {
			//权限验证判断当前用户是否拥有权限
			Set<String> authUriSet = (Set<String>)request.getSession().getAttribute("authUriSet");
			if(authUriSet.contains(uri)) {
				return true;
			}else {
				response.sendRedirect(path + "/error");
				
				return false;
			}
		}else {
			return true;
		}
		
	}

	

}
