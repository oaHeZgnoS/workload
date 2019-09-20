package com.workload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workload.dao.WorkloadDao;
import com.workload.domain.Workload;
@Service
public class WorkloadService {
	
	private WorkloadDao workloadDao;
	

//  新增一条工作量
	public void addWorkload(Workload workload) {
		workloadDao.addWorkload(workload);
	}
//  分页    展示工作量
	public List<Workload> listWorkloadByPage(int page, int count) {
		
		return workloadDao.listWorkloadByPage(page,count);
	}
//  得到   工作量总数目
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCount() {
		
		return workloadDao.getAllWorkloadCount();
	}
//  修改  工作量信息
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateWorkload(Workload workload) {
		workloadDao.updateWorkload(workload);
	}
//  删除一条工作量
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Workload workload) {
		workloadDao.deleteWl(workload);
	}
//  根据  教师姓名t_name查询工作量
	public List<Workload> listWorkloadByPageAndT_name(int page, int count,String t_name) {
		return workloadDao.listWorkloadByPageAndT_name(page,count,t_name);
	}
//  根据教师id查找其工作量，并分页展示
	public List<Workload> listWorkloadByPageAndT_id(int page, int count, int t_id) {
		return workloadDao.listWorkloadByPageAndT_id(page,count,t_id);
	}
//  根据t_id得到   我的工作量总数目
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getMyWlCount(int t_id) {
		return workloadDao.getMyWlCount(t_id);
	}
//  根据t_name得到   我的工作量总数目
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCountByT_name(String t_name) {
		return workloadDao.getMyWlCountByT_name(t_name);
	}
//  根据t_id和term得到   我的工作量	
	public List<Workload> listWorkloadByPageAndTermAndT_id(int page, int count, String term, int t_id) {
		return workloadDao.listWorkloadByPageAndTermAndT_id(page,count,term,t_id);
	}
//  根据t_id和term得到   我的工作量  总数目	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCountByTermAndT_id(String term, int t_id) {
		return workloadDao.getMyWlCountByTerm(term,t_id);
	}
//  根据id查找到那条工作量
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Workload getWlById(int id) {
		return workloadDao.getWlById(id);
	}
//  拿到统计需要的数据
	public List<Workload> getAllWorkload() {
		return workloadDao.getAllWorkload();
	}
	
	
	
	
	
	public WorkloadDao getWorkloadDao() {
		return workloadDao;
	}
	public void setWorkloadDao(WorkloadDao workloadDao) {
		this.workloadDao = workloadDao;
	}




	
	
	
	
	
	
	
	
	
	
}
