<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>重置密码</title>

<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#password").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					password: {
						required: true
					},
					passwordConfirm: {
						required: true,
						equalTo: "#password"
					}
				},
				messages: {
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
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
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/reset/user/modForgetPwd" method="post">
		<input type="hidden" id="token" name="token" value="${token}" />
		<table width="830" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="36" colspan="2">登录名：<b>${user.loginName}</b></td>
			</tr>
			<tr>
				<td height="56" colspan="2">你的新口令（英文字母，符号或数字）：</td>
				<td align="right">&nbsp;</td>
				<td align="left">&gt;&gt; <span class="blue">已有点课网账号？</span> <a
					href="${ctx}/register/user">立即注册</a>
				</td>
			</tr>
			<tr>
				<td colspan="4"><input type="password" id="password"
					name="password" size="40" value="" class="input_text" minlength="3" />
				</td>
			</tr>
			<tr>
				<td height="56" colspan="2">再输一次</td>
				<td align="right">&nbsp;</td>
				<td width="310" align="left"><a href="#">如何使密码更安全？</a></td>
			</tr>
			<tr>
				<td colspan="4"><input type="password" id="passwordConfirm"
					name="passwordConfirm" size="40" value="" class="input_text" /></td>
			</tr>
			<tr>
				<td width="96"><input id="submit" type="submit" class="btn5"
					value="确认新密码" /></td>
				<td width="165">&nbsp;</td>
				<td width="259">&nbsp;</td>
				<td>1.使用标点符号、数字和大小写字母的组合作为密码。<br /> 2.密码中勿包含个人信息（如姓名、生日等）。<br />
					3.不使用和其他网站相同的密码。<br /> 4.定期修改密码。
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>