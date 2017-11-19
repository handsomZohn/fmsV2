<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="cn">
<head>
<meta name="decorator" content="error" />
</head>

<body>
		
		<div class="clear">
		</div>
		<div class="content">
			${message}
		</div>
	</body>
</html>
