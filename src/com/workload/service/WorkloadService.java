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
	

//  ����һ��������
	public void addWorkload(Workload workload) {
		workloadDao.addWorkload(workload);
	}
//  ��ҳ    չʾ������
	public List<Workload> listWorkloadByPage(int page, int count) {
		
		return workloadDao.listWorkloadByPage(page,count);
	}
//  �õ�   ����������Ŀ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCount() {
		
		return workloadDao.getAllWorkloadCount();
	}
//  �޸�  ��������Ϣ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateWorkload(Workload workload) {
		workloadDao.updateWorkload(workload);
	}
//  ɾ��һ��������
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Workload workload) {
		workloadDao.deleteWl(workload);
	}
//  ����  ��ʦ����t_name��ѯ������
	public List<Workload> listWorkloadByPageAndT_name(int page, int count,String t_name) {
		return workloadDao.listWorkloadByPageAndT_name(page,count,t_name);
	}
//  ���ݽ�ʦid�����乤����������ҳչʾ
	public List<Workload> listWorkloadByPageAndT_id(int page, int count, int t_id) {
		return workloadDao.listWorkloadByPageAndT_id(page,count,t_id);
	}
//  ����t_id�õ�   �ҵĹ���������Ŀ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getMyWlCount(int t_id) {
		return workloadDao.getMyWlCount(t_id);
	}
//  ����t_name�õ�   �ҵĹ���������Ŀ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCountByT_name(String t_name) {
		return workloadDao.getMyWlCountByT_name(t_name);
	}
//  ����t_id��term�õ�   �ҵĹ�����	
	public List<Workload> listWorkloadByPageAndTermAndT_id(int page, int count, String term, int t_id) {
		return workloadDao.listWorkloadByPageAndTermAndT_id(page,count,term,t_id);
	}
//  ����t_id��term�õ�   �ҵĹ�����  ����Ŀ	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCountByTermAndT_id(String term, int t_id) {
		return workloadDao.getMyWlCountByTerm(term,t_id);
	}
//  ����id���ҵ�����������
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Workload getWlById(int id) {
		return workloadDao.getWlById(id);
	}
//  �õ�ͳ����Ҫ������
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
