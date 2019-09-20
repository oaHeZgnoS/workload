<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>浏览选择本地excel文件</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/systemAdmin.css" >
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function($){
		$("#upload").click(function(){
			var file = $("#file").val();
			
			alert(file);
			if(file.endsWith("xls") || file.endsWith("xlsx")){
				alert("导入数据库成功！");
				return true;
			}else{
				alert("选择文件非excel格式！");
				return false;
			}
			
		}); 
		
	});
</script>
</head>
<body>
	<form id="excel" action="user/intoDB.action" method="post" enctype="multipart/form-data">
		<input type="file" name="excel" id="file"/>
		<input type="submit" value="导入数据库" id="upload"/>
	</form> 
</body>
</html>