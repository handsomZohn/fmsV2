<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<!--SiteMesh Setup -->
<meta name="decorator" content="widget" />
<title>部门列表名称</title>
</head>

<body>
				${result}
</body>
</html>
