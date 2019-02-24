package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.PermissionDao;
import com.atguigu.atcrowdfunding.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public Permission queryRootPermission() {
		// TODO Auto-generated method stub
		return permissionDao.queryRootPermission();
	}

	public List<Permission> queryChildPermissions(Integer Pid) {
		// TODO Auto-generated method stub
		return permissionDao.queryChildPermissions(Pid);
	}

	public List<Permission> queryAll() {
		// TODO Auto-generated method stub
		return permissionDao.queryAll();
	}

	public void insert(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.insert(permission);
	}

	public void update(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.update(permission);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		permissionDao.delete(id);
	}

	public Permission queryById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.queryById(id);
	}

	public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionidsByRoleid(roleid);
	}

	public List<Permission> queryPermissionsByUser(User dbUser) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionsByUser(dbUser);
	}
}
