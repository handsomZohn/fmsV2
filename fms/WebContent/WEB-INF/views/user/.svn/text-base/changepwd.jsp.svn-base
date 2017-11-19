<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />


<html>
<head>
<title><fmt:message key="user.updatepwd"/></title>
<c:if test="${not empty  reinfo}">
		<c:if test="${reinfo.code eq '1'}">
		<meta name="decorator" content="single" />
		</c:if>
</c:if>
</head>

<body>
<body>
	<c:if test="${not empty  reinfo}">
		<c:if test="${reinfo.code eq '0' || reinfo.code eq '1'}">
			<div class="alert alert-error">
				<button data-dismiss="alert" class="close" type="button">×</button>
				<strong><fmt:message key="user.opertips"/></strong> ${reinfo.message}
			</div>
		</c:if>
		<c:if test="${reinfo.code eq '9' }">
			<div class="alert alert-success">
				<button data-dismiss="alert" class="close" type="button">×</button>
				<strong><fmt:message key="user.opertips"/></strong> ${reinfo.message}
			</div>
		</c:if>
	</c:if>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="user.updatepwd"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/messages/0" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">

				<form:form id="inputForm" action="${ctx}/web/user/changepwd"
					method="post" class="form-horizontal form-valid">
					<fieldset class="prepend-top">
						<div class="control-group">
							<label for="loginName" class="control-label"><fmt:message
									key="user.password" /></label>
							<div class="controls">
								<input type="password" datatype="*5-18" ip="输入旧密码"
									nullmsg="请输入旧密码！" id="password" name="password" size="40"
									value="" class="input_text" />
							</div>
						</div>
						<div class="control-group">
							<label for="loginName" class="control-label"><fmt:message
									key="user.newpwd" /></label>
							<div class="controls">
								<input type="password" datatype="*6-16" id="newpwd"
									name="newpwd" size="40" value="" class="input_text"
									minlength="5" nullmsg="请填写6到16位任意字符"/>
							</div>
						</div>
						<div class="control-group">
							<label for="loginName" class="control-label"><fmt:message
									key="user.confirmpwd" /></label>
							<div class="controls">
								<input type="password" datatype="*5-18" recheck="newpwd"
									id="confirmpwd" name="confirmpwd" size="40" value=""
									class="input_text" minlength="5" nullmsg="请确认密码"/>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<input id="submit" class="btn btn-primary" type="submit"
							value="<fmt:message key="com.button.save" />" />&nbsp; <input
							id="cancel" class="btn" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</form:form>
			</div>
		</div>
		<!--/span-->
	</div>
	
	<!--/row-->
</body>

</html>