package com.workload.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao<T extends Serializable> {
	
	private SessionFactory sessionFactory;
	
//通用的  添加 方法	
	public void save(T t){
		Session session = sessionFactory.openSession();
		session.save(t);
	}
//通过参数  获得  集合	
	public List<T> getListByParams(String hql,Object[] params){
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		
		if(params!=null && params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter((i+1)+"", params[i]);
			}
		}
		return query.list();
		
	}
	
//通用的  修改  方法
	public void update(T t){
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
			
	}
//通用的  分页 方法	
	public List<T> listByPage(String hql,int page,int count){
		Session session = sessionFactory.openSession();
			
		Query query = session.createQuery(hql);
		query.setFirstResult((page-1)*count);
		query.setMaxResults(count);
			
		return query.list();
			
	}
//通用的  查询总数  方法	
	public int getAllCount(String hql){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
			
		long count = (long) query.uniqueResult();
			
		return (int)count;
	}
//通用的  删除 方法	
	public void delete(T t){
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	
	
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
