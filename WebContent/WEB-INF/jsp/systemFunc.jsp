<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统管理员模块</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/systemAdmin.css" >
</head>
<body>
	<div>
		<a href="user/toWlManage.action"><span id="manageWl"></span></a>
	</div>
	
	<div>
		<a href="user/toClassifyCount.action"><span id="classifyCount"></span></a>
	</div>
	
	<div>
		<a href="user/toIntoDB.action"><span id="intoDB"></span></a>
	</div>
	
	<div>
		<a href="user/toSetFormula.action"><span id="setFormula"></span></a>
	</div>
</body>
</html>