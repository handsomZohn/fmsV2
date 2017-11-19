<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<!--SiteMesh Setup -->
<meta name="decorator" content="widget" />
<title>文件类型分布饼图</title>
</head>

<body>
<script>
$(function () {
	showFiletypeChart();
	var filetypechart = $('#filetypecontainer').highcharts();
	filetypechart.showLoading("正在加载图表数据，请稍等......");
	if("${month}"){
	    $.getJSON("${ctx}/json/summary/org/monthly/filetype/${year}/${month}?orgid=${orgid}",function(remotedata){
	   	 	filetypechart.addSeries( {
	            name: '文件类型百分比',
	            data: remotedata
	        });
	    		filetypechart.hideLoading();
	    });
	}else if("${quarter}"){	
	    $.getJSON("${ctx}/json/summary/org/quarter/filetype/${year}/${quarter}?orgid=${orgid}",function(remotedata){
	   	 	filetypechart.addSeries( {
	            name: '文件类型百分比',
	            data: remotedata
	        });
	    		filetypechart.hideLoading();
	    });
	}else{
	    $.getJSON("${ctx}/json/summary/org/annual/filetype/${year}?orgid=${orgid}",function(remotedata){
	   	 	filetypechart.addSeries( {
	            name: '文件类型百分比',
	            data: remotedata
	        });
	    		filetypechart.hideLoading();
	    });
		
	}

    function showFiletypeChart(sumdata){
	    $('#filetypecontainer').highcharts({
	    		 loading: {
	             hideDuration: 1000,
	             showDuration: 1000
	         },
	        chart: {
	            type: 'pie',
	            options3d: {
	                enabled: true,
	                alpha: 45,
	                beta: 0
	            }
	        },
	        title: {
	            text: '文件类型数量分布百分比'
	        },
	        tooltip: {
	        		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                depth: 35,
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}:{point.percentage:.1f}%'
	                }
	            }
	        },
	        credits:{
	            enabled:false // 禁用版权信息
	      	 }
	    });
    }
    
});
</script>
<div id="filetypecontainer" style="height: 400px"></div>
		
</body>
</html>
