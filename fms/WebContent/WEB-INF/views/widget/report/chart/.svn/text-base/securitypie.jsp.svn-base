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
<title>密级分布饼图</title>
</head>

<body>
<script>
$(function () {
	showSeurityChart();
	var chart = $('#securitycontainer').highcharts();
	chart.showLoading("正在加载图表数据，请稍等......");
	if("${month}"){
	    $.getJSON("${ctx}/json/summary/org/monthly/security/${year}/${month}?orgid=${orgid}",function(remotedata){
	        chart.addSeries( {
	            name: '密级百分比',
	            data: remotedata
	        });
		    	chart.hideLoading();
	    });
	}else if("${quarter}"){
	    $.getJSON("${ctx}/json/summary/org/quarter/security/${year}/${quarter}?orgid=${orgid}",function(remotedata){
	        chart.addSeries( {
	            name: '密级百分比',
	            data: remotedata
	        });
		    	chart.hideLoading();
	    });
	}else{
	    $.getJSON("${ctx}/json/summary/org/annual/security/${year}/?orgid=${orgid}",function(remotedata){
	        chart.addSeries( {
	            name: '密级百分比',
	            data: remotedata
	        });
		    	chart.hideLoading();
	    });
		
	}

    function showSeurityChart(sumdata){
	    $('#securitycontainer').highcharts({
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
	            text: '文件密级数量分布百分比'
	        },
	        tooltip: {
	        		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                innerSize: 100,
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
<div id="securitycontainer" style="height: 400px"></div>
		
</body>
</html>
