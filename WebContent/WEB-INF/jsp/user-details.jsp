<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
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
					alert("用户姓名、年龄不能为空！");
					return false;
				}
				
			}
		});
		
		$("#btn-delete").click(function(){
			
			var qx =  ${sessionScope.dbUser.authority};
			if(confirm("确定要删除吗？")){
				if(qx==1){
					
					$("form").attr("action","user/delete-user.action");
				}else{
					alert("权限不够，不能删除！");
					return false;
				}
				
			}else{
				return false;
			}
		});
		
		
	});
</script>
</head>
<body>

		<div class="table-title">
			<h2>&nbsp;&nbsp;修改个人信息>></h2>
		</div>
	
		<form action="user/update-user.action" method="post" class="add-form">
		
			<input type="hidden" name="user.id" value="${user.id}" />
			<input type="hidden" name="user.password" value="${user.password}"/>
			<input type="hidden" name="user.authority" value="${user.authority}"/>
			<ul>
				
				<li><span>用户名称：</span><input disabled="disabled" type="text" name="user.username" id="i2" value="${user.username}" /><span>&nbsp;*</span></li>
				<li><span>用户性别：</span>
					
					<select name="user.sex" disabled="disabled" value="${user.sex}">
						
						<option value="男" <c:if test="${user.sex=='男'}">selected="selected"</c:if>>男</option>
						<option value="女" <c:if test="${user.sex=='女'}">selected="selected"</c:if>>女</option>
					</select>
				</li>
				
				<li><span>用户年龄：</span><input disabled="disabled" type="text" name="user.age" id="i3" value="${user.age}"/><span>&nbsp;*</span></li>
				<li><span>用户住址：</span><textarea disabled="disabled" rows="3" cols="20" name="user.addr">${user.addr}</textarea></li>
				<li><span>所在系：</span><input disabled="disabled" type="text" name="user.department" value="${user.department}"/></li>
				
				<li><span>用户权限：</span>
					<span><c:if test="${user.authority=='1'}">管理员</c:if><c:if test="${user.authority=='0'}">普通教职工</c:if></span>
				</li>
			</ul>
			<div class="form-btn">
				<button class="btn-form" id="btn-back">返回</button>
				<button class="btn-form" id="btn-alter">修改</button>
				<!-- <button class="btn-form" id="btn-delete">删除</button> -->
				<!-- <button class="btn-form" id="btn-update">修改密码</button> -->
				<a href="user/to-update-psw.action?user.id=${user.id}
				&user.password=${user.password}&user.username=${user.username}
				&user.sex=${user.sex}&user.age=${user.age}&user.addr=${user.addr}
				&user.authority=${user.authority}&user.department=${user.department}" class="updatepsw">修改密码</a>
			</div>
		</form>
	
		
		

	
</body>
</html>