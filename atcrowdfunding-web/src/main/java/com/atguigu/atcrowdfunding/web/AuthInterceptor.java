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
	 * 1����ȡ���·���Ƿ����
	 * 2��getContextPath���·���õ�ַ ==>  ��Ŀ����+ӳ��·��
	 * 3��ʹ��set����ȥ��,��ֹ�ظ�������ӳ���ַ
	 * 4��������������еĶ�����Ҫ�ǻ�ȡurl
	 *      �ж����·���Ƿ�Ϊnull���߿��ַ�������������ھ�set.add(·��+��ȡ���·��)
	 * 5����login��session�洢��key��ȡ�������ж��Ƿ�������·��     
	 * 6���ٴ��ж����·�����Ƿ�������ӳ���ַ
	 *     �ж�������ھͷ���  �����û�о��ض���һ������ҳ��     
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//�������·��
		String uri = request.getRequestURI();
		String path = request.getSession().getServletContext().getContextPath();
		
		//�жϵ�ǰ·���Ƿ���ҪȨ����֤
		//��ѯ���е�·������
		List<Permission> permissions = permissionService.queryAll();
		Set<String> uriSet = new HashSet<String>();
		
		for (Permission permission : permissions) {
			if(permission.getUrl() != null && !"".equals(permission.getUrl())) {
				uriSet.add(path + permission.getUrl());
			}
		}
		
		if (uriSet.contains(uri)) {
			//Ȩ����֤�жϵ�ǰ�û��Ƿ�ӵ��Ȩ��
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
