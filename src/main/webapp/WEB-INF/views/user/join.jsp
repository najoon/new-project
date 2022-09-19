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

	h1{
		text-align: center;
		margin-bottom: 0;
		color: #aaa;
		margin-top: 200px;
	}
	.input-container{
		display:  flex;
		flex-direction: column;
		width: 30%;
		margin: 10px auto 0;
		border-top: 1px solid #aaa;
		padding-top:20px;
	}
	
	.input-box{
		display: flex;
		margin: 0 auto 10px;
	}
	
	.input-box .label{
		width: 170px;
	}
	
	.input-box input{
		flex: 1;
	}
	button{
		margin: 10px auto 0;
		padding: 10px 20px;
		background-color: white;
		border: 1px solid  #49d170;
		border-radius: 8px;
		width: 340px;
		font-size: 20px;
		font-weight: 900;
	}
	
	button:hover{
		background-color: #49d170;
		border: 1px solid  #49d170;
		color: white;
	}
	
	a{
		margin-left: 63%;
		text-decoration: none;
		color:#aaa;
		padding: 10px 12px;
		margin-top:20px;
	}
	
	a:hover > .i-hover {
		color: #49d170;
	}
</style>
</head>
<body>
	<div class="main-container">
		<h1>Join</h1>
		<a href="${rootPath}/zandi"><i class="fa-solid fa-house i-hover"></i></a>
		<form method="POST" class="input-container">
					<div class="input-box">
						<div class="label">
							<label for="username">ID : </label>
						</div>
						<input name="u_username" placeholder="ID를 입력하세요" id="username">
					</div>
					<div class="input-box">
						<div class="label">
							<label  for="password">PASSWORD : </label>
						</div>
						<input name="u_password" type="password" id="password">
					</div>
					<div class="input-box">
						<div class="label"> 	
							<label for="re_password">PASSWORD CEHCK : </label>
						</div>
						<input name="re_password" type="password" id="re_password">
					</div>
					<div class="input-box">
						<div class="label">
							<label for="github_id">GITHUB ID : </label>
						</div>
						<input name="u_github_id" placeholder="github name을 입력하세요" id="github_id">
					</div>
					<div class="input-box">
						<div class="label">
							<label for="email">EMAIL : </label>
						</div>
						<input name="u_email" placeholder="email을 입력하세요" id="email">
					</div>
					<button>회원가입</button>
		</form>
	</div>
</body>
</html>