<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="securitylimit"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="securitylimit"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="securitylimit.keyword"/></th>
						<th><fmt:message key="securitylimit.lower"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td>${model.keyword}</td>
							<td class="loadname"
								loadnameurl="${ctx}/web/widget/security/${model.securityCode}/name">${model.securityCode}</td>
							<td>
								<a href="${ctx}/web/securitylimit/${model.id}"  class="btn btn-primary"><fmt:message key="com.button.edit"/></a>
								<a href="${ctx}/web/securitylimit/delete/${model.id}"  class="btn btn-primary"><fmt:message key="com.button.delete"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
		<!--/span-->
		<a class="btn btn-primary" href="${ctx}/web/securitylimit/create"><fmt:message key="com.button.add"/></a>
		
	</div>
	<script>
	$(document).ready(function(){
		showLabelName();
	});

	function showLabelName(){
		$(".loadname").each(function(index,el){
			var url = $(el).attr("loadnameurl");
			$(el).load(url);
		});
	}
</script>


</body>
</html>
