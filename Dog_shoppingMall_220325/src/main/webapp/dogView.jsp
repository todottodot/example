<!-- 개 하나의 상세정보 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#listForm{
		width:640px;
		border:1px solid red;
		margin:auto;
	}
	h2{
		text-align:center;
	}
	img{
		width: 280px;
		height: 280px;
		border: none;
	}
	#content_main{
		height:300px;
	}
	#content_left{
		width: 300px;
		float:left;
	}
	#content_right{
		width: 340px;
		float:left;
	}
	#commandList{
		text-align: center;
	}
	#desc{
		height:170px;
		background: skyblue;
	}
</style>

</head>
<body>
<!-- 교재 p.752 두번째 그림 -->
<section id="viewForm">
	<h2>${dog.kind }의 상세정보</h2>
	<section id="content_main">
		<section id="content_left">
			<img src="images/${dog.image }">
		</section>
		<section id="content_right">
			<b>품종 : </b>${dog.kind }<br>
			<b>가격 : </b>${dog.price }<br>
			<b>신장 : </b>${dog.height }<br>
			<b>체중 : </b>${dog.weight }<br>
			<b>원산지 : </b>${dog.country }<br>
			<p id="desc">
				<b>내용 : </b>${dog.content }<br>
			</p>
		
		</section>
	</section>
	
	<div style="clear:both" ></div>
	
	<nav id="commandList">
		<a href="dogList.dog">쇼핑 계속하기</a>
		
		<!-- 이때, 해당 개를 구분할 수 있도록 id값을 파라미터로 전송해야한다. -->
		<a href="dogCartAdd.dog?id=${dog.id }">장바구니에 담기</a>
	</nav>
</section>

</body>
</html>