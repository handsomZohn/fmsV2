<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>密码重置邮件发送成功</title>
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

	<div class="blue2">重置密码</div>
	<table width="670" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="56" colspan="3">请到 <a href="#">&nbsp;${email}&nbsp;</a>查阅来自豆瓣的邮件，从邮件重设你的密码。
			</td>
			<td width="212">&gt;&gt; <span class="blue">已有点课网账号？</span> <a
				href="${ctx}/register/user">立即注册</a>
			</td>
		</tr>
		<tr>
			<td width="233"><input name="loginMail" type="button"
				class="btnLong" value="登录邮箱查收确认信"
				onclick="javascript: if(confirm('新打开一个窗口，输入邮箱地址')){window.open('', '_blank'); }" />
			</td>
			<td width="162">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</body>
</html>