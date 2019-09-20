package com.workload.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.workload.domain.Series;
import com.workload.domain.User;
import com.workload.domain.Workload;
import com.workload.service.UserService;
import com.workload.service.WorkloadService;

import net.sf.json.JSONObject;

@Controller
@Scope(value = "prototype")
public class WorkloadAction implements ServletRequestAware, ServletResponseAware {

	private User user;
	private Workload workload;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	private HttpSession session;

	private WorkloadService workloadService;
	private UserService userService;

	// 普通教职工,新增自己的工作量
	public String addWorkload() {
		User user = (User) session.getAttribute("dbUser");

		workload = new Workload();

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

		if (department == null) {
			workload.setDepartment(user.getDepartment());
		} else {
			workload.setDepartment(department);
		}
		if (t_id == null) {
			workload.setT_id(user.getId());
		} else {
			workload.setT_id(Integer.parseInt(t_id));
		}
		if (t_name == null) {
			workload.setT_name(user.getUsername());
		} else {
			workload.setT_name(t_name);
		}
		workload.setName(name);
		workload.setTime(time);
		workload.setClasses(classes);
		workload.setAmount(Integer.parseInt(amount));
		workload.setWl(Float.parseFloat(wl));
		workload.setType(type);
		workload.setRemark(remark);
		workload.setTerm(term);

		workloadService.addWorkload(workload);
		System.out.println("新增工作量信息为：" + workload.toString());

		return "success";
	}

	// 管理员，新增的工作量
	public String addWl() {

		workload = new Workload();

		String t_id = req.getParameter("t_id");
		String name = req.getParameter("name");
		String time = req.getParameter("time");
		String classes = req.getParameter("classes");
		String amount = req.getParameter("amount");
		String wl = req.getParameter("wl");
		String type = req.getParameter("type");
		String remark = req.getParameter("remark");
		String term = req.getParameter("term");

		User u = userService.getUserById(Integer.parseInt(t_id));

		workload.setDepartment(u.getDepartment());

		workload.setT_id(Integer.parseInt(t_id));

		workload.setT_name(u.getUsername());

		workload.setName(name);
		workload.setTime(time);
		workload.setClasses(classes);
		workload.setAmount(Integer.parseInt(amount));
		workload.setWl(Float.parseFloat(wl));
		workload.setType(type);
		workload.setRemark(remark);
		workload.setTerm(term);

		workloadService.addWorkload(workload);
		System.out.println("新增工作量信息为：" + workload.toString());

		return "success";
	}

	// 列出所有工作量 //分页
	public String workloadList() {

		String p = req.getParameter("page");

		if (p == null) {
			p = "1";
		}
		int page = Integer.parseInt(p);

		User dbUser = (User) session.getAttribute("dbUser");

		List<Workload> workloadList = workloadService.listWorkloadByPage(page, 5);
		req.setAttribute("workloadList", workloadList);

		int allCount = workloadService.getAllWorkloadCount();

		int pageCount = allCount % 5 == 0 ? allCount / 5 : allCount / 5 + 1;

		req.setAttribute("pageCount", pageCount);

		req.setAttribute("currentPage", page);

		return "success";
	}

	// 一条工作量详情
	public String workloadDetails() throws Exception {

		String department = new String(workload.getDepartment().getBytes("ISO-8859-1"), "UTF-8");
		workload.setDepartment(department);

		String t_name = new String(workload.getT_name().getBytes("ISO-8859-1"), "UTF-8");
		workload.setT_name(t_name);

		String name = new String(workload.getName().getBytes("ISO-8859-1"), "UTF-8");
		workload.setName(name);

		String time = new String(workload.getTime().getBytes("ISO-8859-1"), "UTF-8");
		workload.setTime(time);

		String classes = new String(workload.getClasses().getBytes("ISO-8859-1"), "UTF-8");
		workload.setClasses(classes);

		String type = new String(workload.getType().getBytes("ISO-8859-1"), "UTF-8");
		workload.setType(type);

		String remark = new String(workload.getRemark().getBytes("ISO-8859-1"), "UTF-8");
		workload.setRemark(remark);

		String term = new String(workload.getTerm().getBytes("ISO-8859-1"), "UTF-8");
		workload.setTerm(term);

		System.out.println(workload.toString());

		return "success";
	}

	// 修改一条工作量信息
	public String update() {
		System.out.println("修改前信息：" + workload.toString());
		workloadService.updateWorkload(workload);
		System.out.println("修改后信息：" + workload.toString());
		return "success";
	}

	// 删除一条工作量
	public String delete() {
		System.out.println("要删除的工作量：" + workload.toString());
		workloadService.delete(workload);
		return "success";
	}

	// 到新增工作量
	public String toWorkloadAdd() {
		return "success";
	}

	// 根据 教师姓名t_name查询工作量
	public String workloadList1() throws Exception {

		String p = req.getParameter("page");

		if (p == null) {
			p = "1";
		}
		int page = Integer.parseInt(p);

		String t_name = new String(req.getParameter("t_name").getBytes("ISO-8859-1"), "UTF-8");
		List<Workload> workloadList = workloadService.listWorkloadByPageAndT_name(page, 5, t_name);

		req.setAttribute("workloadList", workloadList);

		int allCount = workloadService.getAllWorkloadCountByT_name(t_name);

		int pageCount = allCount % 5 == 0 ? allCount / 5 : allCount / 5 + 1;

		req.setAttribute("pageCount", pageCount);

		req.setAttribute("currentPage", page);

		return "success";
	}

	// 根据 学期term查询工作量
	public String workloadList2() throws Exception {
		User dbUser = (User) session.getAttribute("dbUser");
		String p = req.getParameter("page");

		if (p == null) {
			p = "1";
		}
		int page = Integer.parseInt(p);

		String term = new String(req.getParameter("term").getBytes("ISO-8859-1"), "UTF-8");
		List<Workload> workloadList = workloadService.listWorkloadByPageAndTermAndT_id(page, 5, term, dbUser.getId());

		req.setAttribute("workloadList", workloadList);

		int allCount = workloadService.getAllWorkloadCountByTermAndT_id(term, dbUser.getId());

		int pageCount = allCount % 5 == 0 ? allCount / 5 : allCount / 5 + 1;

		req.setAttribute("pageCount", pageCount);

		req.setAttribute("currentPage", page);

		return "success";
	}

	// 到分类统计页面，拿到需要的数据
	public String toClassifyCount() {

		final List<Workload> wlList = workloadService.getAllWorkload();

		Iterator<Workload> it1 = wlList.iterator();
		while (it1.hasNext()) {
			Workload w = it1.next();
			if (w.getType().equals("课堂")) {
				w.setType("1");
			} else {
				w.setType("2");
			}
		}

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				if (o1.getT_id() == o2.getT_id()) {
					int i = Integer.parseInt(o1.getType()) - Integer.parseInt(o2.getType());
					return i;
				} else {
					return o1.getT_id() - o2.getT_id();
				}
			}
		});

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				int i = o1.getT_id() - o2.getT_id();
				if (i == 0 && o1.getType().equals("1") && o2.getType().equals("1")) {
					o1.setWl(o1.getWl() + o2.getWl());
					o2.setWl(0);
				}
				return i;
			}
		});

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				int i = o1.getT_id() - o2.getT_id();
				if (i == 0 && o1.getType().equals("2") && o2.getType().equals("2")) {
					o1.setWl(o1.getWl() + o2.getWl());
					o2.setWl(0);
				}
				return i;
			}
		});

		Iterator<Workload> it2 = wlList.iterator();
		while (it2.hasNext()) {
			Workload w = it2.next();
			if (w.getType().equals("1")) {
				w.setType("课堂");
			} else {
				w.setType("实践");
			}

		}

		for (int i = wlList.size() - 1; i >= 0; i--) {
			if (wlList.get(i).getWl() == 0) {
				wlList.remove(i);
			}
		}

		req.setAttribute("wlList", wlList);

		return "success";
	}

	public String test() {
		return "success";
	}

	public void testEcharts() {

		//
		final List<Workload> wlList = workloadService.getAllWorkload();

		Iterator<Workload> it1 = wlList.iterator();
		while (it1.hasNext()) {
			Workload w = it1.next();
			if (w.getType().equals("课堂")) {
				w.setType("1");
			} else {
				w.setType("2");
			}
		}

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				if (o1.getT_id() == o2.getT_id()) {
					int i = Integer.parseInt(o1.getType()) - Integer.parseInt(o2.getType());
					return i;
				} else {
					return o1.getT_id() - o2.getT_id();
				}
			}
		});

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				int i = o1.getT_id() - o2.getT_id();
				if (i == 0 && o1.getType().equals("1") && o2.getType().equals("1")) {
					o1.setWl(o1.getWl() + o2.getWl());
					o2.setWl(0);
				}
				return i;
			}
		});

		Collections.sort(wlList, new Comparator<Workload>() {

			@Override
			public int compare(Workload o1, Workload o2) {
				int i = o1.getT_id() - o2.getT_id();
				if (i == 0 && o1.getType().equals("2") && o2.getType().equals("2")) {
					o1.setWl(o1.getWl() + o2.getWl());
					o2.setWl(0);
				}
				return i;
			}
		});

		Iterator<Workload> it2 = wlList.iterator();
		while (it2.hasNext()) {
			Workload w = it2.next();
			if (w.getType().equals("1")) {
				w.setType("课堂");
			} else {
				w.setType("实践");
			}

		}

		for (int i = wlList.size() - 1; i >= 0; i--) {
			if (wlList.get(i).getWl() == 0) {
				wlList.remove(i);
			}
		}
		
		// 得到集合的人数
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < wlList.size(); i++) {
			if (!lst.contains(wlList.get(i).getT_name())) {
				lst.add(wlList.get(i).getT_name());
			}
		}

		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("utf-8");
			resp.setCharacterEncoding("utf-8");

			PrintWriter write = resp.getWriter();
			List<String> xAxisData = new ArrayList<String>();
			List<JSONObject> seriesList = new ArrayList<JSONObject>();

			Iterator<String> it4 = lst.iterator();
			while (it4.hasNext()) {
				xAxisData.add(it4.next());
			}

			// 得到各类别的工作量(课堂)

			List<Float> list1 = new ArrayList<Float>();
			for (int j = 0; j < wlList.size(); j++) {
				if (wlList.get(j).getType().equals("课堂")) {
					System.out.println(wlList.get(j).getT_name());
					list1.add(wlList.get(j).getWl());
				}
			}
			Series series1 = new Series("课堂", Series.TYPE_LINE, list1);
			JSONObject jsonObject2 = new JSONObject();

			jsonObject2.put("name", series1.toName());
			jsonObject2.put("type", "bar");
			jsonObject2.put("data", series1.data);
			seriesList.add(jsonObject2);
			

			// 得到各类别的工作量(实践)
			List<Float> list2 = new ArrayList<Float>();
			for (int j = 0; j < wlList.size(); j++) {
				if (wlList.get(j).getType().equals("实践")) {
					list2.add(wlList.get(j).getWl());
				}
			}
			
			Series series2 = new Series("实践", Series.TYPE_LINE, list2);

			JSONObject jsonObject3 = new JSONObject();
			
			jsonObject3.put("name", series2.toName());
			jsonObject3.put("type", "bar");
			jsonObject3.put("data", series2.data);
			seriesList.add(jsonObject3);
			
			

			// xAxisData和seriesList转为json

			JSONObject jsonObject1 = new JSONObject();

			jsonObject1.put("xAxisData", xAxisData);

			jsonObject1.put("seriesList", seriesList);
			// 发送给前台
			System.out.println(jsonObject1.toString());
			
			write.write(jsonObject1.toString());
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession(true);

	}

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workload getWorkload() {
		return workload;
	}

	public void setWorkload(Workload workload) {
		this.workload = workload;
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

}
