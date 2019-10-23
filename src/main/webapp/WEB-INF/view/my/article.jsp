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

<script type="text/javascript">
		function pre() {
			$.get("/article/checkPre",
					{id:${article.id},
				channelId:${article.channelId}},
					function(obj){
				if (obj) {
					location.href = "/article/selectPre?id=${article.id}&channelId=${article.channelId}"
				}else{
					alert("到头了 点鸡毛啊")
				}
				})
		}
		
		function next() {
			$.get("/article/checkNext",
					{id:${article.id},
				channelId:${article.channelId}},
					function(obj){
				if (obj) {
					location.href = "/article/selectNext?id=${article.id}&channelId=${article.channelId}"
				}else{
					alert("到头了 点鸡毛啊")
				}
				})
		}
		
		
	
	</script>
<body>

	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>


	<div class="container">
		<div>
			<a
				<a href="/">首页 </a> / <a href="/?channelId=${article.channel.id }">
				${article.channel.name}</a> / <a href="/?channelId=${article.channel.id }&categoryId=${article.category.id}"> ${article.category.name} </a>
				/ 正文
		</div>
		<br>
		<div class="col-md-10 split min_h_500" style="float: left;">
			<br>
			<div style="float: left">
				<button class="btn btn-outline-secondary" onclick="pre()">上一篇</button>
			</div>
			<div style="float: right;">
				<button class="btn btn-outline-secondary" onclick="next()">下一篇</button>
			</div>
			<br>
			<dl>
				<dt>
					<h1 align="center">${article.title}</h1>
				</dt>
				<dd>${article.user.nickname}
					&emsp;
					<fmt:formatDate value="${article.updated }"
						pattern="yyyy-MM-dd hh:mm:ss" />
				</dd>
				<dd style="float: right;"></dd>
				<dd>
					<hr />
				</dd>
				<dd>${article.content }
					<hr>
				</dd>

			</dl>
			<div>

				<c:if test="${sessionScope.user ==null}">
					<span>请<a href="/passport/login" style="color: red;"> 登陆
					</a>后评论 <br>
					</span>
					<br>
					<br>
				</c:if>
				<c:if test="${sessionScope.user !=null}">
					<form id="form2">
						<input type="hidden" name="user_id"
							value="${sessionScope.user.id}"> <input type="hidden"
							name="article_id" value="${article.id}">
						<textarea rows="10" cols="125" name="content" id="content"></textarea>
					</form>
					<button onclick="insert()" class="btn btn-primary">评论</button>
				</c:if>
				</form>
			</div>

			<div>
				<dl>
					<c:forEach items="${comments}" var="c">
						<dt>${c.users.nickname }&nbsp;
							<fmt:formatDate value="${c.created }"
								pattern="yyyy-MM-dd hh:mm:ss" />
						</dt>
						<dd>${c.content }</dd>
						<hr>
					</c:forEach>
				</dl>


				<div>
					<button onclick="page(${page.prePage })" class="btn btn-light">上一页</button>

					<c:forEach end="${page.pages}" step="1" begin="1" var="pageNum">
						<c:choose>
							<c:when test="${pageNum==page.pageNum }">
								<a onclick="page(${pageNum })"
									style="color: red; cursor: pointer;">${pageNum }</a>
							</c:when>
							<c:otherwise>
								<a onclick="page(${pageNum })" style="cursor: pointer;">${pageNum }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<button onclick="page(${page.nextPage })" class="btn btn-light">下一页</button>
				</div>
				<script type="text/javascript">
				function page(pageNum) {
					if (pageNum==0) {
						alert("别点了到底了")
						
					}else{
						location.href = "/article/selectByUser?id=${article.id}&pageNum="+pageNum; 
						   
					}
				}
			</script>
			</div>
		</div>


		<div class="col-md-2" style="float: right;">
			<div class="card" style="width: 18rem;">
				<div class="card-header">点击排行榜</div>
				<ul class="list-group list-group-flush">
					<c:forEach items="${hitarticle }" var="l" varStatus="i">
						<li class="list-group-item"><strong>${i.index+1 }.</strong> <a
							href="/article/selectByUser?id=${l.id }" target="_blank">${l.title }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-md-2" style="float: right;">
			<div class="card" style="width: 18rem;">
				<div class="card-header">评论排行榜</div>
				<ul class="list-group list-group-flush">
					<c:forEach items="${countsarticle }" var="l" varStatus="i">
						<li class="list-group-item"><strong>${i.index+1 }.</strong><a
							href="/article/selectByUser?id=${l.id }" target="_blank">${l.title }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div>
			<br /> <br />
		</div>
</body>
<script type="text/javascript">
	function insert() {
		var a = $("#form2").serialize();
		$.post("/article/insert", a, function(data) {
			if (data) {
				alert("成功")
				location.reload();
			}
		})
	}
	
	//jquery前端验证
	$(function(){
		$("#form2").validate({
			rules:{
				
				content:{
					required:true,
				}
			},
			messages:{
				content:{
					required:"评论不能为空",
				}
			}
		})
	})
</script>
</html>