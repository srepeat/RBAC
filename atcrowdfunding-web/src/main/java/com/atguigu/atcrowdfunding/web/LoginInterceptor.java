package com.atguigu.atcrowdfunding.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.atcrowdfunding.bean.User;

public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * �ڿ��Ʋ�֮ǰ���ҵ���߼�����
	 * �����ķ���ֵ�����Ƿ����ִ��  true������   false������
	 */
		
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//�ж�session�Ƿ��¼
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String path = session.getServletContext().getContextPath();
		if(loginUser == null) {
			response.sendRedirect(path+"/login");
			return false;
		}else {
			return true;
		}
	}

	/**
	 * �ڿ��������֮��ִ�е��߼�
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * �������ͼ��Ⱦ֮��ִ��
	 */

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
