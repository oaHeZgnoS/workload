package com.workload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workload.dao.UserDao;
import com.workload.domain.User;
import com.workload.domain.Workload;
@Service
public class UserService {
	
	private UserDao userDao;
	
//  用户 登录
	public User userLogin(User user) {
		
		return userDao.userLogin(user);
	}

//  用户 注册
	public void regUser(User user) {
		userDao.regUser(user);
	}

//  一个    用户    详情  
	public User getSingleUser(User user) {
		return userDao.getSingleUser(user);
	}

//  修改  用户信息
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) {
		userDao.updateUser(user);
	
	}
//  分页    展示用户	
	public List<User> listUserByPage(User dbUser,int page,int count) {
		
		return userDao.listUserByPage(dbUser,page,count);
	}
//  用户  总数（为了方便分页）
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllUserCount() {

		return userDao.getAllUserCount();
	}
//  删除   用户
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(User user) {
	
		userDao.deleteUser(user);
	
	}
	
//  根据  名字  查询到用户
	public List<User> listUserByName(String name) {
		return userDao.listUserByName(name);
	}
//  新增用户
	public void addUser(User user) {
		userDao.addUser(user);
	}
//  根据  id  查询到用户
	public User getUserById(int t_id) {
		return userDao.getUserById(t_id);
	}

	
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}




}
