<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新公式页面</title>
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
		
	});
</script>
</head>
<body>

		<div class="table-title">
			<h2>&nbsp;&nbsp;更新公式>></h2>
			<span style="color: #3379B7">&nbsp;&nbsp;&nbsp;&nbsp;参数约定：sh-学时;flag-人数限制;ratio-合班系数;
			limit-合班系数上限;sth-补充内容;nt-开新课;nc-新开课;redo-单设重修班;double-双语班;</span><br>
			<span style="color: #3379B7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;eratio-特殊系数;amount-选课人数;days-天数;weeks-周数;each-每组人数。</span>
		</div>
		<span>&nbsp;&nbsp;更新工作量计算公式</span>
		<form action="formula/updateFml.action" method="post" class="add-form">
			<ul id="myul">
				<li><span>课堂教学公式：</span><input type="text" name="fml1"/>*eg:fml=ratio*sh*eratio</li>
				<li><span>选课人数阈值：</span><input type="text" name="flag"/></li>
				<li><span>合班系数(人数小于阈值)：</span><input type="text" name="ratio1"/></li>
				<li><span>合班系数(人数不小于阈值)：</span><input type="text" name="ratio2"/></li>
				<li><span>合班系数上限：</span><input type="text" name="lmt"/></li>
				
				<li><span>特殊系数(默认)：</span><input type="text" name="eratio1"/></li>
				<li><span>特殊系数(开新课)：</span><input type="text" name="eratio2"/></li>
				<li><span>特殊系数(新开课)：</span><input type="text" name="eratio3"/></li>
				<li><span>特殊系数(单设重修班)：</span><input type="text" name="eratio4"/></li>
				<li><span>特殊系数(双语班)：</span><input type="text" name="eratio5"/></li>
				
				<li><span>校内实习公式：</span><input type="text" name="fml2"/>
				<li><span>校外实习公式：</span><input type="text" name="fml3"/>
				<li><span>课程设计公式：</span><input type="text" name="fml4"/>
				<li><span>毕业设计公式：</span><input type="text" name="fml5"/>
				<li><span>实验公式：</span><input type="text" name="fml6"/>
				
				
			</ul>
			
			<div class="form-btn">
         		<input type="submit" value="更新" class="btn-form" id="btn-submit"></input>
         		<input type="button" value="返回" class="btn-form" id="btn-back"></input>
         	</div>
		</form>
	

</body>
</html>