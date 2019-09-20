package com.workload.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.workload.db.DBHelper;
import com.workload.domain.Cworkload;
import com.workload.domain.Formula;
import com.workload.domain.Notice;
import com.workload.domain.User;
import com.workload.domain.Workload;
import com.workload.service.CworkloadService;
import com.workload.service.FormulaService;
import com.workload.service.NoticeService;
import com.workload.service.UserService;
import com.workload.service.Wl_teacherService;
import com.workload.service.WorkloadService;

@Controller
@Scope(value="prototype")
public class UserAction implements ServletRequestAware {
	
	private File excel;
	
	//�ļ�����
	private String excelFileName;
	
	//�ļ�����
	private String excelContentType;
	//ע�⣺�ļ����ƺ��ļ����͵�����ǰ׺������ͬ��
	
	private User user;
	private Cworkload cworkload;
	
	
	private HttpServletRequest req;

	private HttpSession session;
	
	private UserService userService;
	private NoticeService noticeService;
	private WorkloadService workloadService;
	private CworkloadService cworkloadService;
	private FormulaService formulaService;
	
	
	//  �û�   ��¼
	public String login(){
		System.out.println(user.getUsername()+","+user.getPassword());
		
		/*if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
			System.out.println("ϵͳ����Ա��¼�ɹ���");
			User loginUser = new User();
			loginUser.setUsername("admin");
			session.setAttribute("dbUser", loginUser);
			return "success";
		}*/
		
		User loginUser = userService.userLogin(user);
		
		if(loginUser!=null){
			session.setAttribute("dbUser", loginUser);
			
			String p = req.getParameter("page");
			
			if(p==null){   //������Ͻ�   �û����� ����
				p = "1";
			}
			int page = Integer.parseInt(p);
			User dbUser = (User) session.getAttribute("dbUser");
			if(dbUser.getAuthority()==1){
				
				List<Notice> noticeList =  noticeService.listNoticeByPage(page,5);
				req.setAttribute("noticeList", noticeList);
			}
			
			int allCount = noticeService.getAllNoticeCount();
			int pageCount = allCount%5==0? allCount/5 :allCount/5+1;
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("currentPage", page);
			
			return "success";
		}else{
			session.setAttribute("flag", 100);
			return "failure";
		}
	}
//  �˳�  ϵͳ
	public String exit(){
		session.removeAttribute("dbUser");
		return "success";
	}
//  ע�� ���û�
	public String reg() {
		System.out.println("ע�����û��������ǣ�"+user.getUsername());
		userService.regUser(user);
		return "success";
	}
//  �� �û�ģ��ҳ��	
	public String userFunc(){
		User user = (User) session.getAttribute("dbUser");
		return "success";
	}
//  �� ����Աģ��ҳ��	
	public String adminFunc(){
		User user = (User) session.getAttribute("dbUser");
		if(user.getAuthority()==1){
			return "success";
		}else{
			return "failure";
		}
	}
//  �� �޸ĸ�����Ϣҳ��(�鿴������Ϣ)	
	public String toUserModify(){
		User dbUser = (User) session.getAttribute("dbUser");
		User user = userService.getSingleUser(dbUser);
		req.setAttribute("user", user);
		
		
		return "success";
	}
//  �޸� ������Ϣ	
	public String update(){
		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		userService.updateUser(user);
		return "success";
	}
	
//  ��  �޸�  ����
	public String pswDetails(){	
		return "success";
	}
//   �޸�  ����
	public String updatePsw() throws Exception{
		String username=new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		user.setUsername(username);
		
		String sex=new String(user.getSex().getBytes("ISO-8859-1"),"UTF-8");
		user.setSex(sex);
		
		String addr=new String(user.getAddr().getBytes("ISO-8859-1"),"UTF-8");
		user.setAddr(addr);
		
		String psw=new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
		user.setPassword(psw);
		
		String department=new String(user.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		user.setDepartment(department);
		
   	
		userService.updateUser(user);
		return "success";
	}
//  ��  ��������
	public String toSendNotice(){
		return "success";
	}
//  �г������û�  //��ҳ
	public String userList(){
		
		String p = req.getParameter("page");
		
		if(p==null){   //������Ͻ�   �û����� ����
			p = "1";
		}
		int page = Integer.parseInt(p);

		User dbUser = (User) session.getAttribute("dbUser");
		if(dbUser.getAuthority()==1){
			
			List<User> userList =  userService.listUserByPage(dbUser,page,5);
			req.setAttribute("userList", userList);
		}
		
		int allCount = userService.getAllUserCount();
		
		int pageCount = allCount%5==0? allCount/5
				:allCount/5+1;
		
		req.setAttribute("pageCount", pageCount);
		
		req.setAttribute("currentPage", page);
		
		
		return "success";
	}
//  ���ݲ�ѯ���� name  �г������û�  //��ҳ
	public String userList1() throws Exception{
		
		String p = req.getParameter("page");
		
		if(p==null){   //������Ͻ�   �û����� ����
			p = "1";
		}
		int page = Integer.parseInt(p);
		String name=new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");

		List<User> userList =  userService.listUserByName(name);
		req.setAttribute("userList", userList);
		
		int allCount = userList.size();
		
		int pageCount = allCount%5==0? allCount/5
				:allCount/5+1;
		
		req.setAttribute("pageCount", pageCount);
		
		req.setAttribute("currentPage", page);
		
		return "success";
	}
//  ��������Ȩ�޷�ҳҳ��    �г������û�  //��ҳ
	public String userList2() throws Exception{
		
		String p = req.getParameter("page");
		
		if(p==null){   //������Ͻ�   �û����� ����
			p = "1";
		}
		int page = Integer.parseInt(p);

		User dbUser = (User) session.getAttribute("dbUser");
		if(dbUser.getAuthority()==1){
			
			List<User> userList =  userService.listUserByPage(dbUser,page,5);
			req.setAttribute("userList", userList);
		}
		
		int allCount = userService.getAllUserCount();
		
		int pageCount = allCount%5==0? allCount/5
				:allCount/5+1;
		
		req.setAttribute("pageCount", pageCount);
		
		req.setAttribute("currentPage", page);
		
		
		return "success";
	}
//  ��  Ȩ��չʾҳ��
	public String toShowQx(){
		return "success";
	}
//  ��  �û�Ȩ��չʾҳ��
	public String userQx(){
		return "success";
	}	
//  ����  Ȩ��
	public String upgrade() throws Exception{
		
		String username=new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		user.setUsername(username);
		//System.out.println("������"+username);
		
		String sex=new String(user.getSex().getBytes("ISO-8859-1"),"UTF-8");
		user.setSex(sex);
		//System.out.println("�Ա���"+sex);
		
		String addr=new String(user.getAddr().getBytes("ISO-8859-1"),"UTF-8");
		user.setAddr(addr);
		//System.out.println("סַ��"+addr);
		
		
		String psw=new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
		user.setPassword(psw);
		//System.out.println("������"+psw);
		
		String department=new String(user.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		user.setDepartment(department);
		//System.out.println("����ϵ��"+department);
		
		user.setAuthority(1);

		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		
		userService.updateUser(user);
		return "success";
	}
//  ��  ����Աģ����û�����
	public String toUserManage(){
		return "success";
	}
//  չʾ  һ���û���Ϣ����
	public String userDetails() throws Exception{
		String username=new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		user.setUsername(username);
		//System.out.println("������"+username);
		
		String sex=new String(user.getSex().getBytes("ISO-8859-1"),"UTF-8");
		user.setSex(sex);
		//System.out.println("�Ա���"+sex);
		
		String addr=new String(user.getAddr().getBytes("ISO-8859-1"),"UTF-8");
		user.setAddr(addr);
		//System.out.println("סַ��"+addr);
		
		
		String psw=new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
		user.setPassword(psw);
		//System.out.println("������"+psw);
		
		String department=new String(user.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		user.setDepartment(department);
		//System.out.println("����ϵ��"+department);
		
		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		return "success";
	}
//  ɾ��  һ���û�
	public String deleteUser(){
		userService.deleteUser(user);
		return "success";
	}
//  ��  ����û�
	public String toUserAdd(){
		return "success";
	}
//  ����û�
	public String addUser(){
		userService.addUser(user);
		return "success";
	}
//  ��  ϵͳ����Ա  ����
	public String systemFunc(){
		return "success";
	}
//  ��  �������ݿ����
	public String toIntoDB(){
		return "success";
	}
	
//  excel�������ݿ�
	public String upload() throws Exception {
		
		System.out.println("����upload������"+excel);
		System.out.println("����upload������"+excelFileName);
		System.out.println("����upload������"+excelContentType);
		
		String load = ServletActionContext.getServletContext().getRealPath(
					"/upload/");
			// �õ�·��
		File fileDirect = new File(load);// ����Ŀ¼
			// ��Ҫע����� ��һ���ϴ���ʱ�� ���Ŀ¼��������
			// ���Ҫ���пմ��� ������ �򴴽�
		if (!fileDirect.exists()) {
			fileDirect.mkdirs();
		}

		File saveUpFile = new File(fileDirect, this.excelFileName);// ������������ļ�
		System.out.println(saveUpFile.getPath());
			
		FileUtils.copyFile(excel, saveUpFile);// ���ļ����и��Ʊ���
			

		//�õ���������е�����
        List<Workload> listExcel=Wl_teacherService.getAllByExcel(saveUpFile.getPath());
        /*//�õ����ݿ�������е�����
        List<Workload> listDb=Wl_teacherService.getAllByDb();*/
        
        DBHelper db=new DBHelper();
        
        for (Workload wl_teacher : listExcel) {
            int id=wl_teacher.getId();
            if (!Wl_teacherService.isExist(id)) {
                //�����ھ����
                String sql="insert into wl_teacher0 (department,t_id,t_name,name,time,classes,amount,wl,type,remark,term) values(?,?,?,?,?,?,?,?,?,?,?)";
                String[] str=new String[]{wl_teacher.getDepartment(),wl_teacher.getT_id()+"",
                		wl_teacher.getT_name(),wl_teacher.getName(),wl_teacher.getTime(),
                		wl_teacher.getClasses(),wl_teacher.getAmount()+"",wl_teacher.getWl()+"",
                		wl_teacher.getType(),wl_teacher.getRemark(),wl_teacher.getTerm()};
                db.AddU(sql, str);
            }else {
                //���ھ͸���
                String sql="update wl_teacher0 set department=?,t_id=?,t_name=?,name=?,time=?,classes=?,amount=?,wl=?,type=?,remark=?,term=? where id=?";
                String[] str=new String[]{wl_teacher.getDepartment(),wl_teacher.getT_id()+"",
                		wl_teacher.getT_name(),wl_teacher.getName(),wl_teacher.getTime(),
                		wl_teacher.getClasses(),wl_teacher.getAmount()+"",wl_teacher.getWl()+"",
                		wl_teacher.getType(),wl_teacher.getRemark(),wl_teacher.getTerm(),id+""};
                db.AddU(sql, str);
            }
        }
		
		return "success";
	}

//  ����д��������Ϣҳ��
	public String toWlFill(){
		return "success";
	}
//  ������������ҳ��,��ҳչʾ���й�������Ϣ
	public String toWlManage(){
		//System.out.println("��ǰ����Ա��:"+user.getUsername()+","+user.getPassword());
		
		String p = req.getParameter("page");
		
		if(p==null){
			p = "1";
		}
		int page = Integer.parseInt(p);
		User dbUser = (User) session.getAttribute("dbUser");
		if(dbUser.getAuthority()==1){
			
			List<Workload> workloadList = workloadService.listWorkloadByPage(page,5);
			req.setAttribute("workloadList", workloadList);
		}
		
		int allCount = workloadService.getAllWorkloadCount();
		int pageCount = allCount%5==0? allCount/5 :allCount/5+1;
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", page);
		
		return "success";
	}
//  ����ְ���Լ��˶Թ�����
	public String toWlSure(){
		
		String p = req.getParameter("page");
		
		if(p==null){
			p = "1";
		}
		int page = Integer.parseInt(p);
		User dbUser = (User) session.getAttribute("dbUser");
		
		if(dbUser.getAuthority()==1){
			
			List<Workload> workloadList = workloadService.listWorkloadByPageAndT_id(page,5,dbUser.getId());
			req.setAttribute("workloadList", workloadList);
		}
		
		int myWlCount = workloadService.getMyWlCount(dbUser.getId());
		int pageCount = myWlCount%5==0? myWlCount/5 :myWlCount/5+1;
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", page);
		
		return "success";
	}
	
//  ����Ա����  ��˹�����ҳ��(��,ȫ���ҳչʾ����˱�wl_teacher1)
	public String toCheckWl(){
		
		String p = req.getParameter("page");
		
		if(p==null){
			p = "1";
		}
		int page = Integer.parseInt(p);
		User dbUser = (User) session.getAttribute("dbUser");
		if(dbUser.getAuthority()==1){
			
			List<Cworkload> cworkloadList = cworkloadService.listCworkloadByPage(page,5);
			req.setAttribute("cworkloadList", cworkloadList);
		}
		
		int allCount = cworkloadService.getAllWorkloadCount();
		int pageCount = allCount%5==0? allCount/5 :allCount/5+1;
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", page);
		
		return "success";
	}
//  ����Ա��˹�����(op�ж�)
	public String checkCworkload(){
		
		Workload workload = new Workload();
		
		int op = Integer.parseInt(req.getParameter("op"));
		String id = req.getParameter("id");
		String department = req.getParameter("department");
		String t_id = req.getParameter("t_id");
		String t_name = req.getParameter("t_name");
		String name = req.getParameter("name");
		String time = req.getParameter("time");
		String classes = req.getParameter("classes");
		String amount = req.getParameter("amount");
		String wl = req.getParameter("wl");
		String type = req.getParameter("type");
		String remark = req.getParameter("remark");
		String term = req.getParameter("term");
	
		if(op==0){
			System.out.println("����-��ˣ�");
			workload.setDepartment(department);
			workload.setT_id(Integer.parseInt(t_id));
			workload.setT_name(t_name);
			workload.setName(name);
			workload.setTime(time);
			workload.setClasses(classes);
			workload.setAmount(Integer.parseInt(amount));
			workload.setWl(Float.parseFloat(wl));
			workload.setType(type);
			workload.setRemark(remark);
			workload.setTerm(term);
			
			workloadService.addWorkload(workload);
			cworkloadService.deleteById(Integer.parseInt(id));
			
		}else if(op<0){
			System.out.println("�޸�-���");
			workload.setId(-op);
			workload.setDepartment(department);
			workload.setT_id(Integer.parseInt(t_id));
			workload.setT_name(t_name);
			workload.setName(name);
			workload.setTime(time);
			workload.setClasses(classes);
			workload.setAmount(Integer.parseInt(amount));
			workload.setWl(Float.parseFloat(wl));
			workload.setType(type);
			workload.setRemark(remark);
			workload.setTerm(term);
			
			workloadService.updateWorkload(workload);
			cworkloadService.deleteById(Integer.parseInt(id));
		}else{
			System.out.println("ɾ��-���");
			
			workload.setId(op);
			workload.setDepartment(department);
			workload.setT_id(Integer.parseInt(t_id));
			workload.setT_name(t_name);
			workload.setName(name);
			workload.setTime(time);
			workload.setClasses(classes);
			workload.setAmount(Integer.parseInt(amount));
			workload.setWl(Float.parseFloat(wl));
			workload.setType(type);
			workload.setRemark(remark);
			workload.setTerm(term);
			
			workloadService.delete(workload);
			cworkloadService.deleteById(Integer.parseInt(id));
		}
		
		return "success";
	}
//  �����ù�ʽҳ�棬��չʾ��ǰ��ʽҳ��
	public String listFormula(){
		String p = req.getParameter("page");
		
		if(p==null){
			p = "1";
		}
		int page = Integer.parseInt(p);

		int allCount = formulaService.getAllFormulaCount();
		
		List<Formula> formulaList =  formulaService.listFormulaByPage(page,allCount);
		req.setAttribute("formulaList", formulaList);
		
		
		int pageCount = 1;
		
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", page);
		
		return "success";
	}

	
	
	
	
	
	
	
	
	
	

	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getExcelContentType() {
		return excelContentType;
	}
	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
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
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public NoticeService getNoticeService() {
		return noticeService;
	}
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public WorkloadService getWorkloadService() {
		return workloadService;
	}
	public void setWorkloadService(WorkloadService workloadService) {
		this.workloadService = workloadService;
	}
	public CworkloadService getCworkloadService() {
		return cworkloadService;
	}
	public void setCworkloadService(CworkloadService cworkloadService) {
		this.cworkloadService = cworkloadService;
	}
	public Cworkload getCworkload() {
		return cworkload;
	}
	public void setCworkload(Cworkload cworkload) {
		this.cworkload = cworkload;
	}
	public FormulaService getFormulaService() {
		return formulaService;
	}
	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}
}
