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
	
	//文件名称
	private String excelFileName;
	
	//文件类型
	private String excelContentType;
	//注意：文件名称和文件类型的名称前缀必须相同，
	
	private User user;
	private Cworkload cworkload;
	
	
	private HttpServletRequest req;

	private HttpSession session;
	
	private UserService userService;
	private NoticeService noticeService;
	private WorkloadService workloadService;
	private CworkloadService cworkloadService;
	private FormulaService formulaService;
	
	
	//  用户   登录
	public String login(){
		System.out.println(user.getUsername()+","+user.getPassword());
		
		/*if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
			System.out.println("系统管理员登录成功！");
			User loginUser = new User();
			loginUser.setUsername("admin");
			session.setAttribute("dbUser", loginUser);
			return "success";
		}*/
		
		User loginUser = userService.userLogin(user);
		
		if(loginUser!=null){
			session.setAttribute("dbUser", loginUser);
			
			String p = req.getParameter("page");
			
			if(p==null){   //点击左上角   用户管理 来的
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
//  退出  系统
	public String exit(){
		session.removeAttribute("dbUser");
		return "success";
	}
//  注册 新用户
	public String reg() {
		System.out.println("注册新用户的名字是："+user.getUsername());
		userService.regUser(user);
		return "success";
	}
//  到 用户模块页面	
	public String userFunc(){
		User user = (User) session.getAttribute("dbUser");
		return "success";
	}
//  到 管理员模块页面	
	public String adminFunc(){
		User user = (User) session.getAttribute("dbUser");
		if(user.getAuthority()==1){
			return "success";
		}else{
			return "failure";
		}
	}
//  到 修改个人信息页面(查看个人信息)	
	public String toUserModify(){
		User dbUser = (User) session.getAttribute("dbUser");
		User user = userService.getSingleUser(dbUser);
		req.setAttribute("user", user);
		
		
		return "success";
	}
//  修改 个人信息	
	public String update(){
		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		userService.updateUser(user);
		return "success";
	}
	
//  到  修改  密码
	public String pswDetails(){	
		return "success";
	}
//   修改  密码
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
//  到  新增公告
	public String toSendNotice(){
		return "success";
	}
//  列出所有用户  //分页
	public String userList(){
		
		String p = req.getParameter("page");
		
		if(p==null){   //点击左上角   用户管理 来的
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
//  根据查询条件 name  列出所有用户  //分页
	public String userList1() throws Exception{
		
		String p = req.getParameter("page");
		
		if(p==null){   //点击左上角   用户管理 来的
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
//  关于设置权限分页页面    列出所有用户  //分页
	public String userList2() throws Exception{
		
		String p = req.getParameter("page");
		
		if(p==null){   //点击左上角   用户管理 来的
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
//  到  权限展示页面
	public String toShowQx(){
		return "success";
	}
//  到  用户权限展示页面
	public String userQx(){
		return "success";
	}	
//  升级  权限
	public String upgrade() throws Exception{
		
		String username=new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		user.setUsername(username);
		//System.out.println("姓名是"+username);
		
		String sex=new String(user.getSex().getBytes("ISO-8859-1"),"UTF-8");
		user.setSex(sex);
		//System.out.println("性别是"+sex);
		
		String addr=new String(user.getAddr().getBytes("ISO-8859-1"),"UTF-8");
		user.setAddr(addr);
		//System.out.println("住址是"+addr);
		
		
		String psw=new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
		user.setPassword(psw);
		//System.out.println("密码是"+psw);
		
		String department=new String(user.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		user.setDepartment(department);
		//System.out.println("所在系是"+department);
		
		user.setAuthority(1);

		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		
		userService.updateUser(user);
		return "success";
	}
//  到  管理员模块的用户管理
	public String toUserManage(){
		return "success";
	}
//  展示  一个用户信息详情
	public String userDetails() throws Exception{
		String username=new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8");
		user.setUsername(username);
		//System.out.println("姓名是"+username);
		
		String sex=new String(user.getSex().getBytes("ISO-8859-1"),"UTF-8");
		user.setSex(sex);
		//System.out.println("性别是"+sex);
		
		String addr=new String(user.getAddr().getBytes("ISO-8859-1"),"UTF-8");
		user.setAddr(addr);
		//System.out.println("住址是"+addr);
		
		
		String psw=new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
		user.setPassword(psw);
		//System.out.println("密码是"+psw);
		
		String department=new String(user.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		user.setDepartment(department);
		//System.out.println("所在系是"+department);
		
		System.out.println(user.getId()+","+user.getUsername()+","+user.getSex()+","
				+user.getAge()+","+user.getAddr()+","+user.getAuthority()+","
					+user.getPassword()+","+user.getDepartment());
		return "success";
	}
//  删除  一个用户
	public String deleteUser(){
		userService.deleteUser(user);
		return "success";
	}
//  到  添加用户
	public String toUserAdd(){
		return "success";
	}
//  添加用户
	public String addUser(){
		userService.addUser(user);
		return "success";
	}
//  到  系统管理员  界面
	public String systemFunc(){
		return "success";
	}
//  到  导入数据库界面
	public String toIntoDB(){
		return "success";
	}
	
//  excel导入数据库
	public String upload() throws Exception {
		
		System.out.println("进入upload方法！"+excel);
		System.out.println("进入upload方法！"+excelFileName);
		System.out.println("进入upload方法！"+excelContentType);
		
		String load = ServletActionContext.getServletContext().getRealPath(
					"/upload/");
			// 得到路径
		File fileDirect = new File(load);// 创建目录
			// 需要注意的是 第一次上传的时候 这个目录并不存在
			// 因此要做判空处理 不存在 则创建
		if (!fileDirect.exists()) {
			fileDirect.mkdirs();
		}

		File saveUpFile = new File(fileDirect, this.excelFileName);// 创建被保存的文件
		System.out.println(saveUpFile.getPath());
			
		FileUtils.copyFile(excel, saveUpFile);// 对文件进行复制保存
			

		//得到表格中所有的数据
        List<Workload> listExcel=Wl_teacherService.getAllByExcel(saveUpFile.getPath());
        /*//得到数据库表中所有的数据
        List<Workload> listDb=Wl_teacherService.getAllByDb();*/
        
        DBHelper db=new DBHelper();
        
        for (Workload wl_teacher : listExcel) {
            int id=wl_teacher.getId();
            if (!Wl_teacherService.isExist(id)) {
                //不存在就添加
                String sql="insert into wl_teacher0 (department,t_id,t_name,name,time,classes,amount,wl,type,remark,term) values(?,?,?,?,?,?,?,?,?,?,?)";
                String[] str=new String[]{wl_teacher.getDepartment(),wl_teacher.getT_id()+"",
                		wl_teacher.getT_name(),wl_teacher.getName(),wl_teacher.getTime(),
                		wl_teacher.getClasses(),wl_teacher.getAmount()+"",wl_teacher.getWl()+"",
                		wl_teacher.getType(),wl_teacher.getRemark(),wl_teacher.getTerm()};
                db.AddU(sql, str);
            }else {
                //存在就更新
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

//  到填写工作量信息页面
	public String toWlFill(){
		return "success";
	}
//  到工作量管理页面,分页展示所有工作量信息
	public String toWlManage(){
		//System.out.println("当前管理员是:"+user.getUsername()+","+user.getPassword());
		
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
//  到教职工自己核对工作量
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
	
//  管理员进行  审核工作量页面(即,全查分页展示待审核表wl_teacher1)
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
//  管理员审核工作量(op判断)
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
			System.out.println("新增-审核！");
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
			System.out.println("修改-审核");
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
			System.out.println("删除-审核");
			
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
//  到设置公式页面，即展示当前公式页面
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
