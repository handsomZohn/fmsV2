<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>头像上传</title>
<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#file").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					file: {
						required: true
					}
				},
				messages: {
					file: {
						required: "请选择需要上传的图片."
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if ( element.is(":checkbox") )
						error.appendTo ( element.parent().next() );
					else
						error.insertAfter( element );
				}
			});
		});
	</script>
<script>
	 $(document).ready(function() {
		 $("#message").fadeOut(3000);
	});
	</script>
</head>

<body>
	<div class="photo_title">上传头像——开始</div>
	<br />
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>
	</c:if>
	<div id="messageBox" class="error" style="display: none">输入有误，请先更正。</div>

	<div style="margin-top: 10px; margin-left: 25px;">
		<form id="inputForm" action="${ctx}/servlet/FaceUploadServlet"
			method="post" enctype="multipart/form-data">
			<input type="hidden" id="token" name="token" value="${token}" /> <input
				type="hidden" id="userId" name="userId" value="${userId}" />
			<table>
				<tr>
					<td>&nbsp;</td>
					<td height="56">你可以上传JPG,JPEG,GIF,PNG或BMP文件，每个文件大小可以到3M。<br />
						尺寸为 80 * 100 像素最佳。
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>上传：<input type="file" id="file" name="file" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center" colspan="2"><input type="submit"
						name="uploadIt" class="opt_button" value="上传" /> <input
						type="reset" name="reset" class="opt_button" value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
