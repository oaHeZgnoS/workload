<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增公告</title>
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
		$("#btn-submit").click(function(){
			alert("发布成功！");
		});

	});
</script>
</head>
<body>

		<div class="table-title">
			<h2>&nbsp;&nbsp;新增公告>></h2>
		</div>
	
		<form action="notice/addNotice.action" method="post" class="add-form">
			<ul>
				<li><span>公告标题：</span><input type="text" name="title" id="i2"/></li>
				
				<li><span>发布内容：</span><textarea rows="3" cols="20" name="content"></textarea></li>
			</ul>
			
			<div class="form-btn">
         		<input type="submit" value="发布" class="btn-form" id="btn-submit"></input>
         		<input type="button" value="返回" class="btn-form" id="btn-back"></input>
         	</div>
		</form>

</body>
</html>