<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="message.manager"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="message.manager"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/message" class="btn btn-round"><i
						class="icon-edit"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="message.title"/></th>
						<th><fmt:message key="message.ts"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="message">
						<tr>
							<td>${message.title}</td>
							<td>${message.ts}</td>
							<td><a href="${ctx}/web/message/update/${message.id}"><fmt:message key="com.button.edit"/></a>
								<a href="${ctx}/web/message/delete/${message.id}"><fmt:message key="com.button.delete"/></a></td>
						</tr>
					</c:forEach>
				</table>
				<div id="pagebar" class="pagination pagination-centered"
					pageurl="${ctx}/web/messages/{pageindex}" total="${total}"
					totalpages="${totalpages}" pageindex="${pageindex}"
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
		<a class="btn btn-primary" href="${ctx}/web/message"><fmt:message key="com.button.add"/></a>
	</div>
	<script>
	$(document).ready(function(){
		showPagebar("#pagebar");
	});
</script>


</body>
</html>
