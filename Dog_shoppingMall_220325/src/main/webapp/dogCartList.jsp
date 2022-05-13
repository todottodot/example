<!-- 교재(p.762~768) 그림보고 기능(수량변경, 목록 전체 선택, 항목삭제, 감색범위설정 ..)을 파악하기 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%> <!-- isELIgnored="false" : 기본값 -->
    
<!-- JSTL사용 (prefix : 접두어 ) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#listForm {
   width: 640px;
   border: 1px red solid;
   margin: auto;
   
}

h2 {
   text-align: center;
}

table {
   width: 550px;
   margin: auto;
}

.tr_top {
   background-color: lime;
}

.div_empty {
   text-align: center;
   background-color:
}

.td_command {
   text-align: right;
}
#todayImageList{
   text-align: center;
}
#productImage{
   width:150px;
   height:150px;
   border:none;
}
#cartImage{
   width:70px;
   height:70px;
   border:none;
}
#select{
	text-align: right;
}
#commandList{
	text-align: center;
}
#upImage{
	width: 15px;
}
#downImage{
	width: 15px;
}
</style>

</head>

<script type="text/javascript">
function checkALL(theForm) {
	if(theForm.remove.length == undefined){ // 장바구니 항목을 선택하는 체크박스가 1개 일때(즉, 개 상품이 1개만 담겼을 때) length는 undefined가 된다.(이유? length는 배열에만 존재한다.)
		theForm.remove.checked = theForm.allCheck.checked; // 체크하면 true, 체크해제하면 false
	}else{ // 장바구니 항목을 선택하는 체크박스가 여러개일 때 (=배열객체(remove가 배열이됨)일때. 즉, 개상품이 2개이상 담겼을 때) (장바구니 객체가 여러개일 경우 배열객체로 전달받음 -> length가 존재함)
		for(var i=0; i<theForm.remove.length; i++){
			theForm.remove[i].checked = theForm.allCheck.checked; // 장바구니항목에 있는 remove체크박스 값들을 전체 체크
		}
	}
}
/*
  - encodeURI(문자열) : 인터넷 주소에서 사용하는 특정문자( :, ;, /, =, ?, &) 등을 제외하고 "UTF-8로 인코딩" 하는 함수
  - encodeURIComponent(문자열) : 모든 문자를 "UTF-8로 인코딩"하는 함수
  
  - decodeURI(encodeURI()로 인코딩된 문자열) : "encodeURI()로 인코딩한 문자열"을 디코딩하는 함수
  - decodeURIComponent(encodeURI()로 인코딩된 문자열) : "encodeURIComponent()로 인코딩한 문자열"을 디코딩하는 함수
 */
function checkQtyUp(kind) {
	location.href = "dogCartQtyUp.dog?kind=" + encodeURIComponent(kind);
	
}

 function checkQtyDown(kind, qty) {
	if(qty != 1){ // 현재 수량이 1보다 클때만 장바구니에 있는 현재수량을 감소시킬수 있음 (0은 존재하지 않음)
		location.href = "dogCartQtyDown.dog?kind=" + encodeURIComponent(kind);
	}
}
  
  
</script>

<body>

<!-- 검색에 사용되는 startMoney(=최하)값과 endMoney(=최고)값을 속성으로 설정한다.
     이때, 검색작업을 하지않고 그냥 장바구니 목록 보기 페이지가 실행된 경우는 이 값들이 null이기 때문에 -->
<c:if test="${startMoney != null }"><
	<c:set var="startMoney" value="${startMoney }" />
</c:if>

<c:if test="${endMoney != null }"><
	<c:set var="endMoney" value="${endMoney }" />
</c:if>

<section id="listForm">
                              <!-- session영역에 있는 cartList값을 가져옴 -->
<c:if test="${cartList != null && cartList.size()>0 }">
	<h2>장바구니 목록</h2>
	<form method="post" name="f"> <!-- action 제외한 이유 : 아래에서 요청을 각각 처리하기 위해서 -->
		<table>
			<tr id="select">
				<td colspan="6">
				<!-- name="startMoney" : 사용자가 선택한 startMoney값 -->
					<select id = "startMoney" name="startMoney">
						<option>=최하=</option>
						<!-- JSTL사용 (JSTL사이에 주석처리할때 주의하기) -->
						<!-- switch(case문 .. default) -->
						<c:choose>
							<c:when test="${startMoney == 1000 }">
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 2000 }">
								<option>1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 3000 }">
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney == 4000 }">
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:otherwise>
						</c:choose>
					</select>
					
					<select id = "endMoney" name="endMoney">
						<option>=최고=</option>
						<!-- JSTL사용 (JSTL사이에 주석처리할때 주의하기) -->
						<!-- switch(case문 .. default) -->
						<c:choose>
							<c:when test="${endMoney == 1000 }">
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 2000 }">
								<option>1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 3000 }">
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney == 4000 }">
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:otherwise>
						</c:choose>
					</select>
					
					<!-- [검색]버튼을 클릭하면 '최하 가격 ~ 최고 가격'으로 장바구니 항목을 검색하는 요청 -->
					<input type="submit" value="검색" formaction="dogCartSearch.dog">
				</td>
			</tr>
			<!-- [가격별 검색처리 끝] -->
			
			<tr class="tr_top">
				<td><!-- 전체 체크박스 : 체크하면 모든 장바구니 항목의 체크박스가 체크, 체크해제하면 모든 장바구니 항목의 체크박스가 체크 해제 -->
					<input type="checkbox" name="allCheck" onclick="checkALL(this.form)"><!-- this.form : 이 폼안에 있는 값을 전달함 -->
					<!-- <input type="checkbox" name="allCheck" onclick="checkALL(f)"> --> <!-- form에 name=f로 값을 줬을 경우 이렇게 작성해도 됨 -->
				</td>
				<td>번호</td>
				<td>상품 이미지</td>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>
			</tr>
			
			<!-- [JSTL] 향상된 for문 시작 ------------------------------------>
			<!-- session영역에 있는 cartList값을 가져와 cart변수에 저장 -->
			<c:forEach var="cart" items="${cartList }" varStatus="status">
				<tr>
					<!-- 삭제할 기준 : cart.kind(개품종= 개 상품명)  -->
					<td><input type="checkbox" name="remove" value="${cart.kind }"></td>
					<td>${status.index+1 }</td>
					<td><img src="images/${cart.image }" id="cartImage"></td>
					<td>${cart.kind }</td>
					<td>${cart.price }</td>
					<td>
						<!-- ▲ 클릭시 : 장바구니 항목의 수량 증가 요청(이때, "kind값"을 파라미터로 전송함) -->
						<!-- cart.encodingKind : Cart.java 안에 있는 private String kind;값 -->
						<!-- [방법1] -->
						<%-- <a href="dogCartQtyUp.dog?kind=${cart.kind }"><img src="images/up.jpg" id="upImage" border="0"></a> --%> <!-- border="0" : 테두리 삭제 -->
						<!-- [방법2] -->
						<a href="javascript:checkQtyUp('${cart.kind }')"><img src="images/up.jpg" id="upImage" border="0"></a>
						<br>
						<!-- 현재 수량 -->
						${cart.qty }
						<br>
						<!-- ▼ 클릭시 : 장바구니 항목의 수량 감소 요청하기 위해 checkQty()함수 호출 (이때, "kind값, 현재 수량"을 매개값으로 전송해야 함-->
						<a href="javascript:checkQtyDown('${cart.kind }', '${cart.qty }')"><img src="images/down.jpg" id="downImage" border="0"></a>
					</td>
				</tr>
			</c:forEach>
			<!-- [JSTL] 향상된 for문 끝 ------------------------------------->
			<tr>
				<td colspan="6" align="center">총 금액 : ${totalMoney}원</td> <!-- totalMoney : request객체안에 있으므로 바로 출력 가능 -->
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="submit" value="삭제" formaction="dogCartRemove.dog"> </td>
			</tr>
		</table>
	</form>
</c:if>

<!-- [장바구니 목록이 비어있을 경우]------------------------------------------------------------- -->
<c:if test="${cartList == null}">
	<section class="div_empty">장바구니가 비어있습니다.</section>
</c:if>
<!-- ------------------------------------------------------------------------------------ -->
<nav id="commandList">
	<!-- 개 상품 목록보기 요청 -->
	<a href="dogList.dog">쇼핑계속하기</a>
</nav>
</section>






</body>
</html>