<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>&nbsp;ECharts图表统计</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%> ">
<link type="text/css" rel="stylesheet" href="css/super.css">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/echarts-all.js"></script>

</head>
<body>
	<div id="supplier-table">
		<div class="table-title">
			<h2>&nbsp;ECharts统计>></h2>
		</div>

		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div id="main" style="height: 400px;"></div>


		<script type="text/javascript">
			$(function($) {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main'));

				// 初始 option
				option = {
					title : {
						text : '教职工工作量统计图表'
					},
					tooltip : {
						show : true
					},
					legend : {
						data : [ "课堂", "实践" ]
					},

					xAxis : {
						type : "category",
						data : []
					},
					yAxis : [ {
						type : "value",

					} ],

					series : [ {
							name : "课堂",
							type : "bar",
							data : []
							}, 
							{
							name : "实践",
							type : "bar",
							data : []
							},

					]
				};

				myChart.showLoading();
				//通过Ajax获取数据
				$.ajax({
					type : "post",
					async : true, //异步执行
					url : "workload/testEcharts.action",
					dataType : "json", //返回数据形式为json
					success : function(json) {
						
						
						if (json) {
							
							//fetchData(function(data) {
								
								myChart.hideLoading();
								myChart.setOption({
									tooltip : {
										show : true
									},
									legend : {
										data : [ "课堂", "实践" ]
									},
									title : {
										text : '教职工工作量统计图表'
									},
									
									xAxis : {
										type : "category",
										data : json.xAxisData
									},
									yAxis : [ {
										type : "value",

									} ],

									series : json.seriesList
									
								});
								

							//});
							
						}
					},
					error : function(errorMsg) {
						alert("请求数据失败");
					}
				});
				myChart.setOption(option);
			});
		</script>


	</div>


</body>
</html>