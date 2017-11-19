<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="user.accountmanager"/></title>
</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i><fmt:message key="user.infoedit"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/user" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form:form class="form-horizontal form-valid" id="inputForm"
					modelAttribute="user" action="${ctx}/web/user/save" method="post">
					<input type="hidden" name="id" value="${user.id}" />
					<fieldset class="prepend-top">

						<div id="messageBox" class="error" style="display: none"><fmt:message key="errorinfo"/></div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message 
										key="user.loginname"/></label>
							<div class="controls">
								<input type="text" id="loginName" name="loginName"
									value="${user.loginName}" datatype="*"  nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>

						<div class="control-group">
							<label for="name" class="control-label"><fmt:message
											key="user.username"/></label>
							<div class="controls">
								<input type="text" id="name" name="name" size="40"
									value="${user.name}" datatype="*"  nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>
						<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="user.password"/></label>
							<div class="controls">
								<input datatype="*6-16" type="password" id="password" name="password" size="40"
									value="" class="input_text" nullmsg="请填写6到16位任意字符" />
							</div>
						</div>
						<div class="control-group">
							<label for="passwordConfirm" class="control-label"><fmt:message key="user.confirmpwd" /></label>
							<div class="controls">
								<input datatype="*" type="password" id="passwordConfirm"
									name="passwordConfirm" size="40" value=""   equalTo="#password"
									class="input_text" nullmsg="请确认密码"/>
							</div>
						</div>
						<div class="control-group">
							<label for="email" class="control-label"><fmt:message
												key="user.email"/></label>
							<div class="controls">
								<input type="text" id="email" name="email" size="40"
									value="${user.email}" class="input" />
							</div>
						</div>
						<div class="control-group">
							<label for="role" class="control-label"><fmt:message
												key="user.role"/></label>
							<div class="controls">
								  <select name="userroleid">  
								  		<c:forEach items="${roles}"  var="role">
								  			<c:if test="${role.id == roleid}">
									            <option value="${role.id}" selected="selected">${role.name}</option>  
								            </c:if>
								  			<c:if test="${role.id != roleid }">
									            <option value="${role.id}">${role.name}</option>  
								            </c:if>
								  		</c:forEach>
							       </select>  
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
