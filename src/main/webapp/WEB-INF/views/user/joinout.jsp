<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		height: 100%;
	}
	
	a{
		text-decoration: none;
		color: #aaa;
	}
	
</style>
</head>
<body>
	<div class="box">
		<img src="${rootPath}/static/image/esteregg.jpg" title="회원탈퇴? 그딴거 없다!" alt="탈퇴 같은건 없다">
		<a href="${rootPath}/">홈 가기</a>
	</div>

</body>
</html>