<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>头像上传失败</title>
</head>

<body>
	<div class="photo_title">上传头像——失败</div>
	<br />
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>
	</c:if>
	<div style="margin-top: 10px; margin-left: 25px;">
		<table>
			<tr>
				<td height="56" cospan="2">上传失败！</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center"><input type="button" name="uploadIt"
					class="opt_button" value="重传"
					onclick="javascript: location.href='${ctx}/upload/face/preUploadFace/${token}';" />
					<input type="button" id="cancel" class="opt_button" value="返回"
					onclick="history.back()" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
