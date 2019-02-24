package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleDao.queryAll();
	}

	public List<Role> pageQueryData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.pageQueryData(map);
	}

	public int pageQueryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.pageQueryCount(map);
	}

	public void insertRolePermission(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		/**
		 * 先删除之前写入的然后添加
		 */
		roleDao.deleteRolePermission(paramMap);
		roleDao.insertRolePermission(paramMap);
	}

	public Role queryById(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.queryById(id);
	}

	public void queryAndUpdate(Role role) {
		// TODO Auto-generated method stub
		roleDao.queryAndUpdate(role);
	}

	public void queryAndDelete(Integer id) {
		// TODO Auto-generated method stub
		roleDao.queryAndDelete(id);
	}

}
