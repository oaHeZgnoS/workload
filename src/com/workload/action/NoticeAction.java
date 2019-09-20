package com.workload.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.workload.domain.Notice;
import com.workload.domain.User;
import com.workload.service.NoticeService;

@Controller
@Scope(value="prototype")
public class NoticeAction implements ServletRequestAware{
	
	private User user;
	private Notice notice;
	
	private HttpServletRequest req;
	

	private HttpSession session;
	
	private NoticeService noticeService;
	
	
//  新增公告
	public String addNotice(){
		User user = (User) session.getAttribute("dbUser");
		
		notice = new Notice();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(now);
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		notice.setTitle(title);
		notice.setContent(content);
		notice.setTime(time);
		notice.setWorker_id(user.getId());
		
		System.out.println(notice.getId()+","+notice.getWorker_id()+","+notice.getTitle()
				+","+notice.getContent()+","+notice.getTime());
		
		noticeService.addNotice(notice);
		return "success";
	}
//  列出所有公告  //分页
	public String noticeList(){
		
		String p = req.getParameter("page");
		
		if(p==null){   //点击左上角   用户管理 来的
			p = "1";
		}
		int page = Integer.parseInt(p);

		User dbUser = (User) session.getAttribute("dbUser");
			
			List<Notice> noticeList =  noticeService.listNoticeByPage(page,5);
			req.setAttribute("noticeList", noticeList);
		
		
		int allCount = noticeService.getAllNoticeCount();
		
		int pageCount = allCount%5==0? allCount/5
				:allCount/5+1;
		
		req.setAttribute("pageCount", pageCount);
		
		req.setAttribute("currentPage", page);
		
		
		return "success";
	}
	
	
	
	
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession(true);
		
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public NoticeService getNoticeService() {
		return noticeService;
	}
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
