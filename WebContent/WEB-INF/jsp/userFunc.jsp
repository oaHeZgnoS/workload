<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教职工功能模块</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/user.css" >
</head>
<body>
	<div>
		<a href="user/toUserModify.action"><span id="modify"></span></a>
	</div>
	
	<div>
		<a href="user/toWlFill.action"><span id="fill"></span></a>
	</div>
	
	<div>
		<a href="user/toWlSure.action"><span id="sure"></span></a>
	</div>
</body>
</html>