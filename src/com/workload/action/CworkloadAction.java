package com.workload.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.workload.domain.Cworkload;
import com.workload.domain.User;
import com.workload.domain.Workload;
import com.workload.service.CworkloadService;
import com.workload.service.FormulaService;
import com.workload.service.UserService;
import com.workload.service.WorkloadService;

@Controller
@Scope(value="prototype")
public class CworkloadAction implements ServletRequestAware{
	
	private User user;
	private Cworkload cworkload;
	
	private HttpServletRequest req;
	

	private HttpSession session;
	
	private CworkloadService cworkloadService;
	private UserService userService;
	
	private WorkloadService workloadService;
	private FormulaService formulaService;
	
	
//  普通教职工,新增自己的工作量
	public String addCworkload(){
		User user = (User) session.getAttribute("dbUser");
		
		cworkload = new Cworkload();
		
		String department = req.getParameter("department");
		String t_id = req.getParameter("t_id");
		String t_name = req.getParameter("t_name");
		String name = req.getParameter("name");
		String time = req.getParameter("time");
		String classes = req.getParameter("classes");
		String amount = req.getParameter("amount");
		//String wl = req.getParameter("wl");  //
		String type = req.getParameter("type");
		String remark = req.getParameter("remark");
		String term = req.getParameter("term");
		String reason = req.getParameter("reason");
		
		//1.根据type拿到合适的公式
		//2.根据公式计算工作量
		float w = 0;
		String formula = formulaService.getFormulaByType(type);
		System.out.println(formula);
		
		//课堂的计算
		if(type.equals("课堂")){
			float eratio = 1;int sh = 0;String[] s = null;
			if(formula.contains("*")){
				s = formula.split("*");
			}else{
				s = new String[]{"sh"};
			}
			if(s.length!=1){
				eratio = Float.parseFloat(s[1]);  //特殊系数
			}
			if(s[0].equals("sh")){
				sh = Integer.parseInt(time);  //学时
			}
			if(Integer.parseInt(amount)<30){
				w = sh*eratio;
			}else{
				w = (float) (sh*(eratio+((Integer.parseInt(amount))-30)*0.015));
			}
			System.out.println("课堂工作量为："+w);
		}
		
		//校内实习的计算
		if(type.equals("校内实习")){
			int days = 0,amount1 = Integer.parseInt(amount);
			if(time.contains("周")){
				days = 7*Integer.parseInt(time.replaceAll("周", ""));
				System.out.println("days="+days);
				w = days*amount1/21;
			}
			System.out.println("校内实习工作量为："+w);
		}
		
		//校外实习的计算
		if(type.equals("校外实习")){
			int days = 0,amount1 = Integer.parseInt(amount);
			if(time.contains("周")){
				days = 7*Integer.parseInt(time.replaceAll("周", ""));
				System.out.println("days="+days);
				w = (float) (days*1.5*amount1/20);
			}
			System.out.println("校外实习工作量为："+w);
		}
		
		//课程设计的计算
		if(type.equals("课程设计")){
			int weeks = 0,amount1 = Integer.parseInt(amount);
			if(time.contains("周")){
				weeks = Integer.parseInt(time.replaceAll("周", ""));
				System.out.println("weeks="+weeks);
				w = (float) (weeks*amount1*0.6);
			}
			System.out.println("课程设计工作量为："+w);
		}
		
		//毕业设计的计算
		if(type.equals("毕业设计")){
			int weeks = 0,amount1 = Integer.parseInt(amount);
			if(time.contains("周")){
				weeks = Integer.parseInt(time.replaceAll("周", ""));
				System.out.println("weeks="+weeks);
				w = (float) (weeks*amount1);
			}
			System.out.println("毕业设计工作量为："+w);
		}
		
		//实验的计算
		if(type.equals("实验")){
			int each= 10,sh = 0,amount1 = Integer.parseInt(amount);
			if(time.contains("周")){
				sh = Integer.parseInt(time.replaceAll("周", ""));
				w = (float) (sh*(amount1/each)*0.6);
			}
			System.out.println("实验工作量为："+w);
		}
		
		
		if(department==null){
			cworkload.setDepartment(user.getDepartment());
		}else{
			cworkload.setDepartment(department);
		}
		if(t_id==null){
			cworkload.setT_id(user.getId());
		}else{
			cworkload.setT_id(Integer.parseInt(t_id));
		}
		if(t_name==null){
			cworkload.setT_name(user.getUsername());
		}else{
			cworkload.setT_name(t_name);
		}
		cworkload.setName(name);
		cworkload.setTime(time);
		cworkload.setClasses(classes);
		cworkload.setAmount(Integer.parseInt(amount));
		//cworkload.setWl(Float.parseFloat(wl));  //
		cworkload.setWl(w);
		cworkload.setType(type);
		cworkload.setRemark(remark);
		cworkload.setTerm(term);
		cworkload.setOp(0);
		cworkload.setReason(reason);
		
		cworkloadService.addWorkload(cworkload);
		System.out.println("新增工作量信息为："+cworkload.toString());
		
		return "success";
	}


//  删除一条工作量
	public String deleteCworkload(){
		
		cworkload = new Cworkload();
		
		String id = req.getParameter("id");
		cworkload.setOp(Integer.parseInt(id));
		String reason = req.getParameter("reason");
		Workload wl = workloadService.getWlById(Integer.parseInt(id));
		cworkload.setAmount(wl.getAmount());
		cworkload.setClasses(wl.getClasses());
		cworkload.setDepartment(wl.getDepartment());
		cworkload.setName(wl.getName());
		cworkload.setRemark(wl.getRemark());
		cworkload.setT_id(wl.getT_id());
		cworkload.setT_name(wl.getT_name());
		cworkload.setTerm(wl.getTerm());
		cworkload.setTime(wl.getTime());
		cworkload.setType(wl.getType());
		cworkload.setWl(wl.getWl());
		cworkload.setReason(reason);
		
		cworkloadService.addWorkload(cworkload);
		
		return "success";
	}
//  修改工作量
	public String updateCworkload(){
		String id = req.getParameter("id");
		User user = (User) session.getAttribute("dbUser");
		
		cworkload = new Cworkload();
		
		String reason = req.getParameter("reason");
		String department = req.getParameter("department");
		String t_id = req.getParameter("t_id");
		String t_name = req.getParameter("t_name");
		String name = req.getParameter("name");
		String time = req.getParameter("time");
		String classes = req.getParameter("classes");
		String amount = req.getParameter("amount");
		//String wl = req.getParameter("wl");  //
		String type = req.getParameter("type");
		String remark = req.getParameter("remark");
		String term = req.getParameter("term");
		
		
		//1.根据type拿到合适的公式
				//2.根据公式计算工作量
				float w = 0;
				String formula = formulaService.getFormulaByType(type);
				System.out.println(formula);
				
				//课堂的计算
				if(type.equals("课堂")){
					float eratio = 1;int sh = 0;String[] s = null;
					if(formula.contains("*")){
						s = formula.split("*");
					}else{
						s = new String[]{"sh"};
					}
					if(s.length!=1){
						eratio = Float.parseFloat(s[1]);  //特殊系数
					}
					if(s[0].equals("sh")){
						sh = Integer.parseInt(time);  //学时
					}
					if(Integer.parseInt(amount)<30){
						w = sh*eratio;
					}else{
						w = (float) (sh*(eratio+((Integer.parseInt(amount))-30)*0.015));
					}
					System.out.println("课堂工作量为："+w);
				}
				
				//校内实习的计算
				if(type.equals("校内实习")){
					int days = 0,amount1 = Integer.parseInt(amount);
					if(time.contains("周")){
						days = 7*Integer.parseInt(time.replaceAll("周", ""));
						System.out.println("days="+days);
						w = days*amount1/21;
					}
					System.out.println("校内实习工作量为："+w);
				}
				
				//校外实习的计算
				if(type.equals("校外实习")){
					int days = 0,amount1 = Integer.parseInt(amount);
					if(time.contains("周")){
						days = 7*Integer.parseInt(time.replaceAll("周", ""));
						System.out.println("days="+days);
						w = (float) (days*1.5*amount1/20);
					}
					System.out.println("校外实习工作量为："+w);
				}
				
				//课程设计的计算
				if(type.equals("课程设计")){
					int weeks = 0,amount1 = Integer.parseInt(amount);
					if(time.contains("周")){
						weeks = Integer.parseInt(time.replaceAll("周", ""));
						System.out.println("weeks="+weeks);
						w = (float) (weeks*amount1*0.6);
					}
					System.out.println("课程设计工作量为："+w);
				}
				
				//毕业设计的计算
				if(type.equals("毕业设计")){
					int weeks = 0,amount1 = Integer.parseInt(amount);
					if(time.contains("周")){
						weeks = Integer.parseInt(time.replaceAll("周", ""));
						System.out.println("weeks="+weeks);
						w = (float) (weeks*amount1);
					}
					System.out.println("毕业设计工作量为："+w);
				}
				
				//实验的计算
				if(type.equals("实验")){
					int each= 10,sh = 0,amount1 = Integer.parseInt(amount);
					if(time.contains("周")){
						sh = Integer.parseInt(time.replaceAll("周", ""));
						w = (float) (sh*(amount1/each)*0.6);
					}
					System.out.println("实验工作量为："+w);
				}
		
		
		
		if(department==null){
			cworkload.setDepartment(user.getDepartment());
		}else{
			cworkload.setDepartment(department);
		}
		if(t_id==null){
			cworkload.setT_id(user.getId());
		}else{
			cworkload.setT_id(Integer.parseInt(t_id));
		}
		if(t_name==null){
			cworkload.setT_name(user.getUsername());
		}else{
			cworkload.setT_name(t_name);
		}
		cworkload.setName(name);
		cworkload.setTime(time);
		cworkload.setClasses(classes);
		cworkload.setAmount(Integer.parseInt(amount));
		cworkload.setWl(w);  //
		cworkload.setType(type);
		cworkload.setRemark(remark);
		cworkload.setTerm(term);
		cworkload.setOp(-Integer.parseInt(id));
		cworkload.setReason(reason);
		System.out.println("待修改的："+cworkload.toString());
		
		cworkloadService.addWorkload(cworkload);
		
		return "success";
	}
//  展示待审核工作量详情
	public String cworkloadDetails() throws Exception{
		
		String department = new String(cworkload.getDepartment().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setDepartment(department);
		
		String t_name = new String(cworkload.getT_name().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setT_name(t_name);

		String name = new String(cworkload.getName().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setName(name);

		String time = new String(cworkload.getTime().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setTime(time);

		String classes = new String(cworkload.getClasses().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setClasses(classes);

		String type = new String(cworkload.getType().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setType(type);
		
		String remark = new String(cworkload.getRemark().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setRemark(remark);

		String term = new String(cworkload.getTerm().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setTerm(term);
		
		String reason = new String(cworkload.getReason().getBytes("ISO-8859-1"),"UTF-8");
		cworkload.setReason(reason);
		
		System.out.println(cworkload.toString());
		
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
	
	public Cworkload getCworkload() {
		return cworkload;
	}


	public void setCworkload(Cworkload cworkload) {
		this.cworkload = cworkload;
	}


	public CworkloadService getCworkloadService() {
		return cworkloadService;
	}


	public void setCworkloadService(CworkloadService cworkloadService) {
		this.cworkloadService = cworkloadService;
	}


	public WorkloadService getWorkloadService() {
		return workloadService;
	}


	public void setWorkloadService(WorkloadService workloadService) {
		this.workloadService = workloadService;
	}


	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public FormulaService getFormulaService() {
		return formulaService;
	}


	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}


}
