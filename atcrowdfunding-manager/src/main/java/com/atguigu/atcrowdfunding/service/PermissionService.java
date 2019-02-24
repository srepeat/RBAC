package com.atguigu.atcrowdfunding.service;


import java.util.List;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;

public interface PermissionService {

	/**
	 * ��ѯ�����ڵ�
	 * @return
	 */
	Permission queryRootPermission();

	/**
	 * ��ѯ�ӽڵ�
	 * @param id
	 * @return
	 */
	List<Permission> queryChildPermissions(Integer Pid);

	/**
	 * ��ѯȫ��
	 * @return
	 */
	List<Permission> queryAll();

	/**
	 * ���ӽڵ�
	 * @param permission
	 */
	void insert(Permission permission);

	/**
	 * �޸Ľڵ�
	 * @param permission
	 */
	void update(Permission permission);

	
	/**
	 * ɾ���ڵ�
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * ��ѯ����id
	 * @param id
	 * @return
	 */
	Permission queryById(Integer id);

	/**
	 * ��ѯ��ɫid
	 * @param roleid
	 * @return
	 */
	List<Integer> queryPermissionidsByRoleid(Integer roleid);

	/**
	 * ��ѯ�û�Ȩ��
	 * @param dbUser
	 * @return
	 */
	List<Permission> queryPermissionsByUser(User dbUser);



}
