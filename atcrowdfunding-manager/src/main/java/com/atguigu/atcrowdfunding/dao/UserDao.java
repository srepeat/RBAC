package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.bean.User;

public interface UserDao {

	@Select("select * from t_user")
	List<User> findAll();

	@Select("select * from t_user where loginacct = #{loginacct} and userpswd = #{userpswd}")
	User query4Login(User user);

	List<User> pageQueryDate(Map<String, Object> map);

	int PageQueryCount(Map<String, Object> map);

	void insertUser(User user);

	@Select("select * from t_user where id = #{id}")
	User queryById(Integer id);

	void updateUser(User user);

	void deleteUser(Integer id);

	void deletesUser(@Param("userids")Integer[] userid);

	//void deleteUserRoles(Map<String, Object> map);
	
	void insertUserRoles(Map<String, Object> map);
	
	@Select("select * from t_user_role where userid = #{userid}")
	List<Integer> queryRoleidsByUserid(Integer id);

	void deleteUserRoles(@Param("userid")Integer userid, @Param("roleids")Integer[] assignroleids);
	

}
