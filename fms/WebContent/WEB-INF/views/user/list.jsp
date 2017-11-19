<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="user.manager"/></title>
<script>

</script>
</head>

<body>

	<c:if test="${not empty  reinfo}">
		<c:if test="${!reinfo.code}">
			<div class="alert alert-error">
				<button data-dismiss="alert" class="close" type="button">×</button>
				<strong><fmt:message key="oper.tips"/></strong> ${reinfo.message}
			</div>
		</c:if>
		<c:if test="${reinfo.code}">
			<div class="alert alert-success">
				<button data-dismiss="alert" class="close" type="button">×</button>
				<strong><fmt:message key="oper.tips"/></strong> ${reinfo.message}
			</div>
		</c:if>
	</c:if>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-user"></i> <fmt:message key="user.manager"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/user/create" class="btn btn-round"><i
						class="icon-edit"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="user.loginname"/></th>
						<th><fmt:message key="user.username"/></th>
						<th><fmt:message key="user.role"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="user">
						<tr>
							<td>${user.loginName}</td>
							<td>${user.name}</td>
							<td class="loadinfo" loadurl="${ctx}/web/widget/roles/${user.id}">loading...</td>
							<td>
							<a href="${ctx}/web/user/update/${user.id}"
								id="editLink-${user.name}"><fmt:message
										key="com.button.assign" /></a>
							<a href="${ctx}/web/user/update/${user.id}"
								id="editLink-${user.name}"><fmt:message
										key="com.button.edit" /></a>
										 <a
								href="${ctx}/web/user/delete/${user.id}"><fmt:message
										key="com.button.delete" /></a>
								</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
		<a class="btn btn-primary" href="${ctx}/web/user/create"><fmt:message key="user.create"/></a>
		<a class="btn btn-primary" href="${ctx}/web/roles"><fmt:message key="role.manager"/></a>
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

