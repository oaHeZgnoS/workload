package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.User;
@Repository
public class UserDao extends BaseDao<User>{
	
//  ��¼
	public User userLogin(User user) {
		String hql = "from User where username=?1 and password=?2";
		
		List<User> lists = this.getListByParams(hql, new Object[]{user.getUsername(),user.getPassword()});
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

//  ע��
	public void regUser(User user) {
		save(user);
	}

//  һ��    �û�    ���� 
//ͨ��  ID ���һ��user
	public User getSingleUser(User user) {
		String hql = "from User s where s.id=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{user.getId()});
		
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

//  �޸�     �û�
	public void updateUser(User user) {
		update(user);
	}
//  ��ҳ   չʾ
	public List<User> listUserByPage(User dbUser,int page,int count) {
		
		String hql = "from User";
		
		List<User> list = listByPage(hql,page,count);

		return list;
	}
//  ��ѯ       �û� ����
	public int getAllUserCount() {
			
		String hql = "select count(*) from User";
			
		int count = this.getAllCount(hql);
		return count;
	}
//  ɾ��   �û�
	public void deleteUser(User user) {
		delete(user);
	}
//  ����û�
	public void addUser(User user) {
		save(user);
	}
//  ����  ����  ��ѯ���û�
	public List<User> listUserByName(String name) {
		String hql = "from User s where s.username=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{name});
		
		if(lists.size()>0){
			return lists;
		}
		return null;
	}
//  ����  id ��ѯ���û�
	public User getUserById(int t_id) {
		String hql = "from User s where s.id=?1";
		
		List<User> lists = getListByParams(hql,new Object[]{t_id});
		
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

}
