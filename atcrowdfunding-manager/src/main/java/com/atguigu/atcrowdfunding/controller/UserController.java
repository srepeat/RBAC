package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.AJAXResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;
/**
 * �û�Ȩ��ҳ��
 * @author ����
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/roleIndex")
	public String roleIndex() {
		
		return "role/index";
	}
	
	@RequestMapping("/assign")
	public String assign(Integer id,Model model) {
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		List<Role> roles = roleService.queryAll();
		model.addAttribute("roles", roles);
		
		List<Role> assingedRoles = new ArrayList<Role>();
		List<Role> unassignRoles = new ArrayList<Role>();
		
		// ��ȡ��ϵ�������
		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		for ( Role role : roles ) {
			if ( roleids.contains(role.getId()) ) {
				assingedRoles.add(role);
			} else {
				unassignRoles.add(role);
			}
		}
		
		model.addAttribute("assingedRoles", assingedRoles);
		model.addAttribute("unassignRoles", unassignRoles);
		return "user/assign";
		
	}
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer userid, Integer[] unassignroleids ) {
		AJAXResult result = new AJAXResult();
		
		try {
			// ���ӹ�ϵ������
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", unassignroleids);
			userService.insertUserRoles(map);
			//userService.insertUserRoles(userid,unassignroleids);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/dounAssign")
	public Object dounAssign( Integer userid, Integer[] assignroleids ) {
		AJAXResult result = new AJAXResult();
		
		try {
			// ɾ����ϵ������
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", assignroleids);
			userService.deleteUserRoles(map);*/
			userService.deleteUserRoles(userid,assignroleids);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping("/deletes")
	public @ResponseBody Object deletes(Integer [] userid) {
		AJAXResult result = new AJAXResult();
		
		try {
			userService.deletesUser(userid);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		
		return result;
		
	}
	
	/**
	 * ɾ��
	 */
	@RequestMapping("/delete")
	public @ResponseBody Object delete(Integer id) {
		AJAXResult result = new AJAXResult();
		
		try {
			userService.deleteUser(id);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		return result; 
		
	}
	
	/**
	 * �޸���Ϣ
	 */
	@RequestMapping("/update")
	public @ResponseBody Object update(User user) {
		AJAXResult result = new AJAXResult();
		
		try {
			userService.updateUser(user);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	/**
	 * ��ת�޸�ҳ��
	 */
	@RequestMapping("/goUpdate")
	public String goUpdate(Integer id,Model model) {
		
		User user =  userService.queryById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	
	/**
	 * �����Ϣ
	 */
	@RequestMapping("/insert")
	public @ResponseBody Object insert(User user) {
		AJAXResult result = new AJAXResult();
		try {
			userService.insertUser(user);
			result.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * ��ת����ҳ��
	 */
	
	@RequestMapping("add")
	public String add() {
		
		return "user/add";
	}
	
	
	/**
	 * Ajax�첽��ѯ����ģ����ѯ
	 * @param queryText
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {
		
		AJAXResult result = new AJAXResult();
		
		try {
			
			// ��ҳ��ѯ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			
			List<User> users = userService.pageQueryDate( map );
			// ��ǰҳ��			
			// �ܵ���������
			int totalsize = userService.PageQueryCount( map );
			// ���ҳ�루��ҳ�룩
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			
			// ��ҳ����
			Page<User> userPage = new Page<User>();
			userPage.setDatas(users);
			userPage.setTotalno(totalno);
			userPage.setTotalsize(totalsize);
			userPage.setPageno(pageno);
			
			result.setData(userPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	
	@RequestMapping("/index")
	public String index() {
		
		return "user/index";
	}
	
	
	/**
	 * �û���ҳ
	 * @param pageno
	 * @param pagesize
	 * @param model
	 * @return
	 */
	//��ת�û���ɫҳ��
	@RequestMapping("/index1")
	//@RequestParamָ��������required=false����ʾ���Ǳ���ģ�defaultValue��Ĭ�ϲ���
	public String index1(@RequestParam(required=false,defaultValue="1")Integer pageno,@RequestParam(required=false,defaultValue="3")Integer pagesize,Model model) {
		//����һ��map���Ͻ���ҳ���Դ���map
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", (pageno-1)*pagesize);
		map.put("size", pagesize);
		
		List<User> users = userService.pageQueryDate(map);
		model.addAttribute("users", users);
		//��ǰҳ��
		model.addAttribute("pageno",pageno);
		
		//��ǰ����������
		int totalsize = userService.PageQueryCount(map);
		
		//���ҳ�루��ҳ�룩
		int totalno = 0;
		//�ж��ܷ�����
		if(totalsize %pagesize==0) {
			totalno = totalsize / pagesize;
		}else {
			totalno = totalsize / pagesize +1;
		}
		
		model.addAttribute("totalno", totalno);
		
		return "user/index";
	}
	
}
