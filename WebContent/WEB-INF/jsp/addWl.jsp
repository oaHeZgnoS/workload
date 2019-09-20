<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人工作量信息</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/super.css" >
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function($){
		
		$("#btn-back").click(function(){
			window.history.back();
			return false;
		});
		
		$("#l1").hide();
		$("#l2").hide();
		$("#l3").hide();
		
	});
</script>
</head>
<body>

		<div class="table-title">
			<h2>&nbsp;&nbsp;填写工作量>></h2>
		</div>
	
		<form action="cworkload/addCWorkload.action" method="post" class="add-form">
			<ul>
				<li id="l1"><span>教师工号：</span><input type="text" name="t_id" disabled="disabled" value="${dbUser.id}"/></li>
				<li id="l2"><span>教师姓名：</span><input type="text" name="t_name" disabled="disabled" value="${dbUser.username}"/></li>
				<li id="l3"><span>所在系：</span><input type="text" name="department" disabled="disabled" value="${dbUser.department}"/></li>
				<li><span>工作量名：</span><input type="text" name="name"/></li>
				<li><span>时间：</span><input type="text" name="time"/></li>
				<li><span>所带班级：</span><input type="text" name="classes"/></li>
				<li><span>学生人数：</span><input type="text" name="amount"/></li>
				<!-- <li><span>工作量：</span><input type="text" name="wl"/></li> -->
				<li><span>学期：</span>
					<select name="term">
						<option value="15-16.2">15-16.2</option>
						<option value="16-17.1">16-17.1</option>
						<option value="16-17.2">16-17.2</option>
					</select>
				</li>
				<li><span>工作量类型：</span>
					<select name="type">
						<option value="毕业设计">毕业设计</option>
						<option value="校内实习">校内实习</option>
						<option value="校外实习">校外实习</option>
						<option value="课堂">课堂</option>
						<option value="课程设计">课程设计</option>
						<option value="实验">实验</option>
					</select>
				</li>
				<li><span>所在校区：</span>
					<select name="remark">
						<option value="雁塔">雁塔</option>
						<option value="幸福">幸福</option>
						<option value="草堂">草堂</option>
					</select>
				</li>
				<li><span>原因：</span><input type="text" name="reason"/></li>
			</ul>
			
			<div class="form-btn">
         		<input type="submit" value="添加" class="btn-form" id="btn-submit"></input>
         		<input type="button" value="返回" class="btn-form" id="btn-back"></input>
         	</div>
		</form>
	

</body>
</html>