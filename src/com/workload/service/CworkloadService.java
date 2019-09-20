package com.workload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workload.dao.CworkloadDao;
import com.workload.domain.Cworkload;
@Service
public class CworkloadService {
	
	private CworkloadDao cworkloadDao;
	

//  ����һ��������
	public void addWorkload(Cworkload cworkload) {
		cworkloadDao.addWorkload(cworkload);
	}
//  ��ҳ    չʾ������
	public List<Cworkload> listCworkloadByPage(int page, int count) {
		
		return cworkloadDao.listWorkloadByPage(page,count);
	}
//  �õ�   ����������Ŀ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCount() {
		
		return cworkloadDao.getAllWorkloadCount();
	}
//  �޸�  ��������Ϣ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateWorkload(Cworkload cworkload) {
		cworkloadDao.updateWorkload(cworkload);
	}
//  ɾ��һ��������
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Cworkload cworkload) {
		cworkloadDao.deleteWl(cworkload);
	}
//  ɾ��һ��������by id
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteById(int id) {
		cworkloadDao.deleteById(id);
	}
	
	
	
	
	public CworkloadDao getWorkloadDao() {
		return cworkloadDao;
	}
	public void setCworkloadDao(CworkloadDao cworkloadDao) {
		this.cworkloadDao = cworkloadDao;
	}




	
	
	
	
	
	
	
	
	
	
}
