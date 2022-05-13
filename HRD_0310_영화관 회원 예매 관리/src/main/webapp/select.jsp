<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 회원 예매 관리</title>
</head>


<body>
	<%@ include file = "header.jsp" %>  
	<section>
	<img src="images/movie.png">
		<div>
			<h2>회원별 예매정보 조회</h2>
		</div>
		<form action ="insertPro.jsp" name = "f" method = "post">
				<table style = "margin: 0 auto" border = "1" width = "800">
					<tr>
						<th>회원아이디</th>
						<th>회원이름</th>
						<th>영화제목</th>
						<th>시청등급</th>
						<th>예매횟수</th>
					</tr>
					<%
					sql  = "SELECT me_id, me_name, mo_name, mo_limit, COUNT(*)";
					sql += " FROM movie_2 NATURAL JOIN movie_3 JOIN movie_1";
					sql += " USING (me_id)";
					sql += " GROUP BY me_id, me_name, mo_name, mo_limit";
					sql += " ORDER BY me_name";
					
					rs = stmt.executeQuery(sql);
					while(rs.next()){
					%>
					<tr align="center">
						<td><%=rs.getString(1) %></td>
						<td><%=rs.getString(2) %></td>
						<td><%=rs.getString(3) %></td>
						<td><%=rs.getString(4) %></td>
						<td><%=rs.getString(5) %></td>
					</tr>
					<%	
					}
					%>
				</table>
			</form>
	</section>
	<%@ include file = "footer.jsp" %>
</body>
</html>