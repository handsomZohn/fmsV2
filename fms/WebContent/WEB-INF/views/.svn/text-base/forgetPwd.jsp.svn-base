<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>密码重置邮件</title>

<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#email").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					email: {
						required: true,
						remote: "${ctx}/password/user/isOwerEmail/${loginName}"
					}
				},
				messages: {
					email: {
						remote: "填写的邮箱无效"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if ( element.is(":checkbox") )
						error.appendTo ( element.parent().next() );
					else
						error.insertAfter( element );
				}
			});
		});
	</script>
<script>
	 $(document).ready(function() {
		 $("#message").fadeOut(3000);
	});
	</script>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>
	</c:if>
	<div id="messageBox" class="error" style="display: none">输入有误，请先更正。</div>

	<div class="blue2">重置密码</div>
	<form:form id="inputForm"
		action="${ctx}/password/user/sendMailRetrievePwd" method="post">
		<input type="hidden" id="loginName" name="loginName"
			value="${loginName}" />
		<table width="670" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="53" height="56" align="right">名称：</td>
				<td><b>${loginName}</b></td>
			</tr>
			<tr>
				<td width="53" height="56" align="right">邮箱：</td>
				<td width="233"><input type="text" id="email" name="email"
					size="40" value="${email}" class="input_text" /></td>
				<td width="162"><img src="${ctx}/static/images/i_03.jpg"
					width="8" height="12" /> 请输入你的email地址</td>
				<td width="212">&gt;&gt; <span class="blue">已有点课网账号？</span> <a
					href="${ctx}/register/user">立即注册</a>
				</td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td><input id="submit" type="submit" class="btn2" value="提交" />
					<input id="cancel" type="button" class="btn2" value="返回"
					onclick="history.back()" /></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>
</body>
</html>