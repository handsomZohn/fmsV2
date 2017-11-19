<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="usbkey.manager.dept"/></title>
</head>

<body>

	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-envelope"></i> ${model.name}<fmt:message key="usbkey.table.dept"/>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<table id="contentTable"
					class="table table-striped table-bordered bootstrap-datatable datatable">
					<tr>
						<th><fmt:message key="usbkey.dept"/></th>
						<th><fmt:message key="usbkey.usbkey"/></th>
						<th><fmt:message key="usbkey.dept.role"/></th>
						<th><fmt:message key="operation"/></th>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td  class="loadinfo" loadurl="${ctx}/web/widget/orginfo/${model.orginfoCode}/fullname">${model.orginfoCode}</td>
							<td>${model.usbkey}
								<c:if test="${model.role == 'EMPLOYEE'}">
									<a href ="${ctx}/web/orgusbkey/${model.id}/role"><fmt:message key="usbkey.role.define1"/></a>
								</c:if>
								<c:if test="${model.role== 'LEADER'}">
									<a href ="${ctx}/web/orgusbkey/${model.id}/role"><fmt:message key="usbkey.role.define2"/></a>
								</c:if>
							</td>
							<td>
								<c:if test="${model.role == 'EMPLOYEE'}">
									<fmt:message key="usbkey.dept.emp"/>
								</c:if>
								<c:if test="${model.role== 'LEADER'}">
									<fmt:message key="usbkey.dept.lead"/>
								</c:if>
							</td>
							<td>
								<a href="${ctx}/web/orgusbkey/delete/${model.id}"><fmt:message key="com.button.delete"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
		<!--/span-->
		<a class="btn btn-primary"  id="addUSBKey"><fmt:message key="com.button.add"/></a>
		<a class="btn btn-primary" href="${ctx}/web/orginfoes"><fmt:message key="com.button.return"/></a>
	</div>
	
	<div class="modal hide fade" id="usbkeymodal">
			<input type="hidden" id="orgid" value="${model.id}" />
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3><fmt:message key="usbkey.table.user"/></h3>
			</div>
			<div class="modal-body " >
				<div class="loadinfo"  loadurl="${ctx}/web/widget/orginfo/${model.id}/unassignusbkey">
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal"><fmt:message key="com.button.close"/></a>
				<a href="#" class="btn btn-primary"  id="saveUSBKey"><fmt:message key="com.button.save"/></a>
			</div>
	</div>
	
	<script>
	$(document).ready(function(){
		showLabelName();
		$("#addUSBKey").on("click",function(){
			addUSBKey();
		});
		$("#saveUSBKey").on("click",function(){
			saveUSBKey();
		});
	});

	function showLabelName(){
		$(".loadinfo").each(function(index,el){
			var url = $(el).attr("loadurl");
			$(el).load(url);
		});
	}
	
	function addUSBKey(){
		$('#usbkeymodal').modal('show');
	}
	function saveUSBKey(){
		var str="";
		$("#usbkeymodal input[type='checkbox']:checked").each(function(){   
			     str+=$(this).val()+",";   
		  });
		 if(!str){
			 alert("没有可以分配的USBKey，请删除已经分配到部门的USBKey或者等待系统收集到新的USBKey信息！");
			 return;
		 }
		 $.post("${ctx}/json/usbkey/assign",
				  {
				    orgid:$("#orgid").val(),
				    usbkeyid:str
				  },
				  function(data,status){
					 var ri = eval(data);
				    	 alert("Data: " + ri.message);
				    	 location.reload(true);
	    	  		  }
		   );					
		  $('#usbkeymodal').modal('hide');
	}

</script>


</body>
</html>
