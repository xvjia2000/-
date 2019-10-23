<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传头像</title>
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/open-iconic/font/css/open-iconic-bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<!-- jquery.validate 校验提示样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/jquery/screen.css">
<script charset="utf-8" src="/resource/kindedi
tor/lang/zh-CN.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<form action="/article/head" method="post"
		enctype="multipart/form-data">

		<input type="hidden" name="id" value="${sessionScope.user.id}">

		<label for="exampleFormControlFile1">选择头像:</label><input type="file"
			name="file" class="form-control-file"> <input type="submit"
			class="btn btn-primary btn-lg btn-block" value="提交">

	</form>

</body>
</html>