<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title}</title>
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">

<!-- Custom styles for this template-->
<link href="/resource/css/sb-admin.css" rel="stylesheet">
<!-- use the jQuery resource in html/jsp page -->
</head>
<script
	src="${pageContext.request.contextPath}/resource/js/jquery-1.8.2.min.js"></script>
<body>

	<div class="container">
		<dl>
			<dt>
				<h1 align="center">${article.title}</h1>
			</dt>
			<dd>${article.user.nickname}
				&emsp;
				<fmt:formatDate value="${article.updated }"
					pattern="yyyy-MM-dd hh:mm:ss" />
			</dd>
			<dd style="float: right;">
				<button type="button" class="btn btn-success"
					onclick="update(${article.id},1)"
					${article.status==1?"disabled":"" }>通过</button>
				<button type="button" class="btn btn-warning"
					onclick="update(${article.id},-1)"
					${article.status==-1?"disabled":""}>驳回</button>
			</dd>
			<dd>
				<hr />
			</dd>
			<dd>${article.content }</dd>
		</dl>
		
	</div>
</body>
<script type="text/javascript">
	
	function update(id,status){
		$.post("/article/update",{id:id,status:status},function(flag){
			if(flag){
				alert("操作成功!");
				
				 window.close();//关闭当前页面 
			}else{
				alert("操作失败!")
			}
		})
		
	}
</script>
</html>