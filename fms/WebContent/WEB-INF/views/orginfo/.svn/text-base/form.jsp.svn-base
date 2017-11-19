<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="orginfo.edit"/></title>


</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i><fmt:message key="orginfo.info"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/orginfoes" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form:form class="form-horizontal form-valid" id="inputForm"
					modelAttribute="model" action="${ctx}/web/orginfo/save" method="post">
					<fieldset class="prepend-top">
		 				<form:hidden   path="id"/>
		 				<form:hidden   path="parentId"/>
						<div id="messageBox" class="error" style="display: none"><fmt:message key="errorinfo"/></div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="orginfo.code"/></label>
							<div class="controls">
								<form:input path="code" datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/><fmt:message key="orginfo.code.define"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="orginfo.name"/></label>
							<div class="controls">
								<form:input path="name" datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>
						<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="orginfo.manager"/></label>
							<div class="controls">
								 <form:textarea path="comments"/>  
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<input id="submit" class="btn btn-primary" type="submit"
							value="提交" />&nbsp; <input id="cancel" class="btn" type="button"
							value="返回" onclick="history.back()" />
					</div>
				</form:form>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->

</body>
</html>
