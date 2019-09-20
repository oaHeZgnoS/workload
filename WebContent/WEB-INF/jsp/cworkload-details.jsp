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
			<h2>&nbsp;&nbsp;审核此条工作量>></h2>
		</div>
	
		<form action="user/check-cworkload.action" method="post" class="add-form">
		
			<%-- <input type="hidden" name="user.id" value="${user.id}" />
			<input type="hidden" name="user.password" value="${user.password}"/>
			<input type="hidden" name="user.authority" value="${user.authority}"/> --%>
			<input type="hidden" name="id" value="${cworkload.id}" />
			<input type="hidden" name="op" value="${cworkload.op}" />
			<ul>
				
				<li><span>所在系：</span><input type="text" name="department" id="i2" value="${cworkload.department}" /><span>&nbsp;*</span></li>
				<li><span>教师工号：</span><input type="text" name="t_id" value="${cworkload.t_id}" /></li>
				<li><span>教师姓名：</span><input type="text" name="t_name" value="${cworkload.t_name}" /></li>
				<li><span>工作量名称：</span><input type="text" name="name" value="${cworkload.name}" /></li>
				<li><span>时间：</span><input type="text" name="time" value="${cworkload.time}" /></li>
				<li><span>班级：</span><input type="text" name="classes" value="${cworkload.classes}" /></li>
				<li><span>人数：</span><input type="text" name="amount" value="${cworkload.amount}" /></li>
				<li><span>工作量：</span><input type="text" name="wl" value="${cworkload.wl}" /></li>
				<li><span>工作量类型：</span>
					<select name="type" value="${cworkload.type}">
						
						<option value="毕设" <c:if test="${workload.type=='毕设'}">selected="selected"</c:if>>毕设</option>
						<option value="实习" <c:if test="${workload.type=='实习'}">selected="selected"</c:if>>实习</option>
						<option value="理论" <c:if test="${workload.type=='理论'}">selected="selected"</c:if>>理论</option>
						<option value="课设" <c:if test="${workload.type=='课设'}">selected="selected"</c:if>>课设</option>
						<option value="其他" <c:if test="${workload.type=='其他'}">selected="selected"</c:if>>其他</option>
					</select>
				</li>
				<li><span>校区：</span>
					<select name="remark" value="${cworkload.remark}">
						
						<option value="雁塔" <c:if test="${cworkload.remark=='雁塔'}">selected="selected"</c:if>>雁塔</option>
						<option value="草堂" <c:if test="${cworkload.remark=='草堂'}">selected="selected"</c:if>>草堂</option>
						<option value="幸福" <c:if test="${cworkload.remark=='幸福'}">selected="selected"</c:if>>幸福</option>
					</select>
				</li>
				<li><span>学期：</span><input type="text" name="term" value="${cworkload.term}" /></li>
				<li><span>原因：</span><input type="text" name="reason" value="${cworkload.reason}" /></li>
			</ul>
			<div class="form-btn">
				<button class="btn-form" id="btn-back">返回</button>
				<button class="btn-form" id="btn-ok">确认</button>
			</div>
		</form>
	
		
		

	
</body>
</html>