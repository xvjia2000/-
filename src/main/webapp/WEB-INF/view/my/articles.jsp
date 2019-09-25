<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的文章列表</title>
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- Custom styles for this template-->
<link href="/resource/css/sb-admin.css" rel="stylesheet">
</head>
<!-- use the jQuery resource in html/jsp page -->
<script
	src="${pageContext.request.contextPath}/resource/js/jquery-1.8.2.min.js"></script>


<body>
	<ul class="list-unstyled">
		<c:forEach items="${articles }" var="a">
			<a href="javascript:myOpen(${a.id })"
				style="text-decoration: none; color: #4f4f4f;">
				<li class="media form-group"><img src="/pic/${a.picture }"
					class="mr-3" alt="nopic" style="width: 120px; height: 80px">
					<div class="media-body">
						<h3 class="mt-0 mb-1">${a.title }</h3>
						<h5>${a.user.nickname }&emsp;${a.updated}</h5>
					</div></li>
			</a>
		</c:forEach>
	</ul>
	<div>
		<button onclick="page(${page.prePage })" class="btn btn-light">上一页</button>

		<c:forEach end="${page.pages}" step="1" begin="1" var="pageNum">
			<c:choose>
				<c:when test="${pageNum==page.pageNum }">
					<a onclick="page(${pageNum })" style="color: red; cursor: pointer;">${pageNum }</a>
				</c:when>
				<c:otherwise>
					<a onclick="page(${pageNum })" style="cursor: pointer;">${pageNum }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<button onclick="page(${page.nextPage })" class="btn btn-light">下一页</button>
	</div>
</body>
<script type="text/javascript">
	function page(pageNum) {
		if (pageNum==0) {
			alert("别点了到底了")
			
		}else{
			var url = "/article/selectsByUser?userId=${sessionScope.user.id}&pageNum="+pageNum; 
			  $("#center").load(url);  
		}
	}
	</script>
<script type="text/javascript">
	function myOpen(id) {
		var url = "/article/selectByUser?id=" + id;
		//在新窗口打开
		window.open(url, "_blank");

	}
</script>
</html>