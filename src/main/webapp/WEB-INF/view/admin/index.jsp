<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CMS后台管理系统</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">

<!-- Custom styles for this template-->
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/cms.css" rel="stylesheet">
<link href="/resource/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">
	<!-- 后台管理系统顶部 -->
	<jsp:include page="top.jsp" />

	<div id="wrapper">
		<!-- 后台管理系统左部菜单 -->
		<jsp:include page="left.jsp" />
		<!-- 中间内容显示区域 -->
		<div id="content-wrapper">
			
		
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/resource/js/jquery-1.8.2.min.js"></script>

	<script type="text/javascript">
		$(function(){
			$(".nav-link").click(function(){
				var url = $(this).attr("data");
				//在中间区域加载url
				$("#content-wrapper").load(url);
			})
		})
		
		
		
	</script>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>