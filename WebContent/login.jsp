<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教职工工作量统计系统-登录</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%> ">
<link type="text/css" rel="stylesheet" href="css/workload.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#login").submit(function() {

			var uname = $("input:text").val();
			var psw = $("input:password").val();

			if (uname == "" || psw == "") {
				alert("用户名或密码不能为空！");
				return false;
			} else {
				return true;
			}

		});
		
		$("#register").click(function(){
			$("#reg").show();
		});
		
		$("button").click(function(){
			$("#reg").hide();
		});
		$("input.reg").click(function(){
			alert("注册成功！");
		});

	});
</script>
</head>
<body class="greatest">

	<div id="left"></div>
	<div id="right">
		<form action="user/login.action" method="post" id="login">
			用户名：<input type="text" class="c1" name="user.username" /><br>
			密&nbsp;&nbsp;&nbsp;码：<input type="password" class="c1"
				name="user.password" /><br> <input type="submit" value="登录系统"
				class="c2" /> <input type="reset" value="重&nbsp;&nbsp;&nbsp;填"
				class="c2" /><br>

			<c:if test="${sessionScope.flag==100}">
				<span>用户名或密码错误</span>
			</c:if>
		</form>
		<a id="register">没有账号?点击注册!</a>

	</div>
	
	<div id="reg">
		<span>欢迎注册>></span><button>X</button><br>
		<div id="line"></div>
		<form action="user/reg.action" method="post" >
			<span class="c0">用户名：</span><input type="text" name="user.username"/><br>
			<span class="c0">密码：</span><input type="password" name="user.password"/><br>
			<span class="c0">性别：</span><select name="user.sex"><option value="男">男</option><option value="女">女</option></select><br>
			<span class="c0">年龄：</span><input type="text" name="user.age"/><br>
			<span class="c0">联系地址：</span><input type="text" name="user.addr"/><br>
			<span class="c0">所在系：</span><input type="text" name="user.department"/><br>
			<span class="qx" name="user.authority">权限：普通教职工（默认）</span><br>
			<input type="submit" value="注册" class="reg" />
		</form>
	</div>

</body>
</html>