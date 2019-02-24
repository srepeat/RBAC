package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AJAXResult;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 删除节点信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Object delete(Integer id) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.delete(id);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 修改
	 * @param permission
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Object update(Permission permission) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.update(permission);
			
			
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,Model model) {
		
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		
		return "permission/edit";
	}
	
	//新增
	@RequestMapping("/insert")
	public @ResponseBody Object insert(Permission permission) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.insert(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		
		return "permission/add";
	}
	
	
	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		
		return "permission/index";
	}

	@ResponseBody
	@RequestMapping("/loadAssignData")
	public Object loadAssignData( Integer roleid ) {
		List<Permission> permissions = new ArrayList<Permission>();
		//查询全部
		List<Permission> ps = permissionService.queryAll();
		//获取当前角色已经分配好的信息
		List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);
		
		//使用map索引方式
		Map<Integer, Permission> mapPermissions = new HashMap<Integer, Permission>();
		
		for (Permission p : ps) {
			if (permissionids.contains( p.getId() ) ) {
				p.setChecked(true);
			}else {
				p.setChecked(false);
			}
			
			mapPermissions.put(p.getId(), p);
		}
		
		
		for (Permission p : ps) {
			Permission child = p;
			if(child.getPid() == 0) {
				permissions.add(p);
			}else {
				Permission parent = mapPermissions.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		return permissions;
	}
	
	@RequestMapping("/loadData")
	public @ResponseBody Object loadData() {
		List<Permission> permissions = new ArrayList<Permission>();
		
		/*Permission root = new Permission();
		root.setName("顶级节点");
		
		Permission child = new Permission();
		child.setName("子节点");
		
		root.getChildren().add(child);
		permissions.add(root);*/
		
		/*Permission root = permissionService.queryRootPermission();
		
		List<Permission> childPermissions = permissionService.queryChildPermissions(root.getId());
		//遍历集合中的子节点
		for (Permission childPermission : childPermissions) {
			List<Permission> childChildPermissions = permissionService.queryChildPermissions(childPermission.getId());
			childPermission.setChildren(childChildPermissions);
		}
		
		root.setChildren(childPermissions);
		permissions.add(root);*/
		
		//递归
		/*Permission parate = new Permission();
		parate.setId(0);
		queryPermissions(parate);
		
		return parate.getChildren();*/
		
		//查询全部
		List<Permission> ps = permissionService.queryAll();
		/**
		 * 遍历集合
		 * 1、使用for循环遍历，效率高
		 * 2、同样可以查询
		 */
		/*for (Permission p : ps) {
			
			//判断子节点
			Permission child = p;
			if(p.getPid() == 0) {
				permissions.add(p);
			}else {
				for (Permission innerPermission : ps) {
					if(child.getPid().equals(innerPermission.getId())) {
						//父节点
						Permission parent = innerPermission;
						//组合父子节点关系
						parent.getChildren().add(child);
						break;
					}
				}
				
			}
		}*/
		
		//使用map索引方式
		Map<Integer, Permission> mapPermissions = new HashMap<Integer, Permission>();
		
		for (Permission p : ps) {
			mapPermissions.put(p.getId(), p);
		}
		
		
		for (Permission p : ps) {
			Permission child = p;
			if(child.getPid() == 0) {
				permissions.add(p);
			}else {
				Permission parent = mapPermissions.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		return permissions;
	}
	
	
	/**
	 * 递归遍历菜单节点
	 * 1、代码本身调用本身
	 * 2、有规律的实现
	 * 3、参数有规律
	 * 4、效率不高
	 * all 抑制所有警告⚠
	 * @param parate
	 */
	@SuppressWarnings("all")
	private void queryPermissions(Permission parate) {
		List<Permission> childPermissions = permissionService.queryChildPermissions(parate.getId());
		
		for (Permission permission : childPermissions) {
			queryPermissions(permission);
		}
		
		parate.setChildren(childPermissions);
	}
	
	
	
}
