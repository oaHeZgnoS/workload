<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>一条工作量详情</title>
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
		$("#btn-alter").click(function(){
			var text = $(this).text();
			
			if(text=="修改"){
					
				$(this).text("提交");
				$("form ul li input,form ul li textarea,form ul li select").removeAttr("disabled");
				
				return false;
				
				
			}else if(text=="提交"){
				
				var name = $("#i2").val();
				var age = $("#i3").val();
				
				if(  name!==""&& age!==""){
					return true;
				}else{
					alert("工作量信息不能为空！");
					return false;
				}
			}
		});
		
		$("#btn-delete").click(function(){

			if(confirm("确定要删除吗？")){
				$("form").attr("action","cworkload/delete-cworkload.action");
				return true;
				
			}else{
				return false;
			}
		});
		
		
	});
</script>
</head>
<body>

		<div class="table-title">
			<h2>&nbsp;&nbsp;修改工作量>></h2>
		</div>
	
		<form action="cworkload/update-cworkload.action" method="post" class="add-form">
		
			<%-- <input type="hidden" name="user.id" value="${user.id}" />
			<input type="hidden" name="user.password" value="${user.password}"/>
			<input type="hidden" name="user.authority" value="${user.authority}"/> --%>
			<input type="hidden" name="id" value="${workload.id}" />
			<ul>
				
				<li><span>所在系：</span><input disabled="disabled" type="text" name="department" id="i2" value="${workload.department}" /><span>&nbsp;*</span></li>
				<li><span>教师工号：</span><input disabled="disabled" type="text" name="t_id" value="${workload.t_id}" /></li>
				<li><span>教师姓名：</span><input disabled="disabled" type="text" name="t_name" value="${workload.t_name}" /></li>
				<li><span>工作量名称：</span><input disabled="disabled" type="text" name="name" value="${workload.name}" /></li>
				<li><span>时间：</span><input disabled="disabled" type="text" name="time" value="${workload.time}" /></li>
				<li><span>班级：</span><input disabled="disabled" type="text" name="classes" value="${workload.classes}" /></li>
				<li><span>人数：</span><input disabled="disabled" type="text" name="amount" value="${workload.amount}" /></li>
				<li><span>工作量：</span><input disabled="disabled" type="text" name="wl" value="${workload.wl}" /></li>
				<li><span>工作量类型：</span>
					<select name="type" disabled="disabled" value="${workload.type}">
						
						<option value="毕业设计" <c:if test="${workload.type=='毕业设计'}">selected="selected"</c:if>>毕业设计</option>
						<option value="校内实习" <c:if test="${workload.type=='校内实习'}">selected="selected"</c:if>>校内实习</option>
						<option value="校外实习" <c:if test="${workload.type=='校外实习'}">selected="selected"</c:if>>校外实习</option>
						<option value="课堂" <c:if test="${workload.type=='课堂'}">selected="selected"</c:if>>课堂</option>
						<option value="课程设计" <c:if test="${workload.type=='课程设计'}">selected="selected"</c:if>>课程设计</option>
						<option value="实验" <c:if test="${workload.type=='实验'}">selected="selected"</c:if>>实验</option>
					</select>
				</li>
				<li><span>校区：</span>
					<select name="remark" disabled="disabled" value="${workload.remark}">
						
						<option value="雁塔" <c:if test="${workload.remark=='雁塔'}">selected="selected"</c:if>>雁塔</option>
						<option value="草堂" <c:if test="${workload.remark=='草堂'}">selected="selected"</c:if>>草堂</option>
						<option value="幸福" <c:if test="${workload.remark=='幸福'}">selected="selected"</c:if>>幸福</option>
					</select>
				</li>
				<li><span>学期：</span><input disabled="disabled" type="text" name="term" value="${workload.term}" /></li>
				<li><span>原因：</span><input type="text" name="reason" /></li>
				
			</ul>
			<div class="form-btn">
				<button class="btn-form" id="btn-back">返回</button>
				<button class="btn-form" id="btn-alter">修改</button>
				<button class="btn-form" id="btn-delete">删除</button>
			</div>
		</form>
	
		
		

	
</body>
</html>