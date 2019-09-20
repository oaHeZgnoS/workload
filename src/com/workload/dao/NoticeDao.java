package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.dao.BaseDao;
import com.workload.domain.Notice;
import com.workload.domain.User;
@Repository
public class NoticeDao extends BaseDao<Notice>{

//  ����  һ������
	public void addNotice(Notice notice) {
		save(notice);
	}

//  ��ҳ   չʾ
	public List<Notice> listNoticeByPage(int page,int count) {
		
		String hql = "from Notice";
		
		List<Notice> list = listByPage(hql,page,count);

		return list;
	}
//  ��ѯ       ���� ����
	public int getAllNoticeCount() {
		String hql = "select count(*) from Notice";
		
		int count = this.getAllCount(hql);
		return count;
	}

}
