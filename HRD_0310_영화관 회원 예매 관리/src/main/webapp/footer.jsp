<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 회원 예매 관리</title>
</head>
<body>

	<footer>
		HRDKOREA Copyright &copy;2021 All reserved.
	</footer>
</body>
</html>

<% // jsp -> java의 서블릿 파일 -> 
try{ // close()할때 예외가 발생할수 있음 
	if(con != null) con.close();
	if(stmt != null) stmt.close();
	if(ps != null) ps.close();
	if(rs != null) rs.close();
} catch(Exception e){
	e.printStackTrace();
}
%>