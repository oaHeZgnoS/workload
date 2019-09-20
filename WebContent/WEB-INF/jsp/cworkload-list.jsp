<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>&nbsp;工作量审核</title>
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
	<%-- <form action="workload/display-list1.action?t_name=${t_name}" class="query-form">
		<span>教师姓名：</span>
		<input type="text" name="t_name"/>
		
		<input type="submit" value="查询"/>
	</form> --%>
	
	<div id="supplier-table">
		<div class="table-title">
			<h2>&nbsp;工作量审核>></h2>
		</div>
		
		
		
		<table class="table-content" border=1px cellspacing=0px >
			<tr>
				<th>操作</th>
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
					<c:forEach items="${cworkloadList}" var="cworkload">
					<tr>
					<td><c:if test="${cworkload.op==0}">添加</c:if>
					<c:if test="${cworkload.op<0}">修改</c:if>
					<c:if test="${cworkload.op>0}">删除</c:if></td>
					<td><a href="cworkload/cworkloadDetails.action?cworkload.id=${cworkload.id}
				&cworkload.department=${cworkload.department}&cworkload.t_id=${cworkload.t_id}
				&cworkload.t_name=${cworkload.t_name}&cworkload.name=${cworkload.name}&cworkload.time=${cworkload.time}
				&cworkload.classes=${cworkload.classes}&cworkload.amount=${cworkload.amount}
				&cworkload.wl=${cworkload.wl}&cworkload.type=${cworkload.type}&cworkload.remark=${cworkload.remark}
				&cworkload.term=${cworkload.term}&cworkload.op=${cworkload.op}&cworkload.reason=${cworkload.reason}"">${cworkload.id}</a></td>
					<td>${cworkload.department}</td>
					<td>${cworkload.t_id}</td>
					<td>${cworkload.t_name}</td>
					<td>${cworkload.name}</td>
					<td>${cworkload.time}</td>
					<td>${cworkload.classes}</td>
					<td>${cworkload.amount}</td>
					<td>${cworkload.wl}</td>
					<td>${cworkload.type}</td>
					<td>${cworkload.remark}</td>
					<td>${cworkload.term}</td>
					</tr>
					</c:forEach>
				</c:when>
				
			</c:choose>

		</table>
		
		<h1 id="tips" hidden>&nbsp;暂无需要审核的数据！</h1>
	</div>
		
	&nbsp;第
	<c:if test="${sessionScope.dbUser.authority==1}">
		<c:forEach begin="1" end="${pageCount}" step="1" varStatus="st">
			<a href="user/toCheckWl.action?page=${st.index}" <c:if test="${currentPage==st.index}">class="currentPage"</c:if>>${st.index}</a>
		</c:forEach>
	</c:if>
	页
	

	
</body>
</html>