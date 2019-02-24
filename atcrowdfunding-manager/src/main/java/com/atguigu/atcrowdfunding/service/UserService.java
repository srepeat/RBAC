package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.User;

public interface UserService {
	/**
	 * 查询全部数据
	 * @return
	 */
	List<User> findAll();
	/**
	 * 查询用户名和密码，完成登录
	 * @return
	 */
	User query4Login(User user);
	
	/**
	 * 查询分页
	 * @param map
	 * @return
	 */
	List<User> pageQueryDate(Map<String, Object> map);
	
	/**
	 * 查询数据总条数
	 * @param map
	 * @return
	 */
	int PageQueryCount(Map<String, Object> map);
	
	/**
	 * 新增用户
	 * @param user
	 */
	void insertUser(User user);
	
	/**
	 * 查询单个id
	 * @param id
	 */
	User queryById(Integer id);
	
	/**
	 * 修改信息
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Integer id);
	
	/**
	 * 删除复选框的数据
	 * @param userid
	 */
	void deletesUser(Integer[] userid);

	
	/**
	 * 删除角色
	 * @param assign
	 */
	   void deleteUserRoles(Integer userid, Integer[] assignroleids);
	  //void deleteUserRoles(Map<String, Object> map);
	  
	/**
	 * 增加角色
	 * @param assign
	//void deleteUserRoles(Assign assign);	 */
	  void insertUserRoles(Map<String, Object> map);
	//void insertUserRoles(Integer userid, Integer[] unassignroleids);
	
	/**
	 * 查询权限id
	 * @param id
	 * @return
	 */
	List<Integer> queryRoleidsByUserid(Integer id);
	

   }