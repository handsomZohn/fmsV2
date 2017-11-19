<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>


<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="cn">
<head>
<title><fmt:message key="system.product" />-<sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="<fmt:message key="system.product" />" />
<meta name="author" content="<fmt:message key="system.author" />" />


<!-- The styles -->
<link href="${ctx}/static/css/bootstrap-redy.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/charisma-app.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/static/css/jquery-ui-1.8.21.custom.css"
	rel="stylesheet" type="text/css" />
<link href='${ctx}/static/css/fullcalendar.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/fullcalendar.print.css' rel='stylesheet'
	media='print' type="text/css" />
<link href='${ctx}/static/css/chosen.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/uniform.default.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/colorbox.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/jquery.cleditor.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/jquery.noty.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/noty_theme_default.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/elfinder.min.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/elfinder.theme.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/jquery.iphone.toggle.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/opa-icons.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/uploadify.css' rel='stylesheet'
	type="text/css" />
<link href='${ctx}/static/css/valid.css' rel='stylesheet'
	type="text/css" />

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="${ctx}/static/img/favicon.ico" />

<!-- jQuery -->
<script src="${ctx}/static/js/jquery-1.7.2.min.js"></script>
<!-- jQuery UI -->
<script src="${ctx}/static/js/jquery-ui-1.8.21.custom.min.js"></script>
<!-- transition / effect library -->
<script src="${ctx}/static/js/bootstrap-transition.js"></script>
<!-- alert enhancer library -->
<script src="${ctx}/static/js/bootstrap-alert.js"></script>
<!-- modal / dialog library -->
<script src="${ctx}/static/js/bootstrap-modal.js"></script>
<!-- custom dropdown library -->
<script src="${ctx}/static/js/bootstrap-dropdown.js"></script>
<!-- scrolspy library -->
<script src="${ctx}/static/js/bootstrap-scrollspy.js"></script>
<!-- library for creating tabs -->
<script src="${ctx}/static/js/bootstrap-tab.js"></script>
<!-- library for advanced tooltip -->
<script src="${ctx}/static/js/bootstrap-tooltip.js"></script>
<!-- popover effect library -->
<script src="${ctx}/static/js/bootstrap-popover.js"></script>
<!-- button enhancer library -->
<script src="${ctx}/static/js/bootstrap-button.js"></script>
<!-- accordion library (optional, not used in demo) -->
<script src="${ctx}/static/js/bootstrap-collapse.js"></script>
<!-- carousel slideshow library (optional, not used in demo) -->
<script src="${ctx}/static/js/bootstrap-carousel.js"></script>
<!-- autocomplete library -->
<script src="${ctx}/static/js/bootstrap-typeahead.js"></script>
<!-- tour library -->
<script src="${ctx}/static/js/bootstrap-tour.js"></script>
<!-- library for cookie management -->
<script src="${ctx}/static/js/jquery.cookie.js"></script>
<!-- calander plugin -->
<script src='${ctx}/static/js/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='${ctx}/static/js/jquery.dataTables.min.js'></script>

<!-- chart libraries start -->
<script src="${ctx}/static/js/excanvas.js"></script>
<script src="${ctx}/static/js/jquery.flot.min.js"></script>
<script src="${ctx}/static/js/jquery.flot.pie.min.js"></script>
<script src="${ctx}/static/js/jquery.flot.stack.js"></script>
<script src="${ctx}/static/js/jquery.flot.resize.min.js"></script>
<!-- select or dropdown enhancer -->
<script src="${ctx}/static/js/jquery.chosen.min.js"></script>
<!-- checkbox, radio, and file input styler -->
<script src="${ctx}/static/js/jquery.uniform.min.js"></script>
<!-- plugin for gallery image view -->
<script src="${ctx}/static/js/jquery.colorbox.min.js"></script>
<!-- rich text editor library -->
<script src="${ctx}/static/js/jquery.cleditor.min.js"></script>
<!-- notification plugin -->
<script src="${ctx}/static/js/jquery.noty.js"></script>
<!-- file manager library -->
<script src="${ctx}/static/js/jquery.elfinder.min.js"></script>
<!-- star rating plugin -->
<script src="${ctx}/static/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="${ctx}/static/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="${ctx}/static/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="${ctx}/static/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="${ctx}/static/js/jquery.history.js"></script>
<script src="${ctx}/static/js/jquery.validform-v5.3.2.js"></script>
<script src="${ctx}/static/js/highcharts.js"></script>
<script src="${ctx}/static/js/highcharts-3d.js"></script>
<script src="${ctx}/static/js/util.js"></script>


<sitemesh:head />
</head>

<body>
	<!-- topbar starts -->
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<!-- topbar ends -->

	<div class="container-fluid">
		<div class="row-fluid">

			<!-- left menu starts -->
			<%@ include file="/WEB-INF/layouts/left.jsp"%>
			<!-- left menu ends -->
			<div id="content" class="span10">
				<!-- content starts -->
				<sitemesh:body />
				<!-- content ends -->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->


		<hr />

		<%@ include file="/WEB-INF/layouts/footer.jsp"%>


	</div>



</body>

</html>
<script>
    $(document).ready(function(){
        //数据校验
        $(".form-valid").Validform({
       	 	tiptype:4,
         });
    });
    
</script>
