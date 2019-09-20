package com.workload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workload.dao.FormulaDao;
import com.workload.domain.Formula;

@Service
public class FormulaService {
	
	private FormulaDao formulaDao;


//  分页展示公式
	public List<Formula> listFormulaByPage(int page, int count) {
		return formulaDao.listFormulaByPage(page,count);
	}
	
//  获得公式总数
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllFormulaCount() {
		return formulaDao.getAllFormulaCount();
	}
//  更改校内实习公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml2(Formula fml2){
		formulaDao.updateFml2(fml2);
	}
//  更改校外实习公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml3(Formula fml3){
		formulaDao.update(fml3);
	}
//  更改课程设计公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml4(Formula fml4){
		formulaDao.updateFml4(fml4);
	}
//  更改毕业设计公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml5(Formula fml5){
		formulaDao.updateFml5(fml5);
	}
//  更改实验公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml6(Formula fml6){
		formulaDao.updateFml6(fml6);
	}

//  更改课堂公式
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml1(Formula fml1){
		formulaDao.updateFml1(fml1);
	}
//  根据类型得到公式	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String getFormulaByType(String type) {
		return formulaDao.getFormulaByType(type);
	}

	
	
	
	public FormulaDao getFormulaDao() {
		return formulaDao;
	}
	
	public void setFormulaDao(FormulaDao formulaDao) {
		this.formulaDao = formulaDao;
	}







}
