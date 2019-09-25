<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<script type="text/javascript">
	function query() {
		var url = "/user/selects?name="+$("[name = name]").val();
		$("#content-wrapper").load(url);
	}
	function page(pageNum) {
		if (pageNum==0) {
			alert("别点了到底了")
			
		}else{
			var url = "/user/selects?pageNum="+pageNum; 
			  $("#content-wrapper").load(url);  
		}
		 
	}
</script>
<body>

	<div class="form-inline col-form-label">
		&emsp;<input class="form-control form-control-sm" " type="text"
			name="name" placeholder="用户名" value="${name}">&nbsp;
		<button onclick="query()" class="btn btn-light">查找</button>
	</div>


	<table class="table table-striped table-bordered">
		<tr>
			<td>编号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>注册时间</td>
			<td>用户状态</td>
		</tr>
		<c:forEach items="${users }" var="user" varStatus="i">
			<tr>
				<td>${i.index+1 }</td>
				<td>${user.username}</td>
				<td>${user.nickname}</td>
				<td><fmt:formatDate value="${user.birthday}"
						pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${user.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:if test="${user.locked==0 }">
						<c:if test="${user.role==1 }">
							<p style="color: red;">
								<strong>管理员</strong>
							</p>
						</c:if>
						<c:if test="${user.role==0 }">
							<button class="btn btn-success"
								onclick="update(${user.id},${user.locked})">正常</button>
						</c:if>
					</c:if> <c:if test="${user.locked==1 }">
						<button class="btn btn-warning"
							onclick="update(${user.id},${user.locked})">禁用</button>
					</c:if></td>
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
	function update(id,locked) {
		$.post("/user/updateLocked",{
			id:id,
			locked:locked
		},function(data){
			$("#content-wrapper").load("/user/selects?pageNum="+${page.pageNum});
		})
	}
</script>

</html>