package com.atguigu.atcrowdfunding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public User query4Login(User user) {
		// TODO Auto-generated method stub
		return userDao.query4Login(user);
	}

	public List<User> pageQueryDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.pageQueryDate(map);
	}

	public int PageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.PageQueryCount(map);
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		//封装密码
		//日期格式化
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(dateFormat.format(new Date()));
		user.setUserpswd("123456");
		userDao.insertUser(user);
	}

	public User queryById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.queryById(id);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	public void deletesUser(Integer[] userid) {
		// TODO Auto-generated method stub
		userDao.deletesUser(userid);
	}

	public void insertUserRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.insertUserRoles(map);
	}

	/*public void deleteUserRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.deleteUserRoles(map);
	}*/

	public void deleteUserRoles(Integer userid, Integer[] assignroleids) {
		// TODO Auto-generated method stub
		System.out.println("userimpl");
		userDao.deleteUserRoles(userid,assignroleids);
	}

	/*public void insertUserRoles(Integer userid, Integer[] unassignroleids) {
		// TODO Auto-generated method stub
		userDao.insertUserRoles(userid,unassignroleids);
	}*/

	public List<Integer> queryRoleidsByUserid(Integer id) {
		// TODO Auto-generated method stub
		return userDao.queryRoleidsByUserid(id);
	}

}
