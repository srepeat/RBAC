package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Role;

public interface RoleService {

	/**
	 * ��ѯȫ��
	 * @return
	 */
	List<Role> queryAll();

	/**
	 * ��ѯҳ��
	 * @param map
	 * @return
	 */
	List<Role> pageQueryData(Map<String, Object> map);

	/**
	 * ��ѯ����
	 * @param map
	 * @return
	 */
	int pageQueryCount(Map<String, Object> map);

	/**
	 * ����Ȩ��
	 * @param paramMap
	 */
	void insertRolePermission(Map<String, Object> paramMap);

	/**
	 * ��ѯ��ɫid
	 * @param id
	 * @return
	 */
	Role queryById(Integer id);

	/**
	 * �޸Ľ�ɫ��Ϣ
	 * @param role
	 */
	void queryAndUpdate(Role role);

	/**
	 * ɾ����ɫ��Ϣ
	 * @param id
	 */
	void queryAndDelete(Integer id);

}
