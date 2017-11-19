<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>头像上传成功</title>
<script language="javascript">
		function callRefreshFace(){
			try {
				var type = typeof eval(parent.refreshFace);
				if(type == "function"){
					parent.refreshFace();
				}else{
					alert(eval(parent.refreshFace));
				}
			} catch (e) {
				alert(e);
			}		
		}
		
		$(document).ready(function (){
			if(window.parent != window){
				callRefreshFace();
			}
		});
	</script>
</head>

<body>
	<div class="photo_title">上传头像——成功</div>
	<br />

	<div style="margin-top: 10px; margin-left: 25px;">
		<table>
			<tr>
				<td height="56" cospan="2">完成上传！预览如下：</td>
			</tr>
			<tr>
				<td align="center"><img
					src="${ctx}/servlet/FaceBrowseServlet?faceId=${faceId}&random=<%=Math.random() %>"
					width="80" height="100" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="button"
					name="uploadIt" class="opt_button" value="重传"
					onclick="javascript: location.href='${ctx}/upload/face/preUploadFace/${token}';" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


