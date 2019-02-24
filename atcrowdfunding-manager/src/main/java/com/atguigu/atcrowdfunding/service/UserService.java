package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.User;

public interface UserService {
	/**
	 * ��ѯȫ������
	 * @return
	 */
	List<User> findAll();
	/**
	 * ��ѯ�û��������룬��ɵ�¼
	 * @return
	 */
	User query4Login(User user);
	
	/**
	 * ��ѯ��ҳ
	 * @param map
	 * @return
	 */
	List<User> pageQueryDate(Map<String, Object> map);
	
	/**
	 * ��ѯ����������
	 * @param map
	 * @return
	 */
	int PageQueryCount(Map<String, Object> map);
	
	/**
	 * �����û�
	 * @param user
	 */
	void insertUser(User user);
	
	/**
	 * ��ѯ����id
	 * @param id
	 */
	User queryById(Integer id);
	
	/**
	 * �޸���Ϣ
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * ɾ���û�
	 * @param id
	 */
	void deleteUser(Integer id);
	
	/**
	 * ɾ����ѡ�������
	 * @param userid
	 */
	void deletesUser(Integer[] userid);

	
	/**
	 * ɾ����ɫ
	 * @param assign
	 */
	   void deleteUserRoles(Integer userid, Integer[] assignroleids);
	  //void deleteUserRoles(Map<String, Object> map);
	  
	/**
	 * ���ӽ�ɫ
	 * @param assign
	//void deleteUserRoles(Assign assign);	 */
	  void insertUserRoles(Map<String, Object> map);
	//void insertUserRoles(Integer userid, Integer[] unassignroleids);
	
	/**
	 * ��ѯȨ��id
	 * @param id
	 * @return
	 */
	List<Integer> queryRoleidsByUserid(Integer id);
	

   }