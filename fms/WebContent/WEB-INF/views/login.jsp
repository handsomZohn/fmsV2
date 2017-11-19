<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>

<shiro:user>
	<%@ include file="/WEB-INF/views/logined.inc"%>
</shiro:user>

<shiro:guest>
	<%@ include file="/WEB-INF/views/login.inc"%>
</shiro:guest>

</html>
