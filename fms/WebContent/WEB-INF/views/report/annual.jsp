<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="report.dept.log"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<!-- 月度数据分析 -->
		<div class="box span12" >
			<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> <fmt:message key="report.condition"/></h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
						<form:form class="form-horizontal" id="searchform"
							modelAttribute="querymodel" action="${ctx}/web/report/annual" method="post">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="typeahead"><fmt:message key="report.monthly.year"/></label>
							  <div class="controls">
							  		<form:select path="year"  items="${years}"   ></form:select>  
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label"><fmt:message key="orginfoes"/></label>
							  <div class="controls">
							  		<form:select path="orginfoId"  items="${orginfoes}" itemLabel="showTag" itemValue="id"></form:select>  
							  </div>
							</div>
							<div class="form-actions">
							  <button type="submit" class="btn btn-primary"><fmt:message key="com.button.query"/></button>
							</div>
						  </fieldset>
						</form:form>
					</div>				
		</div>
	</div>

	<div class="row-fluid sortable">
				<div class="box span6">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-list-alt"></i><fmt:message key="report.num"/></h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
							<div  class="loadinfo"
								loadurl="${ctx}/web/widget/report/annual/security/pie/${querymodel.year}?orgid=${querymodel.orginfoId}">
							</div>
					</div>
				</div>
				
				<div class="box span6">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-list-alt"></i><fmt:message key="report.filetype"/></h2>
						<div class="box-icon"> 
						</div>
					</div>
					<div class="box-content">
							<div   class="loadinfo"
								loadurl="${ctx}/web/widget/report/annual/filetype/pie/${querymodel.year}?orgid=${querymodel.orginfoId}">
							</div>
				</div>
	</div>
	<div class="row-fluid sortable">
		<!-- 月度数据分析 -->
		<div class="box span12  loadinfo"    >
			<div class="box-header well" data-original-title>
						<h3><fmt:message key="report.comments"/></h3>
					</div>
					<div class="box-content loadinfo"  loadurl="${ctx}/web/widget/report/annual/${querymodel.year}?orgid=${querymodel.orginfoId}">
					</div>
		</div>
	</div>
	
	<script>
	$(document).ready(function(){
		showLabelName();
	});

	function showLabelName(){
		$(".loadinfo").each(function(index,el){
			var url = $(el).attr("loadurl");
			$(el).load(url);
		});
	}

</script>


</body>
</html>
