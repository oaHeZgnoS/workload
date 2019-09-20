<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<%String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; %>
<base href="<%=path%> " >
<link type="text/css" rel="stylesheet" href="css/super.css" >
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function($){
		$("a.add-link").click(function(){
			var qx = ${sessionScope.dbUser.authority};
			if( qx==0 ){
				alert("权限不够，不能添加!");
				return false;
			}else{
				return true;
			}
			
		});
	});

</script>
</head>
<body>
	<form action="user/display-list1.action?name=${name}" class="query-form">
		<span>用户姓名：</span>
		<input type="text" name="name"/>
		
		<input type="submit" value="查询"/>
	</form>
	
	<div id="supplier-table">
		<div class="table-title">
			<h2>&nbsp;用户管理>></h2>
			<a href="user/to-user-add.action" class="add-link">添加用户</a>
		</div>
		
		<table class="table-content" border=1px cellspacing=0px >
			<tr>
				<th>用户编号</th>
				<th>员工姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>地址</th>
				<th>所在系</th>
				<th>权限</th>
			</tr>
			
			<c:choose>
				<c:when test="${sessionScope.dbUser.authority==1}">
					<c:forEach items="${userList}" var="user">
					<tr>
					<td><a href="user/userDetails.action?user.id=${user.id}
				&user.password=${user.password}&user.username=${user.username}
				&user.sex=${user.sex}&user.age=${user.age}&user.addr=${user.addr}
				&user.authority=${user.authority}&user.department=${user.department}">${user.id}</a></td>
					<td>${user.username}</td>
					<td>${user.sex}</td>
					<td>${user.age}</td>
					<td>${user.addr}</td>
					<td>${user.department}</td>
					<td><c:choose><c:when test="${user.authority==1}">管理员</c:when><c:otherwise>普通教职工</c:otherwise></c:choose>
					</td>
					</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td><a href="user/userDetails.action?user.id=${user.id}">${sessionScope.dbUser.id}</a></td>
						<td>${sessionScope.dbUser.username}</td>
						<td>${sessionScope.dbUser.sex}</td>
						<td>${sessionScope.dbUser.age}</td>
						<td>${sessionScope.dbUser.addr}</td>
						<td>${sessionScope.dbUser.department}</td>
						<td><c:choose><c:when test="${user.authority==1}">管理员</c:when><c:otherwise>普通教职工</c:otherwise></c:choose></td>
					</tr>
				</c:otherwise>
			</c:choose>
			
			
			
			
		</table>
	</div>
		
	&nbsp;第
	<c:if test="${sessionScope.dbUser.authority==1}">
		
		<c:forEach begin="1" end="${pageCount}" step="1" varStatus="st">
			<a href="user/display-list.action?page=${st.index}" <c:if test="${currentPage==st.index}">class="currentPage"</c:if>>${st.index}</a>
		</c:forEach>
	</c:if>
	<c:if test="${sessionScope.dbUser.authority==0}">
		<a href="user/display-list.action" class="currentPage">1</a>
	</c:if>
	
	页
	

	
</body>
</html>