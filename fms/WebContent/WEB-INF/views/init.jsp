<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
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
					<a href="${ctx}/login"><fmt:message key="system.login.start"/></a>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/fluid-row-->

	</div>
	<!--/.fluid-container-->
</body>
