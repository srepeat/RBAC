package com.atguigu.atcrowdfunding.service;


import java.util.List;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;

public interface PermissionService {

	/**
	 * 查询顶级节点
	 * @return
	 */
	Permission queryRootPermission();

	/**
	 * 查询子节点
	 * @param id
	 * @return
	 */
	List<Permission> queryChildPermissions(Integer Pid);

	/**
	 * 查询全部
	 * @return
	 */
	List<Permission> queryAll();

	/**
	 * 增加节点
	 * @param permission
	 */
	void insert(Permission permission);

	/**
	 * 修改节点
	 * @param permission
	 */
	void update(Permission permission);

	
	/**
	 * 删除节点
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 查询单个id
	 * @param id
	 * @return
	 */
	Permission queryById(Integer id);

	/**
	 * 查询角色id
	 * @param roleid
	 * @return
	 */
	List<Integer> queryPermissionidsByRoleid(Integer roleid);

	/**
	 * 查询用户权限
	 * @param dbUser
	 * @return
	 */
	List<Permission> queryPermissionsByUser(User dbUser);



}
