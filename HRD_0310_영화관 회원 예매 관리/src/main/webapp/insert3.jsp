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
		alert("회원 아이디를 입력해주세요.");
		return f.me_id.focus(); 
	}
	if(f.me_pass.value == "") { 
		alert("회원 비밀번호를 입력해주세요.");
		return f.me_pass.focus(); 
	}
	if(f.mo_name.value == "") { 
		alert("영화제목을 입력해주세요.");
		return f.mo_name.focus(); 
	}
	if(f.rm_date.value == "") { 
		alert("예매일시를 입력해주세요.");
		return f.rm_date.focus(); 
	}
	f.submit();
}
</script>

<body>
	<%@ include file = "header.jsp" %>
	<%
	
	sql = "SELECT to_char(sysdate,'yyyy-mm-dd') AS rm_date FROM dual";
	rs = stmt.executeQuery(sql);//sql을 DBMS로 넘겨서 컴파일함->속도가 느림
	rs.next();
	String rm_date = rs.getString("rm_date");
	
	
	sql = "SELECT mo_name FROM movie_3";
	rs = stmt.executeQuery(sql);
	if(!rs.next()){
		// Systemout.println("등록된 영화가 없습니다."); // 콘솔에 출력
	%>
	<script type="text/javascript">
		alert("등록된 영화가 없습니다.");
	</script>
	<%	
	}
	
	%>  
	<section>
	<img src="images/movie.png">
		<div>
			<h2>영화예매</h2>
		</div>
		<form action ="insertPro3.jsp" name = "f" method = "post">
				<table style = "margin: 0 auto" border = "1" width = "500">
					<tr>
						<th>회원 아이디</th>
						<td>
							<input type = "text" name = "me_id" value = "" size = "20" maxlength = "12" >
						</td>
					</tr>
					<tr>
						<th>회원 비밀번호</th>
						<td>
							<input type = "password" name = "me_pass" value = "" size = "20" maxlength = "15">
						</td>
					</tr>
					<tr>
						<th>영화제목</th>
						<td>
							<select name = "mo_name" multiple> <!-- multiple:  여러개 선택 가능-->
								<option disabled="disabled">영화제목을 선택하시오.</option> <!-- disabled: 선택 불가능-->
							
							<!-- 
								★★ 주의 : 반드시 do~while문만 사용가능 ★★
								 => if(!rs.next()){ }에서 이미 rs.next()를 실행했기때문에 첫번째 영화제목을 가르키고 있기때문에
								    첫번째 영화값을 출력하고 rs.next()를 실행해야함
								    (while문을 사용할 경우 첫번째 영화제목이 나오지 않음)
							-->
							
							<%
							do{	
							%>
								<option value="<%=rs.getString(1) %>"><%=rs.getString(1) %></option>
							<%
								}while(rs.next());
							%>	
							</select>
						</td>
					</tr>
					<tr>
						<th>예매일시</th>
						<td>
							<input type = "text" name = "rm_date" value = "<%=rm_date %>" size = "20" maxlength = "8">
						</td>
					</tr>
					<tr>
						<th colspan = "2">
						    <input type ="submit" value ="영화 예매" onclick = "check(); return false">
						    <input type ="reset" value ="조회" onclick="location.href = 'select.jsp'">
					   </th>
					</tr>
				</table>
			</form>
	</section>
	<%@ include file = "footer.jsp" %>
</body>
</html>