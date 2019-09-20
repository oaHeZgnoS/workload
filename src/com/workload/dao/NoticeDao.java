package com.workload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.workload.dao.BaseDao;
import com.workload.domain.Notice;
import com.workload.domain.User;
@Repository
public class NoticeDao extends BaseDao<Notice>{

//  新增  一条公告
	public void addNotice(Notice notice) {
		save(notice);
	}

//  分页   展示
	public List<Notice> listNoticeByPage(int page,int count) {
		
		String hql = "from Notice";
		
		List<Notice> list = listByPage(hql,page,count);

		return list;
	}
//  查询       公告 总数
	public int getAllNoticeCount() {
		String hql = "select count(*) from Notice";
		
		int count = this.getAllCount(hql);
		return count;
	}

}
