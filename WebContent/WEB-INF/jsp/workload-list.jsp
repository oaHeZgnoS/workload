<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>&nbsp;工作量管理</title>
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
	<form action="workload/display-list1.action?t_name=${t_name}" class="query-form">
		<span>教师姓名：</span>
		<input type="text" name="t_name"/>
		
		<input type="submit" value="查询"/>
	</form>
	
	<div id="supplier-table">
		<div class="table-title">
			<h2>工作量管理>></h2>
			<a href="workload/to-workload-add.action" class="add-link">添加工作量</a>
		</div>
		
		<table class="table-content" border=1px cellspacing=0px >
			<tr>
				<th>工作量编号</th>
				<th>所在系</th>
				<th>教师工号</th>
				<th>教师姓名</th>
				<th>工作量名</th>
				<th>时间</th>
				<th>班级</th>
				<th>人数</th>
				<th>工作量</th>
				<th>工作量类型</th>
				<th>校区</th>
				<th>学期</th>
			</tr>
			
			<c:choose>
				<c:when test="${sessionScope.dbUser.authority==1}">
					<c:forEach items="${workloadList}" var="workload">
					<tr>
					<td><a href="workload/workloadDetails.action?workload.id=${workload.id}
				&workload.department=${workload.department}&workload.t_id=${workload.t_id}
				&workload.t_name=${workload.t_name}&workload.name=${workload.name}&workload.time=${workload.time}
				&workload.classes=${workload.classes}&workload.amount=${workload.amount}
				&workload.wl=${workload.wl}&workload.type=${workload.type}&workload.remark=${workload.remark}
				&workload.term=${workload.term}">${workload.id}</a></td>
					<td>${workload.department}</td>
					<td>${workload.t_id}</td>
					<td>${workload.t_name}</td>
					<td>${workload.name}</td>
					<td>${workload.time}</td>
					<td>${workload.classes}</td>
					<td>${workload.amount}</td>
					<td>${workload.wl}</td>
					<td>${workload.type}</td>
					<td>${workload.remark}</td>
					<td>${workload.term}</td>
					</tr>
					</c:forEach>
				</c:when>
				
			</c:choose>

		</table>
	</div>
		
	&nbsp;第
	<c:if test="${sessionScope.dbUser.authority==1}">
		<c:forEach begin="1" end="${pageCount}" step="1" varStatus="st">
			<a href="workload/display-list.action?page=${st.index}" <c:if test="${currentPage==st.index}">class="currentPage"</c:if>>${st.index}</a>
		</c:forEach>
	</c:if>
	页
	

	
</body>
</html>