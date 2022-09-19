<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.error-message{
		color:red;
		text-align: center;
	}
	
	h1{
		text-align: center;
		margin-bottom: 0;
		color: #aaa;
		margin-top: 200px;
	}
	.form-box{
		display: flex;
		flex-direction: column;
		width: 500px;
		margin: 10px auto 0;
		border-top: 1px solid #aaa;
	}
	
	button{
		margin-top:10px;
		padding:6px 10px;
		width: inherit;
		background-color: white;
		color:#49d170;
		border: 1px solid #49d170;
		border-radius: 8px;
		font-size: 20px;
		font-weight: 900;
	}
	
	button:hover{
		background-color: #49d170;
		color: white;
	}
	
	.input-box input{
		width:  492px;
		margin-top: 10px;
		height: 30px;
	}

</style>
</head>
<body>
		<h1>로그인</h1>
	<form method="POST" class="form-box">
				<c:if test="${error == 'LOGIN_FAIL'}">
					<div>
						<div class="error-message">아이디나 비밀번호가 틀렸습니다</div>
					</div>
				</c:if>
			<div class="input-box">
				<input name="u_username" placeholder="아이디" id="id">
				<input name="u_password" placeholder="비밀번호" type="password" id="pass">
			</div>
			<button>로그인</button>
	</form>
</body>
</html>