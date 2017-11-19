<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="fileclassify.table"/></title>


</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i> <fmt:message key="fileclassify.table"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/fileclassify/0" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form:form class="form-horizontal" id="inputForm"
					modelAttribute="model" action="${ctx}/web/fileclassify/save" method="post">
					<input type="hidden" name="id" value="${model.id}" />
					<fieldset class="prepend-top">

						<div id="messageBox" class="error" style="display: none"><fmt:message key="errorinfo"/></div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="fileclassify.code"/></label>
							<div class="controls">
								<form:input path="code"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="fileclassify.name"/></label>
							<div class="controls">
								<form:input path="name"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="fileclassify.miniSecurity"/></label>
							<div class="controls">
									<form:select path="miniSecurity" items="${levelmodels}" itemLabel="name"  itemValue="code"></form:select>  
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="system.comments"/></label>
							<div class="controls">
								<form:textarea path="comments" />
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
