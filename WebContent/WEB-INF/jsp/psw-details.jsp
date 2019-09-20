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
	   
	   $("#i5").hide();
	   
	   $("#btn-back").click(function(){
		  window.history.back(); 
	   });
	   
	   $("#btn-submit").click(function(){
		   var oldpsw=$("#i1").val();
		   var psw=$("#i2").val();
		   var password=$("#i3").val();
		   var repeat=$("#i4").val();
		   if(oldpsw!=psw){
			   alert("旧密码输入有误！");
			   return false;
		   }
		   if(oldpsw!=""&&password!=""&&repeat!=""){
			   if(password!=repeat){
				   alert("两次密码不一致!");
				   return false;
			   }else{
			       return true;
			   }
		   }else{
			   alert("密码不能为空！");
			   return false;
		   }
	   });
	   
	   $("#i1").blur(function(){
		   var oldpsw=$("#i1").val();
		   var psw=$("#i2").val();
		   var id=$("#d1").val();
		   if(oldpsw!=psw){
			   alert("旧密码输入有误！");
			   $("#i5").show();
			   return false;   
		   }else{
			   $("#i5").hide();
		   }
		   
	   });
	   
	   
   });

</script>

</head>
<body>

    <div class="add-table">
       <div class="table-title">
        <h2>修改个人信息>>修改密码</h2>
        
       </div>
       
       <form  class="add-form" action="user/update-psw.action" method="post">
         <input  type="hidden" name="user.id" value="${user.id}"/>
         <input type="hidden" id="i2" name="" value="${user.password}"/>
         <input  type="hidden" name="user.username" value="${user.username}"/>
         <input  type="hidden" name="user.sex" value="${user.sex}"/>
         <input id="d1" type="hidden" name="user.age" value="${user.age}"/>
         <input  type="hidden" name="user.addr" value="${user.addr}"/>
         <input  type="hidden" name="user.department" value="${user.department}"/>
         <input  type="hidden" name="user.authority" value="${user.authority}"/>
         <ul>
          <li><span>旧的密码:</span><input id="i1" type="password" name=""/><span id="i5">*密码不正确</span></li>
          <li><span>新的密码:</span><input id="i3" type="password" name="user.password"/></li>
          <li><span>确认密码:</span><input id="i4" type="password" name=""/></li>
          
         </ul>
         <div class="form-button">
         	<input type="submit" value="修改密码" class="btn-form" id="btn-submit"></input>
         	<input type="button" value="返回" class="btn-form" id="btn-back"></input>
         </div>
       </form>
       
    </div>

</body>
</html>