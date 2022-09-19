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
		margin: 0;
		padding:0;
		display: inline-block;
		width: 100%;
	}
	
	h1, h2{
		text-align: center;
		margin-bottom: 0;
		margin-top:30px;
		margin-bottom: 10px;
	}
	
	.menu-box{
		margin-left:82%;
	}
	
	a{
		text-decoration: none;
		color:#aaa;
		padding: 10px 10px;
	}
	
	a:hover > .i-hover {
		color: #49d170;
	}
	
	.study-info div{ 
		text-align: center;
	}
	
	.container-wrap{
		width: 80%;
		display: flex;
		margin: 0 auto;
	}	
	
	.peopleList-container{
		width: 280px;
		margin-right: 30px;
	}
	
	.peopleList-container .people-name{
		width:140px;
	}
	
	.peopleList-box{
		border: 2px solid #49d170;
		border-radius: 8px;
		padding: 10px;
		
	}
	
	.peopleList-box .people-box-list{
		display: flex;
		justify-content: flex-start;
		border-top: 1px solid #aaa;
	}
	
	.peopleList-box .people-box-list:first-child{
		font-size: 18px;
		font-weight: 800;
		text-align: center;
		border-top: none;
	}
	
	.peopleList-box .people-box-list div{
		padding: 10px 12px;
	}
	
	.peopleList-box .people-box-list div:first-child{
		width: 140px;
	}
	
	.peopleList-box .people-box-list div:last-child{
		flex: 1;
	}
	
	.comment-container{
		flex: 1;
	}
	
	.comment-list{
		overflow-y: scroll;
		height: 500px;
	}
	
	.comment-box{
		border: 2px solid #49d170;
		border-radius: 8px;
		padding: 12px 20px;
		margin-bottom: 10px;
	}
	
	.comment-not{
		border: 1px solid #aaa;
		border-radius: 8px;
		padding: 12px 20px;
		margin-bottom: 10px;
	}
	
	.not-comment{
		text-align: center;
		font-weight: bold;
		font-size: 20px;
	}
	
	.comment-title{
		display: flex;
	}
	.comment-title div:first-child{
		width: 180px;
		margin-bottom:8px;
	}
	.comment-title div:last-child {
		margin-left: 30px;
		font-size: 12px;
		margin-top:4px;
	}
	
	.form-comment{
		margin-top: 20px;
	}
	
	
	textarea{
		margin: 10px 6px 0 6px;
		border-radius: 8px;
	}
	button{
		padding: 6px 10px;
		background-color: white;
		border:1px solid #49d170;
		color: #49d170;
		border-radius: 8px;
	}
	
	button:hover{
		color: white;
		background-color: #49d170;
	}
</style>
</head>
<body>

	<h1>${GROUP.g_name} Study Room</h1>
	<div class="menu-box">
		<a href="${rootPath}/group/out/${GROUP.g_seq}"><i class="fa-sharp fa-solid fa-door-open fa-lg i-hover" >스터디 탈퇴하기</i></a>
		<a href="${rootPath}/group/list"><i class="fa-solid fa-list fa-lg i-hover">리스트 가기</i></a>
		<a href="${rootPath}/"><i class="fa-solid fa-house fa-lg i-hover">홈 가기</i></a>
	</div>
	<div class="study-info">
		<c:choose>
			<c:when test="${DDAY == DAY}"><div>스터디방의 남은 기간 :<strong>D-DAY</strong></div></c:when>	
			<c:otherwise><div>스터디방의 남은 기간 : <strong>D-${DDAY}</strong></div></c:otherwise>
		</c:choose>
		<div>스터디 기간안에 출석률 <strong>80%</strong> 이상 달성하면 ${GROUP.g_name}잔디가 생겨요!</div>
	</div>
	<div class="container-wrap">
		<div class="peopleList-container">
			<h2>Participants : ${GROUP.g_inpeople} / ${GROUP.g_people}</h2>
			<div class="peopleList-box">
				<div class="people-box-list">
					<div>이름</div>
					<div>출석률</div>
				</div>
				<c:forEach items="${PEOPLELIST}" var="PEOPLE">
					<div class="people-box-list">
						<div>${PEOPLE.j_username}</div>
						<div >${PEOPLE.j_percent}%</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="comment-container">	
			<h2>Comment List</h2>
				<div class="comment-list">
					<c:if test="${empty COMMENT}">
						<div class="comment-not">
							<div class="not-comment">not recoded comment</div>
						</div>	
					</c:if>
					<c:forEach items="${COMMENT}" var="COMMENT">
						<div class="comment-box">
							<div class="comment-title">
								<div>${COMMENT.c_username}님의 Comment</div>
								<div>${COMMENT.c_date} ${COMMENT.c_time}</div>
							</div>
							<div class="comment-content">
								<div>Comment: ${COMMENT.c_comment}</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<form class="form-comment" method="POST">
					<fieldset>
						<legend>Create Comment</legend>
							<textarea name="c_comment" placeholder="내용을 입력하세요" cols="156" rows="6"></textarea>
							<button>작성</button>
					</fieldset>
				</form>
		</div>
	</div>
</body>
</html>