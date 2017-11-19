<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>客户端版本管理<fmt:message key="role.manager"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="client.version.manager"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="client.version"/></th>
						<th><fmt:message key="client.download"/></th>
						<th><fmt:message key="system.comments"/></th>
						<th><fmt:message key="client.update.ts"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td>${model.code}</td>
							<td  style="max-width:200px;word-break:break-all">${model.url}</td>
							<td  style="max-width:200px;word-break:break-all">${model.comment}</td>
							<td>${model.ts}</td>
							<td>
								<a href="${ctx}/web/version/${model.id}"  class="btn btn-primary"><fmt:message key="com.button.edit"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
		<!--/span-->
		<a class="btn btn-primary" href="${ctx}/web/version/create"><fmt:message key="client.create.new"/></a>
		
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
