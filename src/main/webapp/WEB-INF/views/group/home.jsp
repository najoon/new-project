<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js"></script>
<style>

	*{
		box-sizing: border-box;
		list-style: none;
		margin:0;
		padding:0;
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
	
	h1{
		margin-top:20px;
		text-align: center;
	}
	
	.zandi-container{
		width: 74%;
		margin:0 auto;
	}
	
	.zandi-username{
		text-align: center;
	}
	.zandi-box{
		display: flex;
		justify-content: center;
		margin:6px 0 6px; 
	}
	
	.zandi-img{
		margin-left: 6px;
	}
	
	.container-box{
		width:74%;
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		margin: 6px auto 0;
		border-top:1px solid #aaa;
	}
	.in-box{
		padding:14px 16px;
		background-color: #49d170;
		border: 2px solid #49d170;	
		border-radius: 8px;
		margin:10px 10px 0 0;
		width: 272px;
	}
	
	.full-in{
		text-align: center;
		color:red;
	}
	
	.no-study-now{
		margin: 20px auto 0;
	}
	

	
	.study-title{
		font-weight: bold;
	}
	
	.study-detail{
		margin-top:6px;
		display: flex;
	}
	
	.study-detail div:first-child{
		margin-right:26px;
	}
	
	.in-box:hover{
		background-color: white;
		box-shadow: 3px 3px 3px #1b6931;
	}
	.in-box:hover > div{
		color: #49d170;
		font-weight: bold;
	}
	
	.form-box{
		width:500px;
		margin:50px auto 0;
		border: 2px solid #49d170;
		border-radius: 8px;
	}
	
	
	.input-container{
		width:72%;
		margin: 0 auto;
	}
	
	input[name="g_name"]{
		width:250px;
		margin-bottom:6px;
	}
	
	input[name="g_people"]{
		margin-right: 23px;
		width: 60px;	
	}
	input[name="end_date"]{
		width: 130px;	
	}
	
	button{
		width: 100%;
		border: 1px solid #49d170;
		background-color: white;
		padding:6px 0;
		border-radius: 8px;
		margin: 16px 0 30px;
	}
	
	button:hover{
		background-color: #49d170;
		color: white; 
		font-weight: bold;
	}
	
	.form-title{
		text-align: center;
		border-bottom: 1px solid #49d170;
		margin:20px 64px 30px;
		font-size: 20px;
		font-weight: bold;
	}
	
	
	
</style>
</head>
<body>
	<div class="wrap-container">
		<h1>STUDY LIST</h1>
		<a href="${rootPath}/"><i class="fa-solid fa-house fa-lg i-hover"></i></a>
		<div class="zandi-container">
			<div class="zandi-username">${USER}님의 잔디</div>
			<div  class="zandi-box">
				<c:forEach items="${TROPHY}" var="TROPHY">
						<img  class="zandi-img" src="${rootPath}/static/image/trophy.png"
						title="${TROPHY.t_groupname}의 잔디" alt=" ${TROPHY.t_groupname} 트로피">
				</c:forEach>
			</div>
		</div>
		
			<c:if test="${not empty FULLIN}">
				<div class="full-in">
					<div>${FULLIN}의 입장 정원이 초과했습니다</div>
				</div>
			</c:if>	
		<div class="container-box">
			<c:if test="${ empty GROUPLIST}">
				<div class="no-study-now">
					<div>생성된 스터디가 없습니다. 만들어 볼까요?</div>
				</div>
			</c:if>
			<c:forEach items="${GROUPLIST}" var="GROUP">
					<div class="target in-box" data-seq="${GROUP.g_seq}">
						<div class="study-title"><i class="fa-sharp fa-solid fa-pen"></i> : ${GROUP.g_name}</div>
						<div class="study-detail">
							<div><i class="fa-solid fa-user"></i> : ${GROUP.g_inpeople} / ${GROUP.g_people}</div>
							<c:choose>
								<c:when test="${empty GROUP.period}">
									<div>
										<i class="fa-solid fa-clock"></i> : D-DAY
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<i class="fa-solid fa-clock"></i> : D-${GROUP.period}
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
			</c:forEach>
		</div>		
		
		<form class="form-box" method="POST" >
				<div class="form-title">Create Study</div>
				<div class="input-container">
					<div>
						<label>스터디 이름 : </label>
						<input name="g_name" placeholder="생성할 그룹 이름을 입력하세요">
					</div>
					<div>
						<label>인원 제한 : </label>
						<input name="g_people" type="number"  value="4" min="4" max="20" >
						<label>기간 : </label>
						<input name="end_date" type="date" value="2022-09-30" >
					</div>
					<button>CREATE</button>
				</div>
		</form>
	</div>
</body>
<script>
	const rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/group.js?var=2022-09-06-002"></script>
</html>