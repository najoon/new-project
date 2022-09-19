<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
	
	.container{
		width:100%;
		height:100%;
	}
	.head-text{		
		text-align: center;
		margin-top: 4px;
	}
	
	.menu-container{
		display: flex;
		justify-content: space-between;
		margin: 20px auto 0;
	}
	.menu-container.box1{
		width:160px;	
	}
	.menu-container.box2{
		width:260px;	
	}
	
	a{
		color: #aaa;
		text-decoration: none;
		font-weight: 900;
	}
	
	img{
		width: 300px;
	
	}
	
	.img_div{
		width: 300px;
		margin:200px auto 0;
	}
	
	.ester{
		text-align: center;
		margin-top:20px;
	}
	
	.ester a{
		font-size: 12px;
	}
</style>
</head>

<body>
	<div class="container">
		<div class="img_div">
			<img src="${rootPath}/static/image/zandi.png">
		</div>
		<h1 class="head-text">Zandi Project</h1>
	
		<c:choose>
			<c:when test="${LAYOUT == 'NOT_LOGIN'}">
				<div class="menu-container box1">
					<a href="${rootPath}/user/join">회원가입</a>
					<a href="${rootPath}/user/login">로그인</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="menu-container box2">
					<a href="${rootPath}/git">레파지토리</a>
					<a href="${rootPath}/group">스터디</a>
					<a href="${rootPath}/user/logout">로그아웃</a>
				</div>
				<div  class="ester">
					<a href="${rootPath}/user/joinout">회원탈퇴</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>


</body>
	
</html>
