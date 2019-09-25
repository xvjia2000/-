<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章管理</title>
</head>
<script type="text/javascript">
	function query() {
		url = "/article/selectsByAdmin?status="+$("[name = status]").val()
		$("#content-wrapper").load(url);
	}
	
	 $(function () {
		if (${article.status}=="") {
		$("[name='status']").val(9)
		}
		$("[name='status']").val(${article.status})
	})

	
</script>
<body>

	<div class="form-group form-inline">
		&emsp; <select name="status">
			<option id="9" value="9">全部</option>
			<option id="0" value="0">待审</option>
			<option id="1" value="1">已审</option>
			<option id="-1" value="-1">驳回</option>
		</select>&nbsp;
		<button onclick="query()">查找</button>
	</div>

	<table class="table table-striped table-bordered">
		<tr>
			<td>序号</td>
			<td>标题</td>
			<td>作者</td>
			<td>状态</td>
			<td>发布时间</td>
			<td>点击量</td>
			<td>热门</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${articles}" var="a" varStatus="i">
			<tr>
				<td>${i.index+1 }</td>
				<td>${a.title }</td>
				<td>${a.user.username }</td>
				<td>${a.status==0?"待审核":a.status==1?"已审核":"未通过" }</td>
				<td><fmt:formatDate value="${a.updated }" pattern="yyyy-MM-dd" /></td>
				<td>${a.hits }</td>
				<td>${a.hot==1?"是":"否"}</td>
				<td><a href="javascript:myOpen(${a.id })">文章详情</a></td>
			</tr>

		</c:forEach>
	</table>
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
			var url = "/article/selectsByAdmin?pageNum="+pageNum+"&status="+${article.status}; 
			  $("#content-wrapper").load(url);  
		}
		 
	}
	</script>
<script type="text/javascript">
	function myOpen(id){
		var url="/article/selectByAdmin?id="+id;
		//在新窗口打开
		window.open(url,"_blank");
		alert("asdf")
		$("#content-wrapper").load("/article/selectsByAdmin");
	}
	
</script>

</html>