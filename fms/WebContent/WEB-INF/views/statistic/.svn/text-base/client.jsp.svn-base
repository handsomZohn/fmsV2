<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="statistic.client"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i> ${clientinfo.clientName}<fmt:message key="statistic.client.mylogs"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<div id="stackbar"></div>
			
			</div>
		</div>	
	
				
			
	</div>
<script>

    
    $(document).ready(function () {
		$.getJSON("${ctx}/json/filetypes",function(remotedata){
			var filetypes = remotedata;
			updateStackbar(filetypes);
	    });
		
    });
    
    
    function updateStackbar(filetypes){
    		var arrayTypes= new Array();
    		$.each(filetypes, function(i, item){     
    			arrayTypes[i] = item.name;
    		});     
    	   $('#stackbar').highcharts({
          	 chart: {
                   type: 'column'
               },
               title: {
                   text: '文档类型及定密数量分布图'
               },
               credits:{
                   enabled:false // 禁用版权信息
              },                                                                 
               xAxis: {
                   categories:arrayTypes
               },
               yAxis: {
                   min: 0,
                   title: {
                       text: '定密文档数量'
                   }
               },
               tooltip: {
                   headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                   pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                       '<td style="padding:0"><b>{point.y} </b></td></tr>',
                   footerFormat: '</table>',
                   shared: true,
                   useHTML: true
               },
               plotOptions: {
                   column: {
                       pointPadding: 0.2,
                       borderWidth: 0
                   }
               },
	   	        credits:{
		            enabled:false // 禁用版权信息
		       }
               
          });
           chart = $('#stackbar').highcharts();
           chart.showLoading("正在加载终端的统计数据，请稍等......");
           $.getJSON("${ctx}/json/summary/securitytotal/${clientinfo.clientMac}",function(remotedata){
	        	   $.each(remotedata, function(key, val){     
	                   chart.addSeries( {
	                       name: key,
	                       data: val
	                   });
	       		});  
               chart.hideLoading();
            });
          
          
    }

</script>


</body>
</html>
