<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="file.secruitylevel.define"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="file.secruitylevel.define"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="file.securitylevel.code"/></th>
						<th><fmt:message key="file.securitylevel.name"/></th>
						<th><fmt:message key="filetype.ismonitor"/></th>
						<th><fmt:message key="system.comments"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td>${model.code}</td>
							<td>${model.name}</td>
							<td>
								<c:if test="${model.used}">
									<fmt:message key="system.istrue"/>
								</c:if>
								<c:if test="${model.used==false}">
									<fmt:message key="system.isfalse"/>
								</c:if>
								
							</td>
							<td>${model.comments}</td>
							<td>
								<a href="${ctx}/web/sl/${model.code}"  class="btn btn-primary"><fmt:message key="com.button.edit"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
		<!--/span-->
		<a class="btn btn-primary" href="${ctx}/web/sl/create"><fmt:message key="com.button.add"/></a>
		
	</div>
	<script>
	$(document).ready(function(){
		showLabelName();
	});

	function showLabelName(){
		$(".loadinfo").each(function(index,el){
			var url = $(el).attr("loadurl");
			$(el).load(url);
		});
	}
</script>


</body>
</html>
