package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Cworkload;
@Repository
public class CworkloadDao extends BaseDao<Cworkload>{

//  ����  һ��������
	public void addWorkload(Cworkload cworkload) {
		save(cworkload);
	}

//  ��ҳ   չʾ
	public List<Cworkload> listWorkloadByPage(int page,int count) {
		
		String hql = "from Cworkload";
		
		List<Cworkload> list = listByPage(hql,page,count);

		return list;
	}
//  ��ѯ       ������ ����
	public int getAllWorkloadCount() {
		String hql = "select count(*) from Cworkload";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  �޸�  ������
	public void updateWorkload(Cworkload cworkload) {
		update(cworkload);
	}
//  ɾ��  ������
	public void deleteWl(Cworkload cworkload) {
		delete(cworkload);
	}
//  ɾ��  ������by id
	public void deleteById(int id) {
		String hql = "from Cworkload where id="+id;
		Cworkload cworkload = getListByParams(hql, null).get(0);
		delete(cworkload);
	}


}
