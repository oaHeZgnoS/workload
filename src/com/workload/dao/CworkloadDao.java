package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Cworkload;
@Repository
public class CworkloadDao extends BaseDao<Cworkload>{

//  新增  一条工作量
	public void addWorkload(Cworkload cworkload) {
		save(cworkload);
	}

//  分页   展示
	public List<Cworkload> listWorkloadByPage(int page,int count) {
		
		String hql = "from Cworkload";
		
		List<Cworkload> list = listByPage(hql,page,count);

		return list;
	}
//  查询       工作量 总数
	public int getAllWorkloadCount() {
		String hql = "select count(*) from Cworkload";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  修改  工作量
	public void updateWorkload(Cworkload cworkload) {
		update(cworkload);
	}
//  删除  工作量
	public void deleteWl(Cworkload cworkload) {
		delete(cworkload);
	}
//  删除  工作量by id
	public void deleteById(int id) {
		String hql = "from Cworkload where id="+id;
		Cworkload cworkload = getListByParams(hql, null).get(0);
		delete(cworkload);
	}


}
