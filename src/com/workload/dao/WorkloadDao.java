package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.domain.Workload;
@Repository
public class WorkloadDao extends BaseDao<Workload>{

//  ����  һ��������
	public void addWorkload(Workload workload) {
		save(workload);
	}

//  ��ҳ   չʾ
	public List<Workload> listWorkloadByPage(int page,int count) {
		
		String hql = "from Workload";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  ��ѯ       ������ ����
	public int getAllWorkloadCount() {
		String hql = "select count(*) from Workload";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  �޸�  ������
	public void updateWorkload(Workload workload) {
		update(workload);
	}
//  ɾ��  ������
	public void deleteWl(Workload workload) {
		delete(workload);
	}
//  ����  ��ʦ����t_name��ѯ������������ҳչʾ
	public List<Workload> listWorkloadByPageAndT_name(int page, int count,String t_name) {
		String hql = "from Workload where t_name='"+t_name+"'";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  ���ݽ�ʦ����t_id�����乤����������ҳչʾ
	public List<Workload> listWorkloadByPageAndT_id(int page, int count, int t_id) {
		String hql = "from Workload where t_id="+t_id;
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  ����t_id��ѯ       �ҵĹ����� ����
	public int getMyWlCount(int t_id) {
		String hql = "select count(*) from Workload where t_id="+t_id;
		
		int count = this.getAllCount(hql);
		return count;
	}
//  ����t_name��ѯ       �ҵĹ����� ����
	public int getMyWlCountByT_name(String t_name) {
		String hql = "select count(*) from Workload where t_name='"+t_name+"'";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  ����t_id��term��ѯ       �ҵĹ�����������ҳչʾ
	public List<Workload> listWorkloadByPageAndTermAndT_id(int page, int count, String term, int t_id) {
		String hql = "from Workload where t_id="+t_id+" and term='"+term+"'";
		
		List<Workload> list = listByPage(hql,page,count);

		return list;
	}
//  ����t_id��term��ѯ       �ҵĹ�����  ����Ŀ
	public int getMyWlCountByTerm(String term, int t_id) {
		String hql = "select count(*) from Workload where t_id="+t_id+" and term='"+term+"'";
		
		int count = this.getAllCount(hql);
		return count;
	}
//  ����id���ҵ�����������
	public Workload getWlById(int id) {
		String hql = "from Workload where id="+id;
		
		List<Workload> list = getListByParams(hql,null);
		
		return list.get(0);
	}
//  �õ�ͳ����Ҫ������
	public List<Workload> getAllWorkload() {
		String hql = "from Workload";
		List<Workload> list = getListByParams(hql, null);

		return list;
	}


}
