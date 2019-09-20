package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Formula;

@Repository
public class FormulaDao extends BaseDao<Formula>{

//  分页展示公式
	public List<Formula> listFormulaByPage(int page, int count) {
		String hql = "from Formula";
		List<Formula> list = listByPage(hql,page,count);

		return list;
	}

//  获取公式总数
	public int getAllFormulaCount() {
		String hql = "select count(*) from Formula";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  更改校内实习公式
	public void updateFml2(Formula fml2){
		update(fml2);
	}
//  更改校外实习公式
	public void updateFml3(Formula fml3){
		update(fml3);
	}
//  更改课程设计公式
	public void updateFml4(Formula fml4){
		update(fml4);
	}
//  更改毕业设计公式
	public void updateFml5(Formula fml5){
		update(fml5);
	}
//  更改实验公式
	public void updateFml6(Formula fml6){
		update(fml6);
	}
	
//  更改课堂公式
	public void updateFml1(Formula fml1){
		update(fml1);
	}
//  根据类型得到公式	
	public String getFormulaByType(String type) {
		String hql = "from Formula f where f.type=?1";
		
		String formula = getListByParams(hql, new Object[]{type}).get(0).getFml();
		
		return formula;
	}	
	
	
	
}
