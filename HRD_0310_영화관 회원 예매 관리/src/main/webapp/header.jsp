<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ★ 중요 : DB 연결 파일 포함 + 변수선언 -->
<%@ include file = "dbcon.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 회원 예매 관리</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<!-- ★ 중요 : style.css 연결 -->
<link rel = "stylesheet" type = "text/css" href= "style.css">

</head>

<body>
	<header>
		<h2>영화관 회원&예매 관리 프로그램</h2>
	</header>
	<nav>
		<a href="insert.jsp">회원등록</a>
		<a href="insert2.jsp">영화예매_단일선택</a>
		<a href="insert3.jsp">영화예매_다중선택</a>
		<a href="select.jsp">회원별예매조회</a>
		<a href="select2.jsp">영화별예매조회</a>
		<a href="index.jsp">홈으로</a>
	</nav>
	
</body>
</html>