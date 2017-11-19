<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<title><fmt:message key="login.pagetitle" /></title>
<script>
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="row-fluid">
				<div class="span12 center login-header">
					<h3>
						<fmt:message key="system.product" />
					</h3>
				</div>
			</div>
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<c:if test="${not empty message}">
						<div id="message" class="alert alert-info">${message}</div>
					</c:if>
					<form:form id="loginForm" class="form-horizontal"
						action="${ctx}/login" method="post">
						<fieldset>
							<div class="input-prepend" title="" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input
									autofocus class="input-large span10" name="username"
									id="username" type="text" value="admin" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input
									class="input-large span10" type="password" id="password"
									name="password" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend">
								<label class="remember" for="remember"><input
									type="checkbox" id="rememberMe" name="rememberMe" />
								<fmt:message key="login.rememberme" /></label>
							</div>
							<div class="clearfix"></div>

							<p class="center span5">
								<button type="submit" class="btn btn-primary">
									<fmt:message key="login.btnlogin" />
								</button>
							</p>
						</fieldset>

					</form:form>

				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/fluid-row-->

	</div>
	<!--/.fluid-container-->
</body>
