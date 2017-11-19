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
							modelAttribute="querymodel" action="${ctx}/web/report/monthly/0" method="post">
						  <fieldset>
							<div class="control-group">
							  <label class="control-label" for="typeahead"><fmt:message key="report.monthly.year"/></label>
							  <div class="controls">
							  		<form:select path="year"  items="${years}"   ></form:select>  
							  		<form:select path="month" items="${months}"></form:select>  
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
		<!-- 月度数据分析 -->
		<div class="box span12  loadinfo"    >
			<div class="box-header well" data-original-title>
						<h3><fmt:message key="report.comments"/></h3>
					</div>
					<div class="box-content loadinfo"  loadurl="${ctx}/web/widget/report/orgmonthly/${querymodel.year}/${ querymodel.month}?orgid=${querymodel.orginfoId}">
					</div>
		</div>
	</div>
	<c:if test="${fn:length(models)>0}" >
	<div class="row-fluid sortable">
				<div class="box span6">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-list-alt"></i><fmt:message key="report.num"/></h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
							<div  class="loadinfo"
								loadurl="${ctx}/web/widget/report/monthly/security/pie/${querymodel.year}/${ querymodel.month}?orgid=${querymodel.orginfoId}">
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
								loadurl="${ctx}/web/widget/report/monthly/filetype/pie/${querymodel.year}/${ querymodel.month}?orgid=${querymodel.orginfoId}">
							</div>
				</div>
	</div>
	</c:if>				


	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i><fmt:message key="report.dept.monthly"/>
				</h2>
				<div class="box-icon">
				</div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="report.dept.name"/></th>
						<th><fmt:message key="report.year.month"/></th>
						<th><fmt:message key="file.secruitylevel.info"/></th>
						<th><fmt:message key="file.type"/></th>
						<th><fmt:message key="report.num.total"/></th>
						<th><fmt:message key="report.logs.total"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td  class="loadinfo" loadurl="${ctx}/web/widget/orginfo/${model.orgCode}/fullname">${model.orgCode}</td>
							<td>${model.year}<fmt:message key="report.year"/>${model.month}<fmt:message key="report.month"/></td>
							<td class="loadinfo"
								loadurl="${ctx}/web/widget/security/${model.securityCode}/name">${model.securityCode}</td>
							<td class="loadinfo"
								loadurl="${ctx}/web/widget/filetypename/${model.fileType}">${model.fileType}</td>
							<td>${model.settingNumber}</td>
							<td>${model.logNumber}</td>
						</tr>
					</c:forEach>
				</table>
				<div id="pagebar" class="pagination pagination-centered"
					pageurl="${ctx}/web/report/monthly/{pageindex}" total="${total}"
					totalpages="${totalpages}" pageindex="${pageIndex}"
					pagesize="${pagesize}">
					<ul>
						<li><a href="${ctx}/web/messages/"><fmt:message key="com.button.pre.page"/></a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">6</a></li>
						<li><a href="#">7</a></li>
						<li><a href="#">8</a></li>
						<li><a href="#">9</a></li>
						<li><a href="#">10</a></li>
						<li><a href="#"><fmt:message key="com.button.next.page"/></a></li>
					</ul>
				</div>
			</div>
		</div>
		
	</div>
	
	
	<script>
	$(document).ready(function(){
		showPagebar("#pagebar");
		showLabelName();
		$("#searchButton").on("click",function(){
			showSearch();
		});

	});

	function showLabelName(){
		$(".loadinfo").each(function(index,el){
			var url = $(el).attr("loadurl");
			$(el).load(url);
		});
	}
	function showSearch(){
		$('#usbkeymodal').modal('show');
	}

</script>


</body>
</html>
