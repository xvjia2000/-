<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

	<a class="navbar-brand mr-1" href="index.html">CMS系统后台</a>
	<!-- Navbar Search -->
	<form
		class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search for..."
				aria-label="Search" aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary" type="button">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</div>
	</form>

	<!-- Navbar -->
	<ul class="navbar-nav ml-auto ml-md-0">
		<li class="nav-item"><a class="nav-link" href="/my/home"> <img
				alt="" src="/resource/img/RE`ZFG9D]9J`I1JO1[(SFW6.png"
				style="max-height: 2.5rem" class="rounded img-fluid">
		</a></li>
		<li class="nav-item">
			<div class="dropdown" style="padding-top: 0.4rem;">
				<a href="#" class="nav-link dropdown-toggle" role="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <c:out
						value="${sessionScope.admin.nickname}" default="CMS-User" />
				</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="/">返回网站</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/passport/logout">退出</a>
				</div>
			</div>
		</li>
	</ul>

</nav>