<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="filetype.define"/></title>


</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i> <fmt:message key="filetype.define"/>
				</h2>
				<div class="box-icon">
					<a href="${ctx}/web/sls" class="btn btn-round"><i
						class="icon-list"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form:form class="form-horizontal form-valid" id="inputForm"
					modelAttribute="model" action="${ctx}/web/filetype/save" method="post">
					<fieldset class="prepend-top">
		 				<form:hidden   path="id"/>
						<div id="messageBox" class="error" style="display: none"><fmt:message key="errorinfo"/></div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="filetype.code"/></label>
							<div class="controls">
								<form:input path="code" datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="filetype.suffix"/></label>
							<div class="controls">
								<form:input path="suffixName" datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="loginName"><fmt:message key="file.type"/></label>
							<div class="controls">
								<form:input path="name" datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/>
							</div>
						</div>
						
						<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="filetype.ismonitor"/></label>
							<div class="controls">
								 <form:select path="monitor">  
						            	 <form:option value="true"  label="是"/>  
								     <form:option value="false"  label="否"/>  
						        </form:select> 
							</div>
						</div>
						
							<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="filetype.issecuritylevel"/></label>
							<div class="controls">
								 <form:select path="securityLevel">  
						            	 <form:option value="true"  label="是"/>  
								     <form:option value="false"  label="否"/>  
						        </form:select> 
							</div>
						</div>
						
							<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="process.md5"/></label>
							<div class="controls">
								 <form:textarea path="processMD5"  datatype="*" size="50" nullmsg="请填写信息"
									 class="input_text"/>  
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
