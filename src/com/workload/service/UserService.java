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
	
//  �û� ��¼
	public User userLogin(User user) {
		
		return userDao.userLogin(user);
	}

//  �û� ע��
	public void regUser(User user) {
		userDao.regUser(user);
	}

//  һ��    �û�    ����  
	public User getSingleUser(User user) {
		return userDao.getSingleUser(user);
	}

//  �޸�  �û���Ϣ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) {
		userDao.updateUser(user);
	
	}
//  ��ҳ    չʾ�û�	
	public List<User> listUserByPage(User dbUser,int page,int count) {
		
		return userDao.listUserByPage(dbUser,page,count);
	}
//  �û�  ������Ϊ�˷����ҳ��
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllUserCount() {

		return userDao.getAllUserCount();
	}
//  ɾ��   �û�
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(User user) {
	
		userDao.deleteUser(user);
	
	}
	
//  ����  ����  ��ѯ���û�
	public List<User> listUserByName(String name) {
		return userDao.listUserByName(name);
	}
//  �����û�
	public void addUser(User user) {
		userDao.addUser(user);
	}
//  ����  id  ��ѯ���û�
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
