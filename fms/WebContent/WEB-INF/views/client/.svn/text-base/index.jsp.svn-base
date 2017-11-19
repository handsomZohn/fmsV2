<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="client.info"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i> <fmt:message key="client.info"/>
				</h2>
				<div class="box-icon">
				</div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="client.mac"/></th>
						<th><fmt:message key="client.ip"/></th>
						<th><fmt:message key="client.name"/></th>
						<th><fmt:message key="user.loginname"/></th>
						<th><fmt:message key="usbkey.info"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td><a href="${ctx}/web/statistic/client/${model.clientMac}">${model.clientMac}</a></td>
							<td>${model.ipAddress}</td>
							<td>${model.clientName}</td>
							<td>${model.username}</td>
							<td><a href="${ctx}/web/usbkeyinfo/${model.usbkey}">${model.usbkey}</a></td>
							<td><a href="${ctx}/web/client/${model.clientMac}"><fmt:message key="com.button.edit"/></a></td>
						</tr>
					</c:forEach>
				</table>
				<div id="pagebar" class="pagination pagination-centered"
					pageurl="${ctx}/web/clients/{pageindex}" total="${total}"
					totalpages="${totalpages}" pageindex="${pageIndex}"
					pagesize="${pagesize}">
					<ul>
						<li><a href="${ctx}/web/messages/"><fmt:message key="com.button.pre.page"/></a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">6</a></li>
						<li><a href="#">7</a></li>
						<li><a href="#">8</a></li>
						<li><a href="#">9</a></li>
						<li><a href="#">10</a></li>
						<li><a href="#"><fmt:message key="com.button.next.page"/></a></li>
					</ul>
				</div>
			</div>
		</div>
		<!--/span-->
		
	</div>
	<script>
	$(document).ready(function(){
		showPagebar("#pagebar");
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
