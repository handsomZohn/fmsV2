<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>用户注册</title>

<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#email").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						required: true,
						remote: "${ctx}/register/user/checkLoginName"
					},
					email: {
						required: true,
						email: true,
						remote: "${ctx}/register/user/checkLoginMail"
					},
					password: {
						required: true
					},
					passwordConfirm: {
						equalTo: "#password"
					},
					verifyCode: {
						required: true,
						remote: "${ctx}/file/verifycode/reg/validate"
					},
					agreementCheck: {
						required: true
					}
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					},
					email: {
						remote: "此邮箱已被注册"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					},
					verifyCode: {
						required: "请输入验证码",
						remote: "验证码无效"
					},
					agreementCheck: {
						required: "请确认注册协议"
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if ( element.is(":checkbox") ){
						error.insertAfter( document.getElementById("notAgree") );
						error.appendTo ( element.parent().next() );
					} else {
						error.insertAfter( element );
					}
				}
			});
		});
	</script>
<script>
		//新打开的页面，设置定时自动刷新一次
		var autoRefresh = setTimeout("reloadCodeNext()", 3*60*1000);	
		function reloadCode(){
			var verify = document.getElementById('validate');
		    verify.src = "${ctx}/file/verifycode/reg/new?=" + getRandom();
			//刷新验证码后，关闭上一个自动刷新，定时再自动刷新一下
			clearTimeout(autoRefresh);
			autoRefresh = setTimeout("reloadCodeNext()", 3*60*1000);
		}
	
		function reloadCodeNext(){
			var verify = document.getElementById('validate');
		    verify.src = "${ctx}/file/verifycode/reg/new?=" + getRandom();
		}		
	
		function getRandom(){
			return Math.random().toString().substring(2);
		}	
	
		$(document).ready(function() {
			$("#message").fadeOut(3000);
		});
	</script>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>
	</c:if>
	<div id="messageBox" class="error" style="display: none"><fmt:message key="errorinfo"/></div>

	<div class="regist_title"><fmt:message key="system.reg.info"/></div>
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/web/user/password" method="post">
		<tr>
			<td align="right"><fmt:message key="user.password"/></td>
			<td><input type="password" id="password" name="password"
				size="40" value="${user.password}" class="input_text" minlength="3" />
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><img src="${ctx}/static/images/password_07.jpg" width="105"
				height="22" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="user.confirmpwd"/></td>
			<td><input type="password" id="passwordConfirm"
				name="passwordConfirm" size="40" value="${user.password}"
				class="input_text" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="system.validate"/></td>
			<td><input name="verifyCode" type="text" class="input_text2" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><label><span class="f"><input
						name="agreementCheck" type="checkbox" /><fmt:message key="system.agree"/></span></label><a
				href="${ctx}/register/user/agreement" class="f" target="_blank"><fmt:message key="system.agree.user"/></a></td>
			<td>&nbsp;<span id="notAgree"></span></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input id="submit" type="submit" class="btn2" value="提交" />
				<input id="cancel" type="button" class="btn2" value="返回"
				onclick="history.back()" /></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</table>
	</form:form>
</body>
</html>