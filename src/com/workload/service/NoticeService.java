package com.workload.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.workload.dao.NoticeDao;
import com.workload.dao.UserDao;
import com.workload.domain.Notice;
import com.workload.domain.User;
@Service
public class NoticeService {
	
	private NoticeDao noticeDao;
	

//  新增一条公告
	public void addNotice(Notice notice) {
		noticeDao.addNotice(notice);
	}
//  分页    展示公告
	public List<Notice> listNoticeByPage(int page, int count) {
		
		return noticeDao.listNoticeByPage(page,count);
	}
//  得到   公告总数目
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int getAllNoticeCount() {
		
		return noticeDao.getAllNoticeCount();
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}


	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}


	
	
	
	
	
	
	
	
	
	
}
