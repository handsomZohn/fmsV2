<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<!--SiteMesh Setup -->
<meta name="decorator" content="widget" />
<title>监控终端</title>
</head>

<body>
				<ul class="thumbnails gallery "   >
					<c:forEach items="${models}" var="model">
							<li id="image-2" class="thumbnail">
								<a style="background:url(${ctx}/static/img/header/1.jpeg)" title="WWW的计算机" href="${ctx }/web/statistic/client/${model.clientMac}"><img class="grayscale" src="${ctx}/static/img/header/1.jpeg" alt="WWW的计算机"></a>
								<p>${model.clientMac}</p>
							</li>
					</c:forEach>
				</ul>



</body>
</html>
