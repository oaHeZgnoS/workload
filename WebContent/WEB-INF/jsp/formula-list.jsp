<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>当前公式展示</title>
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
		<div class="table-title">
			<h2>&nbsp;公式管理>></h2>
			<a href="formula/toUpdateFml.action" class="add-link">设置新公式</a>
		</div>
		
		<table class="table-content" border=1px cellspacing=0px >
			<tr>
				<th>公式编号</th>
				<th>公式类别</th>
				<th>人数标志位</th>
				<th>合班系数</th>
				<th>系数上限</th>
				<th>补充说明</th>
				<th>特殊系数</th>
				<th>公式</th>
			</tr>
			
			<c:forEach items="${formulaList}" var="formula">
				<tr>
					<td>${formula.id}</td>
					<td>${formula.type}</td>
					<td>${formula.flag}</td>
					<td>${formula.ratio}</td>
					<td>${formula.lmt}</td>
					<td>${formula.sth}</td>
					<td>${formula.eratio}</td>
					<td style="color: #3379B7"><b>${formula.fml}</b></td>
				</tr>
			</c:forEach>
	
		</table>
	</div>
		
	&nbsp;&nbsp;第
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