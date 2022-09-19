<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>
<style>
	*{
		padding:0;
		margin:0;
	}
	body{
		width: 100vw;
		
	}
	.menu-box{
		margin-left:85%;
	}
	
	a{
		text-decoration: none;
		color:#aaa;
		padding: 10px 10px;
	}
	
	a:hover > .i-hover {
		color: #49d170;
	}
	
	h1, h2{
		text-align: center;
		margin-bottom: 0;
		color: #aaa;
		margin-top:20px;
	}
			
	.ok-check-box{
		display: flex;
		justify-content: center;
	}
		
	.commit-container{
		width: 80%;
		display: flex;
		flex-direction: column;
		margin:10px 10% 0;
		height: 700px;
		overflow-y: scroll;
	}
	
	.commit-box{
		padding: 12px 20px;
		border: 2px solid #49d170;
	}
	
	.commit-box:first-child {
		border-radius: 8px 8px 0 0;
	}
	
	.commit-box:not(:first-child) {
		border-top: none;
	}
	
	.commit-box:last-child {
		border-radius: 0 0 8px 8px;
	}
	
	.commit-date{
		border-bottom: 1px solid #aaa;
		margin-bottom: 6px;
		font-size: 14px;
	}
	
	.commit-box:hover{
		background-color: #49d170;
	}
	
	.commit-box:hover > div{
		color: white;
		font-weight: bold;
	}
	
	.commit-box:hover >.commit-date{
		border-bottom: 1px solid white;
	}
	
</style>
</head>
<body>
	<h1>Repo Detail Page</h1>
	<div class="menu-box">
		<a href="${rootPath}/zandi/git"><i class="fa-solid fa-list fa-lg i-hover">리스트 가기</i></a>
		<a href="${rootPath}/zandi"><i class="fa-solid fa-house fa-lg i-hover">홈 가기</i></a>
	</div>
	<div class="ok-check-box">
		<div class="ok-box">
			<c:if test="${TODAYOK == 'OK' }" >
				<div class="repo-ok">
			 		<span><strong>${REPONAME}</strong> 오늘의 커밋 완료</span>
			 	</div>
			</c:if>
			<c:if test="${TODAYOK == 'NO' }" >
				<div class="repo-ok">
			 		<span><strong>${REPONAME}</strong> 오늘의 커밋 미완료</span>
			 	</div>
			</c:if>
		</div>
	</div>
	
	<h2>Commit History</h2>
	<div class="commit-container">
		<c:forEach items="${GITLIST}" var="GITLIST">
			<div class="commit-box">
		 		<div class="commit-date">Date : ${GITLIST.commit.author.date}</div>
				<div>Content : ${GITLIST.commit.message}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>