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
	

//  新增一条工作量
	public void addWorkload(Cworkload cworkload) {
		cworkloadDao.addWorkload(cworkload);
	}
//  分页    展示工作量
	public List<Cworkload> listCworkloadByPage(int page, int count) {
		
		return cworkloadDao.listWorkloadByPage(page,count);
	}
//  得到   工作量总数目
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllWorkloadCount() {
		
		return cworkloadDao.getAllWorkloadCount();
	}
//  修改  工作量信息
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateWorkload(Cworkload cworkload) {
		cworkloadDao.updateWorkload(cworkload);
	}
//  删除一条工作量
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Cworkload cworkload) {
		cworkloadDao.deleteWl(cworkload);
	}
//  删除一条工作量by id
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
