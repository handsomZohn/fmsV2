<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
					<a href="${ctx}/web/messages/0" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">

				<form:form id="inputForm" modelAttribute="message"
					action="${ctx}/web/message" method="post" class="form-horizontal form-valid">
					<input type="hidden" name="id" value="${message.id}" />
					<fieldset class="prepend-top">
						<div class="control-group">
							<label for="loginName" class="control-label"><fmt:message key="message.title"/></label>
							<div class="controls">
								<input type="text" id="title" name="title" size="40" datatype="*"nullmsg="请填写信息"
									value="${model.title}" class="input input-xxlarge" />
							</div>
						</div>
						<div class="control-group">
							<label for="name" class="control-label"><fmt:message key="message.comments"/></label>
							<div class="controls">
								<textarea row=10 id="msgContent" name="msgContent"
									style="width: 600px; height: 120px;" class="cleditor">${model.msgContent}</textarea>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<input id="submit" class="btn btn-primary" type="submit"
							value="发布" />&nbsp; <input id="cancel" class="btn" type="button"
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
