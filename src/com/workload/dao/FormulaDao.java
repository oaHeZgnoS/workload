package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Formula;

@Repository
public class FormulaDao extends BaseDao<Formula>{

//  ��ҳչʾ��ʽ
	public List<Formula> listFormulaByPage(int page, int count) {
		String hql = "from Formula";
		List<Formula> list = listByPage(hql,page,count);

		return list;
	}

//  ��ȡ��ʽ����
	public int getAllFormulaCount() {
		String hql = "select count(*) from Formula";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  ����У��ʵϰ��ʽ
	public void updateFml2(Formula fml2){
		update(fml2);
	}
//  ����У��ʵϰ��ʽ
	public void updateFml3(Formula fml3){
		update(fml3);
	}
//  ���Ŀγ���ƹ�ʽ
	public void updateFml4(Formula fml4){
		update(fml4);
	}
//  ���ı�ҵ��ƹ�ʽ
	public void updateFml5(Formula fml5){
		update(fml5);
	}
//  ����ʵ�鹫ʽ
	public void updateFml6(Formula fml6){
		update(fml6);
	}
	
//  ���Ŀ��ù�ʽ
	public void updateFml1(Formula fml1){
		update(fml1);
	}
//  �������͵õ���ʽ	
	public String getFormulaByType(String type) {
		String hql = "from Formula f where f.type=?1";
		
		String formula = getListByParams(hql, new Object[]{type}).get(0).getFml();
		
		return formula;
	}	
	
	
	
}
