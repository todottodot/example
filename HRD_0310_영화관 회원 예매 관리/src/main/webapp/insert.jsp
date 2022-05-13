<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 회원 예매 관리</title>
</head>

<script type="text/javascript">
//유효성 검사
function check(){ 
	
	if(f.me_id.value == "") {  
		alert("아이디를 입력해주세요.");
		return f.me_id.focus(); 
	}
	if(f.me_pass.value == "") { 
		alert("비밀번호를 입력해주세요.");
		return f.me_pass.focus(); 
	}
	if(f.me_name.value == "") { 
		alert("회원이름을 입력해주세요.");
		return f.me_name.focus(); 
	}
	// ★ 주의 : radio, checkbox
	if(f.gender[0].checked == false && f.gender[1].checked == false) { // f.gender[0].checked : 남자를 선택하면 true , f.gender[1].checked : 여자를 선택하면 true
		alert("성별을 선택해주세요.");
		return; // ★ 주의 : text가 아니기때문에 포커스를 둘수 없음
	}
	if(f.birth.value == "") { 
		alert("생년월일을 입력해주세요.");
		return f.birth.focus(); 
	}
	if(f.address.value == "") { 
		alert("주소를 입력해주세요.");
		return f.address.focus(); 
	}
	if(f.tel.value == "") { 
		alert("전화번호를 입력해주세요.");
		return f.tel.focus(); 
	}
	f.submit();
}
</script>

<body>
	<%@ include file = "header.jsp" %>  
	<section>
	<img src="images/movie.png">
		<div>
			<h2>회원등록</h2>
		</div>
		<form action ="insertPro.jsp" name = "f" method = "post">
				<table style = "margin: 0 auto" border = "1" width = "500">
					<tr>
						<th>아이디</th>
						<td>
							<input type = "text" name = "me_id" value = "" size = "20" maxlength = "12" >
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type = "password" name = "me_pass" value = "" size = "20" maxlength = "15">
						</td>
					</tr>
					<tr>
						<th>회원이름</th>
						<td>
							<input type = "text" name = "me_name" value = "" size = "20" maxlength = "20">
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							&nbsp;&nbsp;<label><input type = "radio" name = "gender" value = "M"> 남자</label>
						    &nbsp;&nbsp;<label><input type = "radio" name = "gender" value = "F"> 여자</label>
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input type = "text" name = "birth" value = "" size = "20" maxlength = "8">
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type = "text" name = "address" value = "" size = "20" maxlength = "100">
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input type = "text" name = "tel" value = "" size = "20" maxlength = "13">
						</td>
					</tr>
					<tr>
						<th colspan = "2">
						    <input type ="submit" value ="회원등록" onclick = "check(); return false">
						    <input type ="reset" value ="다시쓰기" >
					   </th>
					</tr>
				</table>
			</form>
	</section>
	<%@ include file = "footer.jsp" %>
</body>
</html>