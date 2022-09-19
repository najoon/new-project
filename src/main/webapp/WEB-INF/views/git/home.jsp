<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>
<title>Insert title here</title>
<style>
	
	body{
		width: 100vw;
		overflow-x: hidden;
		margin:0;
		padding:0;
	}
	
	h1, h2{
		text-align: center;
		margin-bottom: 0;
		color: #aaa;
	}
	
	h1{
		margin-top:20px;
	}
	
	a{
		margin-left: 85%;
		text-decoration: none;
		color:#aaa;
		padding: 10px 12px;
	}
	
	a:hover > .i-hover {
		color: #49d170;
	}
	
	.repo-container{
		width:74%;
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		margin: 6px auto 0;
		border-top:1px solid #aaa;
	}
	.repo-box{
		padding:10px 16px;
		background-color: #49d170;
		border: 2px solid #49d170;	
		border-radius: 8px;
		margin:10px 10px 0 0;
		width: 236px;
	}
	
	.repo-box:hover{
		background-color: white;
		box-shadow: 3px 3px 3px #1b6931;
	}
	.repo-box:hover > span{
		color: #49d170;
		font-weight: bold;
	}
	.repo-box span{
		color:white;
		font-weight: 900;
	}
	
	form{
		width: 300px;
	}
	
	.zandi-img{
		width: 70%;
		margin:0 auto;
		margin-top:20px;
	}
	.zandi-img img{
		margin: 0 auto;
		width: 100%;
	}
	
</style>
</head>
<body>
	<h1>My Zandi</h1>
	<a href="${rootPath}/"><i class="fa-solid fa-house fa-lg i-hover"></i></a>
	<div class="zandi-img">
		<img src="${IMAGE}" />
	</div>
	<h2>My RepoList</h2>
	<c:if test="${not empty REPONAME}">
		<div class="repo-container">
			<c:forEach items="${REPONAME}" var="REPO" varStatus="INDEX">
				<div class="target repo-box" data-seq="${INDEX.count}">
					<span>${INDEX.count}.</span>
					<span>${REPO.name}</span>
				</div>
			</c:forEach>
		</div>
	</c:if>
	

</body>
<script>
	const rootPath = "${rootPath}"
</script>

<script src="${rootPath}/static/js/repo.js?var=2022-08-25-002"></script>
</html>