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
	
	//��ת��¼ҳ��
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	//��ת����ҳ��
	@RequestMapping("/error")
	public String error() {
		
		return "/error";
	}
	
	//��ת��ҳ��
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
	
	//�˳���¼
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//ʹ��session���session�е���Ϣ
		session.invalidate();
		return "redirect:login";
	}
	
	
	//�ύ��
	@RequestMapping("/doAJAXLogin")
	public @ResponseBody Object doAJAXLogin(User user,HttpSession session) {
		
		User dbUser = userService.query4Login(user);
		
		//���ؽ����
		AJAXResult result = new AJAXResult();
		if(dbUser != null) {
			//��session�Ự�浽session����
			session.setAttribute("loginUser", dbUser);
			// ��ȡ�û�Ȩ����Ϣ
			List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
			//��ȡ�ڵ�
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			
			Permission root = null;
			Set<String> uriSet = new HashSet<String>();
			for (Permission permission : permissions) {
				permissionMap.put(permission.getId(), permission);
				
				//�ж��Ƿ��û����Ȩ��
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
	 * ��ѯ�û���ʵ�ֵ�¼
	 * ��ȡ�������ַ�ʽ
	 * 1��ʹ����ԭ����http��servlet==>request��ʽ
	 * 2��ʹ�ò������ݸ�����
	 * 3�������е�name���Է�װΪһ��bean
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(User user,Model model) {
		
		/**
		 * ���������ֽ������룬�����������ʱĬ�ϲ���ISO8859-1�ı��뷽ʽ
		 * ��������Ҫ����ת��ΪUTF-8�ķ�ʽ����Ҫת��֮ǰ���ַ������룬Ȼ��ת��Ϊ�µı����ʽ
		 */
		/*try {
			String loginacct = user.getLoginacct();
			System.out.println(loginacct);
			byte[] bs = loginacct.getBytes("ISO8859-1");
			//�ٴν����뷵�ظ��û���
			loginacct= new String(bs, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//���÷�����ѯ
		User dbUser = userService.query4Login(user);
		//�ж�dbUser�Ƿ�Ϊ��
		if(dbUser != null) {
			//��¼�ɹ�
			//System.out.println(dbUser.getLoginacct()+"<==>"+dbUser.getUserpswd());
			return "main";
		}else {
			//��¼ʧ�ܣ���ʾ������Ϣ
			String message = "�û��������������";
			model.addAttribute("message", message);
			return "redirect:login";
		}
	}
}
