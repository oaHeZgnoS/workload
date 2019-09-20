<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告分页展示</title>
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
	
	<div id="supplier-table">
		<div class="table-title" style="height:30px">
			<h4>&nbsp;通知公告>></h4>
		</div>
		
		<table class="table-content" border=1px cellspacing=0px >
			<tr>
				<th>标题</th>
				<th>公告内容</th>
				<th>发布人ID</th>
				<th>发布时间</th>
			</tr>
			
			<c:forEach items="${noticeList}" var="notice">
				<tr>
					<td>${notice.title}</td>
					<td>${notice.content}</td>
					<td>${notice.worker_id}</td>
					<td>${notice.time}</td>
				</tr>
			</c:forEach>
				
	
		</table>
	</div>
	
	第
	<c:forEach begin="1" end="${pageCount}" step="1" varStatus="st">
		<a href="notice/display-list.action?page=${st.index}" <c:if test="${currentPage==st.index}">class="currentPage"</c:if>>${st.index}</a>
	</c:forEach>
	页
	

	
</body>
</html>