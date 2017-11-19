<%@ page language="java" pageEncoding="UTF-8"%>

<!-- topbar starts -->
<div class="navbar">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse"> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a> <a class="brand" href="/" style="width: 300px;"><span><fmt:message
						key="system.product" /></span></a>

			<!-- user dropdown starts -->
			<div class="btn-group pull-right">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i><span class="hidden-phone"> <shiro:principal /></span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/web/user/changepwd">修改密码</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/logout?token=${token}">退出</a></li>
				</ul>
			</div>
			<!-- user dropdown ends -->

			<div class="top-nav nav-collapse">
				<ul class="nav">
					<!--li><a href="#">Visit Site</a></li>
						<li>
							<form class="navbar-search pull-left">
								<input placeholder="Search" class="search-query span2" name="query" type="text">
							</form>
						</li-->
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
<!-- topbar ends -->