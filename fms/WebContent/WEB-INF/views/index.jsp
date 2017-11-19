<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="cn">
<head>
</head>

<body>
	<!-- content starts -->
	<div>
		<ul class="breadcrumb">
			<li><a href="#"><fmt:message key="system.controller"/></a> <span class="divider">/</span></li>
			<li><a href="#"><fmt:message key="system.index"/></a></li>
		</ul>
	</div>
	<div class="sortable row-fluid">
		<div data-rel="tooltip" title="监控终端总数量：${totalmember}"
			class="well span3 top-block">
			<a href="${ctx}/web/clients/0"><span
				class="icon32 icon-red  icon-user"></span>
			</a>
			<div><fmt:message key="report.clientnum.total"/></div>
			<div>${totalmember}</div>
		</div>

		<div data-rel="tooltip" title="文件监控总数：${totallog}"
			class="well span3 top-block">
			<a href="${ctx}/web/filelogs/0"><span
				class="icon32 icon-color  icon-book"></span>
			</a>
			<div><fmt:message key="report.filenum.total"/></div>
			<div>${totallog}</div>
		</div>

		<div data-rel="tooltip" title="文件定密总数：${totalsecurity}"
			class="well span3 top-block">
			<a href="${ctx}/web/filelogs/0"><span
				class="icon32 icon-color  icon-key"></span>
			</a>
			<div><fmt:message key="report.slnum.total"/></div>
			<div>${totalsecurity}</div>
		</div>

	</div>

	<div class="row-fluid">
		<div class="box span12">
			<div class="box-header well">
				<h2>
					<i class="icon-info-sign"></i> <fmt:message key="system.run"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<div id="mainmonitor"
					style="min-width: 310px; height: 400px; margin: 0 auto"></div>
			</div>
		</div>
	</div>
	<!-- content ends -->
	<script>
	$(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
    
        var chart = $('#mainmonitor').highcharts({
        	 chart: {
                 type: 'spline'
             },
             title: {
                 text: '30天内的监控日志统计'
             },
             xAxis: {
            		 type: 'category',
            		 tickPositions: [0, 10,20,29]
             },
             yAxis: {
                 title: {
                     text: '文档数量'
                 },
                 labels: {
                     formatter: function () {
                         return this.value ;
                     }
                 }
             },
             tooltip: {
                 crosshairs: true,
                 shared: true
             },
             plotOptions: {
                 spline: {
                     marker: {
                         radius: 4,
                         lineColor: '#666666',
                         lineWidth: 1
                     }
                 }
             },
 	        credits:{
	            enabled:false // 禁用版权信息
	       	}
        });
        chart = $('#mainmonitor').highcharts();
        chart.showLoading("正在加载监控定密数据，请稍等......");
        $.getJSON("${ctx}/json/summary/lastdays/30/SECURITY",function(remotedata){
            //alert("Data: " + remotedata + "\nStatus: " + status);
            chart.addSeries( {
                name: '定密文件数量',
                data: remotedata
            });
            chart.hideLoading();
         });
        $.getJSON("${ctx}/json/summary/lastdays/30/MONITOR",function(remotedata){
            //alert("Data: " + remotedata + "\nStatus: " + status);
            chart.addSeries( {
                name: '监控文件数量',
                data: remotedata
            });
         });
    });
    
		   </script>
</body>
</html>
