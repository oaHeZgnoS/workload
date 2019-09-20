package com.workload.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.workload.domain.Formula;
import com.workload.domain.User;
import com.workload.service.FormulaService;

@Controller
@Scope(value="prototype")
public class FormulaAction implements ServletRequestAware {
	
	private HttpServletRequest req;
	private HttpSession session;
	private FormulaService formulaService;
	
//  列出所有公式,并分页
	public String formulaList(){
		
		String p = req.getParameter("page");
		
		if(p==null){
			p = "1";
		}
		int page = Integer.parseInt(p);

		List<Formula> formulaList =  formulaService.listFormulaByPage(page,5);
		req.setAttribute("formulaList", formulaList);
		
		int allCount = formulaService.getAllFormulaCount();
		
		int pageCount = allCount%5==0? allCount/5
				:allCount/5+1;
		
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", page);
		
		return "success";
	}
	
//  到更改公式页面
	public String toUpdateFml(){
		return "success";
	}
//  更改公式
	public String updateFml(){
		
		int flag = Integer.parseInt(req.getParameter("flag"));
		System.out.println("flag="+flag);
		String ratio1 = req.getParameter("ratio1");
		System.out.println("ratio1="+ratio1);
		String ratio2 = req.getParameter("ratio2");
		System.out.println("ratio2="+ratio2);
		float lmt = Float.parseFloat(req.getParameter("lmt"));
		System.out.println("lmt="+lmt);
		float eratio1 = Float.parseFloat(req.getParameter("eratio1"));
		System.out.println("eratio1="+eratio1);
		float eratio2 = Float.parseFloat(req.getParameter("eratio2"));
		System.out.println("eratio2="+eratio2);
		float eratio3 = Float.parseFloat(req.getParameter("eratio3"));
		System.out.println("eratio3="+eratio3);
		float eratio4 = Float.parseFloat(req.getParameter("eratio4"));
		System.out.println("eratio4="+eratio4);
		float eratio5 = Float.parseFloat(req.getParameter("eratio5"));
		System.out.println("eratio5="+eratio5);
		
		String fml1 = req.getParameter("fml1");
		System.out.println("fml1="+fml1);
		String fml2 = req.getParameter("fml2");
		System.out.println("fml2="+fml2);
		String fml3 = req.getParameter("fml3");
		System.out.println("fml3="+fml3);
		String fml4 = req.getParameter("fml4");
		System.out.println("fml4="+fml4);
		String fml5 = req.getParameter("fml5");
		System.out.println("fml5="+fml5);
		String fml6 = req.getParameter("fml6");
		System.out.println("fml6="+fml6);
		
		//更改校内实习公式
		if(fml2!=""){
			Formula fml = new Formula();
			fml.setFml(fml2);
			fml.setId(1011);
			fml.setType("校内实习");
			formulaService.updateFml2(fml);
		}
		//更改校外实习公式
		if(fml3!=""){
			Formula fml = new Formula();
			fml.setFml(fml3);
			fml.setId(1012);
			fml.setType("校外实习");
			formulaService.updateFml3(fml);
		}
		//更改课程设计公式
		if(fml4!=""){
			Formula fml = new Formula();
			fml.setFml(fml4);
			fml.setId(1013);
			fml.setType("课程设计");
			formulaService.updateFml4(fml);
		}
		//更改毕业设计公式
		if(fml5!=""){
			Formula fml = new Formula();
			fml.setFml(fml5);
			fml.setId(1014);
			fml.setType("毕业设计");
			formulaService.updateFml5(fml);
		}
		//更改实验公式
		if(fml6!=""){
			Formula fml = new Formula();
			fml.setFml(fml6);
			fml.setId(1015);
			fml.setType("实验");
			formulaService.updateFml6(fml);
		}
		//更改课堂教学公式1001
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1001);
			fml.setType("课堂");
			fml.setFlag(-flag);
			fml.setRatio(ratio1);
			fml.setLmt(lmt);
			fml.setEratio(eratio1);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio1==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio1+"");
			}
			if(ratio1.equals("1") || ratio1.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", ratio1);
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println(fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1002
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1002);
			fml.setEratio(eratio2);
			fml.setSth("nt");
			fml.setType("课堂");
			fml.setFlag(-flag);
			fml.setRatio(ratio1);
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio2==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio2+"");
			}
			if(ratio1.equals("1") || ratio1.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", ratio1);
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println("1002="+fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1003
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1003);
			fml.setEratio(eratio3);
			fml.setSth("nc");
			fml.setType("课堂");
			fml.setFlag(-flag);
			fml.setRatio(ratio1);
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio3==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio3+"");
			}
			if(ratio1.equals("1") || ratio1.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", ratio1);
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println("1003="+fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1004
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1004);
			fml.setEratio(eratio4);
			fml.setSth("redo");
			fml.setType("课堂");
			fml.setFlag(-flag);
			fml.setRatio(ratio1);
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio4==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio4+"");
			}
			if(ratio1.equals("1") || ratio1.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", ratio1);
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println("1004="+fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1005
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1005);
			fml.setEratio(eratio5);
			fml.setSth("double");
			fml.setType("课堂");
			fml.setFlag(-flag);
			fml.setRatio(ratio1);
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio5==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio5+"");
			}
			if(ratio1.equals("1") || ratio1.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", ratio1);
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println("1005="+fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1006
		fml1 = req.getParameter("fml1");
		System.out.println("1006="+fml1);
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1006);
			fml.setEratio(eratio1);
			fml.setFlag(flag);
			fml.setRatio(ratio2);
			
			fml.setType("课堂");
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			System.out.println("1006=="+fml1);
			if(eratio1==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", "("+eratio1+")");
			}
			System.out.println("1006==="+fml1);
			if(ratio2.equals("1") || ratio2.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", "("+ratio2+")");
			}
			System.out.println("1006===="+fml1);
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println("1006="+fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		
		//更改课堂教学公式1007
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1007);
			fml.setEratio(eratio2);
			fml.setSth("nt");
			fml.setFlag(flag);
			fml.setRatio(ratio2);
			fml.setType("课堂");
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio2==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio2+"");
			}
			if(ratio2.equals("1") || ratio2.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", "("+ratio2+")");
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println(fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1008
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1008);
			fml.setEratio(eratio3);
			fml.setSth("nc");
			fml.setFlag(flag);
			fml.setRatio(ratio2);
			fml.setType("课堂");
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio3==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio3+"");
			}
			if(ratio2.equals("1") || ratio2.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", "("+ratio2+")");
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println(fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1009
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1009);
			fml.setEratio(eratio4);
			fml.setSth("redo");
			fml.setFlag(flag);
			fml.setRatio(ratio2);
			fml.setType("课堂");
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio4==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio4+"");
			}
			if(ratio2.equals("1") || ratio2.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", "("+ratio2+")");
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println(fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		//更改课堂教学公式1010
		fml1 = req.getParameter("fml1");
		if(fml1!=""){
			Formula fml = new Formula();
			
			fml.setId(1010);
			fml.setEratio(eratio5);
			fml.setSth("double");
			fml.setFlag(flag);
			fml.setRatio(ratio2);
			fml.setType("课堂");
			fml.setLmt(lmt);
			
			fml1 = fml1.replaceAll("fml=", "");
			if(eratio5==1){
				fml1 = fml1.replaceAll("eratio", "");
			}else{
				fml1 = fml1.replaceAll("eratio", eratio5+"");
			}
			if(ratio2.equals("1") || ratio2.equals("1.0")){
				fml1 = fml1.replaceAll("ratio", "");
			}else{
				fml1 = fml1.replaceAll("ratio", "("+ratio2+")");
			}
			//排除首尾的*
			if(fml1.startsWith("*")){
				fml1 = fml1.substring(1);
			}
			if(fml1.endsWith("*")){
				fml1 = fml1.substring(0, fml1.length()-1);
			}
			System.out.println(fml1);
			fml.setFml(fml1);
			
			formulaService.updateFml1(fml);
		}
		
		
		
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public FormulaService getFormulaService() {
		return formulaService;
	}


	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}


	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession(true);
	}

}
