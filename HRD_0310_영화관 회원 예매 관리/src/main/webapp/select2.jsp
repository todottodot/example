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
						<th>영화제목</th>
						<th>시청등급</th>
						<th>예매횟수</th>
					</tr>
					<%
					sql  = "SELECT mo_name, mo_limit, COUNT(*) AS mo_cnt";
					sql += " FROM movie_2 JOIN movie_3";
					sql += " USING (mo_name)";
					sql += " GROUP BY mo_name, mo_limit";
					sql += " ORDER BY mo_cnt DESC";
					
					rs = stmt.executeQuery(sql);
					while(rs.next()){
					%>
					<tr align="center">
						<td><%=rs.getString(1) %></td>
						<td><%=rs.getString(2) %></td>
						<td><%=rs.getString(3) %></td>
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