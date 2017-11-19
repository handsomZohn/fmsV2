<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<!--SiteMesh Setup -->
<meta name="decorator" content="widget" />
</head>
<body>
				 <div class="control-group">
						<div class="controls">
								 USBKey<input  class=""  id="usbkeyFilter" name="usbkeyQuery"/>
								 <input class="btn btn-primary"  type="button" id="queryButton" value="过滤"></input>
						</div>
				</div>
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="usbkey.usbkey"/></th>
						<th><fmt:message key="user.username"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="umodel">
						<tr>
							<td>${umodel.usbkey}</td>
							<td>${umodel.username}</td>
							<td>
									<input type="checkbox" name="usbkeyid" value="${umodel.usbkey}">
							</td>
						</tr>
					</c:forEach>
				</table>

</body>
</html>