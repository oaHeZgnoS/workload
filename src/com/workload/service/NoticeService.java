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
	

//  ����һ������
	public void addNotice(Notice notice) {
		noticeDao.addNotice(notice);
	}
//  ��ҳ    չʾ����
	public List<Notice> listNoticeByPage(int page, int count) {
		
		return noticeDao.listNoticeByPage(page,count);
	}
//  �õ�   ��������Ŀ
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
