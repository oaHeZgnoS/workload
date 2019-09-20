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


//  ��ҳչʾ��ʽ
	public List<Formula> listFormulaByPage(int page, int count) {
		return formulaDao.listFormulaByPage(page,count);
	}
	
//  ��ù�ʽ����
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllFormulaCount() {
		return formulaDao.getAllFormulaCount();
	}
//  ����У��ʵϰ��ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml2(Formula fml2){
		formulaDao.updateFml2(fml2);
	}
//  ����У��ʵϰ��ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml3(Formula fml3){
		formulaDao.update(fml3);
	}
//  ���Ŀγ���ƹ�ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml4(Formula fml4){
		formulaDao.updateFml4(fml4);
	}
//  ���ı�ҵ��ƹ�ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml5(Formula fml5){
		formulaDao.updateFml5(fml5);
	}
//  ����ʵ�鹫ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml6(Formula fml6){
		formulaDao.updateFml6(fml6);
	}

//  ���Ŀ��ù�ʽ
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateFml1(Formula fml1){
		formulaDao.updateFml1(fml1);
	}
//  �������͵õ���ʽ	
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
