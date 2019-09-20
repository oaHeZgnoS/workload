<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加用户</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/super.css" >
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function($){
		$("#btn-back").click(function(){
			window.history.back();
		});
		$("#btn-submit").click(function(){
			var id = $("#i1").val();
			var name = $("#i2").val();
			var psw = $("#i3").val();
			var psw2 = $("#i4").val();
			var age = $("#i5").val();
			if( psw!=psw2 ){
				alert("两次输入的密码不一致！");
				return false;
			}else if( id!=""&& name!==""&& psw!==""&& psw2!==""&& age!=""){
				alert("添加用户成功！");
				return true;
			}else{
				alert(" * 行不能为空！");
				return false;
			}
			
		});
	});
</script>
</head>
<body>
	
	
	<div class="add-table">
		<div class="table-title">
			<h2>&nbsp;&nbsp;添加新用户>></h2>
		</div>
		
		<form action="user/user-add.action" method="post" class="add-form">
			<ul>
				<!-- <li><span>用户编号：</span><input type="text" name="user.id" id="i1"/><span>&nbsp;*</span></li> -->
				<li><span>用户姓名：</span><input type="text" name="user.username" id="i2" /><span>&nbsp;*</span></li>
				<li><span>用户密码：</span><input type="text" name="user.password" id="i3" /><span>&nbsp;*</span></li>
				<li><span>确认密码：</span><input type="text"  id="i4"/><span>&nbsp;*</span></li>
				<li><span>用户性别：</span>
					<select name="user.sex">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</li>
				<li><span>用户年龄：</span><input type="text" name="user.age" id="i5"/><span>&nbsp;*</span></li>
				<li><span>用户住址：</span><textarea rows="3" cols="20" name="user.addr"></textarea></li>
				<li><span>所在系：</span><input type="text" name="user.department"/></li>
				
				<li><span>用户权限：</span>
					<select name="user.authority">
						<option value="1">管理员</option>
						<option value="0">普通教职工</option>
					</select>
				</li>
			</ul>
			<div class="form-btn">
				<input type="submit" value="提交" class="btn-form" id="btn-submit"/>
				<input type="button" value="返回" class="btn-form" id="btn-back"/>
			</div>
		</form>
	</div>	
		
		

	
</body>
</html>