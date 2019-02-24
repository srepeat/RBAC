package com.atguigu.atcrowdfunding.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.atcrowdfunding.bean.User;

public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * 在控制层之前完成业务逻辑操作
	 * 方法的返回值决定是否继续执行  true：继续   false：拦截
	 */
		
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//判断session是否登录
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
	 * 在控制器完成之后执行的逻辑
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 在完成视图渲染之后执行
	 */

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
