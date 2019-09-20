package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.User;
@Repository
public class UserDao extends BaseDao<User>{
	
//  登录
	public User userLogin(User user) {
		String hql = "from User where username=?1 and password=?2";
		
		List<User> lists = this.getListByParams(hql, new Object[]{user.getUsername(),user.getPassword()});
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

//  注册
	public void regUser(User user) {
		save(user);
	}

//  一个    用户    详情 
//通过  ID 获得一个user
	public User getSingleUser(User user) {
		String hql = "from User s where s.id=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{user.getId()});
		
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

//  修改     用户
	public void updateUser(User user) {
		update(user);
	}
//  分页   展示
	public List<User> listUserByPage(User dbUser,int page,int count) {
		
		String hql = "from User";
		
		List<User> list = listByPage(hql,page,count);

		return list;
	}
//  查询       用户 总数
	public int getAllUserCount() {
			
		String hql = "select count(*) from User";
			
		int count = this.getAllCount(hql);
		return count;
	}
//  删除   用户
	public void deleteUser(User user) {
		delete(user);
	}
//  添加用户
	public void addUser(User user) {
		save(user);
	}
//  根据  名字  查询到用户
	public List<User> listUserByName(String name) {
		String hql = "from User s where s.username=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{name});
		
		if(lists.size()>0){
			return lists;
		}
		return null;
	}
//  根据  id 查询到用户
	public User getUserById(int t_id) {
		String hql = "from User s where s.id=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{t_id});
		
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

}
