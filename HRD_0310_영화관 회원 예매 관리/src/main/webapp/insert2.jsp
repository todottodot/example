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
	<section>
	<img src="images/movie.png">
		<div>
			<h2>영화예매</h2>
		</div>
		<form action ="insertPro2_1.jsp" name = "f" method = "post">
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
							<select name = "mo_name">
							<option value="">영화제목을 선택하시오.</option>
							<%
								sql = "SELECT mo_name FROM movie_3";
								rs = stmt.executeQuery(sql);
								
								while(rs.next()){
									
								String mo_name = rs.getString(1);
							
							%>
								<option value="<%=mo_name %>"><%=mo_name %></option>
							<%
								}
							%>	
							</select>
						</td>
					</tr>
					<tr>
						<th>예매일시</th>
						<td>
							<input type = "text" name = "rm_date" value = "" size = "20" maxlength = "8">
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