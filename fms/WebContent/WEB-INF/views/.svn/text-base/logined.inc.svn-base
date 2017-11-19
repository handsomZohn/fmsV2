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
<title></title>
<script>
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="row-fluid">
				<div class="span12 center login-header">快速登录</div>
			</div>
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div id="message" class="alert alert-info">
						<shiro:principal property="name" />
						，系统检测到您已经登录！
					</div>
					<form:form id="loginedForm" action="${ctx}/logined" method="post"
						class="form-horizontal">
						<input type="hidden" name="path" value="${path}">
						<input type="hidden" name="token" value="${token}">
						<fieldset>
							<p class="center span4">
								<button type="submit" class="btn btn-primary">快速登录系统</button>
							</p>
							<a href="${ctx}/logout?token=${token}">重新登录</a>
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

