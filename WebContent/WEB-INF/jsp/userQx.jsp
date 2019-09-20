<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<% String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%>">
<link  type="text/css" rel="stylesheet"  href="css/super.css"/>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
   $(function($){
	   
	   $("#btn-back").click(function(){
		  window.history.back(); 
	   });
	   
	   $("#btn-submit").click(function(){
		   var authority = ${user.authority};
		   if(authority == 1){
			   alert("该教职工当前权限已经是管理员，不能升级权限！");
		   	   return false;
		   }
	   });
	   
	   
   });

</script>

</head>
<body>

    <div class="add-table">
       <div class="table-title">
            <h2>设置权限>>修改权限</h2>
       </div>
       
       <form  class="add-form" action="user/upgrade.action" method="post">
         <input  type="hidden" name="user.id" value="${user.id}"/>
         <input type="hidden" id="i2" name="user.password" value="${user.password}"/>
         <input  type="hidden" name="user.username" value="${user.username}"/>
         <input  type="hidden" name="user.sex" value="${user.sex}"/>
         <input id="d1" type="hidden" name="user.age" value="${user.age}"/>
         <input  type="hidden" name="user.addr" value="${user.addr}"/>
         <input  type="hidden" name="user.authority" value="${user.authority}"/>
         <input  type="hidden" name="user.department" value="${user.department}"/>
         <ul>
         	<li><span>当前权限:</span>
          		<span><c:if test="${user.authority=='1'}">管理员</c:if>
          			<c:if test="${user.authority=='0'}">普通教职工</c:if></span>
         	</li>        
         </ul>
         <div class="form-btn">
         	<input type="submit" value="升级权限" class="btn-form" id="btn-submit"></input>
         	<input type="button" value="返回" class="btn-form" id="btn-back"></input>
         </div>
       </form>
       
    </div>

</body>
</html>