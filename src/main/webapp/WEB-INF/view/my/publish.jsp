<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>

<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form id="form1">

		<input type="hidden" value="${sessionScope.user.id}" id="userId"
			name="userId" /> 文章标题：<input type="text" name="title"
			class="form-control"> <br />


		<textarea name="content1" cols="100" rows="8"
			style="width: 700px; height: 200px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br /> 文章栏目：<select class="custom-select mr-sm-2" name="channelId" id="channel">
			<option value="0">选择栏目</option>
		</select> 文章分类：<select class="custom-select mr-sm-2" name="categoryId" id="category">
			<option value="0">选择分类</option>
		</select> <br> <br> 标题图片:<input type="file" name="file" class="form-control-file"> <br>
		<br>
	</form>
	<input type="button" name="button" value="提交内容" onclick="publish()"
		class="btn btn-light" />


</body>
<script type="text/javascript">
	/* 发布文章*/

	function publish() {
		// 序列化带文件表单数据
		var formData = new FormData($("#form1")[0])
		//将富文本的内容封装到formData里面
		formData.set("content", editor1.html())

		$.ajax({
			type : "post",
			data : formData,userId,
			//告诉jQuery不要去处理发送的数据
			processData : false,
			//告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url : "article/publish",
			success : function(data) {
				if (data) {
					alert("成功")
					$("#center").load("/article/selectsByUser?userId=${sessionScope.user.id}");
				} else {
					alert("失败")
				}
			}

		})

	}

	$(function() {
		$.get("/channel/selects", function(data) {
			for ( var i in data) {
				$("#channel").append(
						"<option value='"+data[i].id+"'>" + data[i].name
								+ "</option>")
			}
		});
		$("#channel").change(
				function() {
					var cid = $(this).val();
					//先清空已有的分类
					$("#category").empty();
					//根据栏目去查询分类的内容
					$.get("/category/selects", {
						cid : cid
					}, function(list) {
						for ( var i in list) {
							$("#category").append(
									"<option value='"+list[i].id+"'>"
											+ list[i].name + "</option>")
						}

					})
				})
	})
</script>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>