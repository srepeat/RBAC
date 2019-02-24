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
 * 用户权限页面
 * @author 鲜磊
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
		
		// 获取关系表的数据
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
			// 增加关系表数据
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
			// 删除关系表数据
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
	 * 删除
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
	 * 修改信息
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
	 * 跳转修改页面
	 */
	@RequestMapping("/goUpdate")
	public String goUpdate(Integer id,Model model) {
		
		User user =  userService.queryById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	
	/**
	 * 添加信息
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
	 * 跳转新增页面
	 */
	
	@RequestMapping("add")
	public String add() {
		
		return "user/add";
	}
	
	
	/**
	 * Ajax异步查询并且模糊查询
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
			
			// 分页查询
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			
			List<User> users = userService.pageQueryDate( map );
			// 当前页码			
			// 总的数据条数
			int totalsize = userService.PageQueryCount( map );
			// 最大页码（总页码）
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			
			// 分页对象
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
	 * 用户首页
	 * @param pageno
	 * @param pagesize
	 * @param model
	 * @return
	 */
	//跳转用户角色页面
	@RequestMapping("/index1")
	//@RequestParam指定参数，required=false：表示不是必须的，defaultValue：默认参数
	public String index1(@RequestParam(required=false,defaultValue="1")Integer pageno,@RequestParam(required=false,defaultValue="3")Integer pagesize,Model model) {
		//创建一个map集合将分页属性存入map
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", (pageno-1)*pagesize);
		map.put("size", pagesize);
		
		List<User> users = userService.pageQueryDate(map);
		model.addAttribute("users", users);
		//当前页数
		model.addAttribute("pageno",pageno);
		
		//当前数据总条数
		int totalsize = userService.PageQueryCount(map);
		
		//最大页码（总页码）
		int totalno = 0;
		//判断能否整除
		if(totalsize %pagesize==0) {
			totalno = totalsize / pagesize;
		}else {
			totalno = totalsize / pagesize +1;
		}
		
		model.addAttribute("totalno", totalno);
		
		return "user/index";
	}
	
}
