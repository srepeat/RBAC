package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Role;

public interface RoleService {

	/**
	 * 查询全部
	 * @return
	 */
	List<Role> queryAll();

	/**
	 * 查询页数
	 * @param map
	 * @return
	 */
	List<Role> pageQueryData(Map<String, Object> map);

	/**
	 * 查询数量
	 * @param map
	 * @return
	 */
	int pageQueryCount(Map<String, Object> map);

	/**
	 * 插入权限
	 * @param paramMap
	 */
	void insertRolePermission(Map<String, Object> paramMap);

	/**
	 * 查询角色id
	 * @param id
	 * @return
	 */
	Role queryById(Integer id);

	/**
	 * 修改角色信息
	 * @param role
	 */
	void queryAndUpdate(Role role);

	/**
	 * 删除角色信息
	 * @param id
	 */
	void queryAndDelete(Integer id);

}
