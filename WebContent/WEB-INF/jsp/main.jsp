<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆成功</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%>" >
<link type="text/css" rel="stylesheet" href="css/main.css" >
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function($){
		$("#a2").hide();
		var name = $("#i1").val();
		var authority = $("#i2").val();
		
		$("#a3").click(function(){
			if(authority==0){
				alert("当前登录用户权限不足！");
				return false;
			}
		});
		
		$("#a1").click(function(){
			if(name=='admin'){
				alert("系统权限下无法操作教职工模块！");
				return false;
			}
		});
		
		$("#a2").click(function(){
			if(name!='admin'){
				alert("无权限！");
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div id="super">
	<div id="head">
		<div id="title"></div>
		<input id="i1" type="hidden" name="" value="${dbUser.username}"/>
		<input id="i2" type="hidden" name="" value="${dbUser.authority}"/>
		<span style="text-shadow:5px 5px 2px blue">欢迎您，${sessionScope.dbUser.username}</span>
	</div>
	<div id="body">
		<div id="left"> 
			<ul>
				<a id="a1" target="myframe" href="user/func.action"><li id="l1"></li></a>
				<a id="a2" target="myframe" href="user/systemFunc.action"><li id="l2"></li></a>
				<a id="a3" target="myframe" href="user/adminFunc.action"><li id="l3"></li></a>
				<a href="user/exit.action"><li id="l4"></li></a>
			</ul>
		</div>
		<div id="mid"></div>
		<div id="right">
			<iframe width=100% height="530px" src="notice/noticeList.action" name="myframe"></iframe>
		</div>
	</div>
	</div>
</body>
</html>