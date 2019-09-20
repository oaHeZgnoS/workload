package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Workload;
@Repository
public class WorkloadDao extends BaseDao<Workload>{

//  新增  一条工作量
	public void addWorkload(Workload workload) {
		save(workload);
	}

//  分页   展示
	public List<Workload> listWorkloadByPage(int page,int count) {
		
		String hql = "from Workload";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  查询       工作量 总数
	public int getAllWorkloadCount() {
		String hql = "select count(*) from Workload";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  修改  工作量
	public void updateWorkload(Workload workload) {
		update(workload);
	}
//  删除  工作量
	public void deleteWl(Workload workload) {
		delete(workload);
	}
//  根据  教师姓名t_name查询工作量，并分页展示
	public List<Workload> listWorkloadByPageAndT_name(int page, int count,String t_name) {
		String hql = "from Workload where t_name='"+t_name+"'";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  根据教师工号t_id查找其工作量，并分页展示
	public List<Workload> listWorkloadByPageAndT_id(int page, int count, int t_id) {
		String hql = "from Workload where t_id="+t_id;
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  根据t_id查询       我的工作量 总数
	public int getMyWlCount(int t_id) {
		String hql = "select count(*) from Workload where t_id="+t_id;
		
		int count = this.getAllCount(hql);
		return count;
	}
//  根据t_name查询       我的工作量 总数
	public int getMyWlCountByT_name(String t_name) {
		String hql = "select count(*) from Workload where t_name='"+t_name+"'";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  根据t_id和term查询       我的工作量，并分页展示
	public List<Workload> listWorkloadByPageAndTermAndT_id(int page, int count, String term, int t_id) {
		String hql = "from Workload where t_id="+t_id+" and term='"+term+"'";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  根据t_id和term查询       我的工作量  总数目
	public int getMyWlCountByTerm(String term, int t_id) {
		String hql = "select count(*) from Workload where t_id="+t_id+" and term='"+term+"'";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  根据id查找到那条工作量
	public Workload getWlById(int id) {
		String hql = "from Workload where id="+id;
		
		List<Workload> list = getListByParams(hql,null);
		
		return list.get(0);
	}
//  拿到统计需要的数据
	public List<Workload> getAllWorkload() {
		String hql = "from Workload";
		List<Workload> list = getListByParams(hql, null);

		return list;
	}


}
