<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.ArrayList"%>    

<%@ include file="dbcon.jsp" %>

<%
ResultSet rs2 = null;
try{
	
	sql = "SELECT me_id FROM movie_1";
	rs = stmt.executeQuery(sql);
	int flag = 0;
	
	ArrayList<String> cusList = new ArrayList<String>();
	
	while(rs.next()){
		String cusID = rs.getString(1);
		cusList.add(cusID);
	}
	
	/*
	   - 에러메세지 : "결과집합을 모두 소모했음"
		 rs2.next()가 false인 경우(즉, 해당하는 값이 없을때) getString()을 하게 되면 발생 -> 해결방법 : if(rs2.next()) 조건문 사용
		
		  그외의 상황
		  1. connection, statement, resultset중 어느거라도 close() 한후에 resultset으로부터 값을 얻으려고 할때
		  2. resultset의 범위를 넘어선 상태에서 값을 얻을려고 할때
		
	   - 에러메세지 : "xxx[을] 종료[했음]"
		 connection, statement, resultset중 어느거라도 close() 한후에 resultset의 next()를 호출하면
		 
	 */
	 
	sql = "SELECT me_pass FROM movie_1 WHERE me_id = '"+ request.getParameter("me_id") +"'";
	rs2 = stmt.executeQuery(sql);
	String cusPw = null;
	if(rs2.next()){
		cusPw = rs2.getString(1);	
	}
	
	for(int i=0; i <cusList.size(); i++){
	
		// if(cusList.get(i).equals(request.getParameter("me_id"))){
		if(request.getParameter("me_id").equals(cusList.get(i))){
			flag = 1;
			
			if(request.getParameter("me_pass").equals(cusPw)){
				flag = 2;
				break;
			} // if문 pw확인
		} // if문 id확인
	} // for문
	
	
	if(flag == 0){
	%>
	<script type="text/javascript">
			alert("등록되지않은 아이디 입니다.");
			history.back();
	</script>
	<%	
	}else if(flag == 1){
	%>
	<script type="text/javascript">
			alert("비밀번호를 잘못 입력했습니다.");
			history.back();
	</script>
	<%	
	}else if(flag == 2){
		
	sql = "INSERT INTO movie_2 VALUES(?, ?, ?, ?)";
	
	ps = con.prepareStatement(sql);
	ps.setString(1, request.getParameter("me_id"));
	ps.setString(2, request.getParameter("me_pass"));
	ps.setString(3, request.getParameter("mo_name"));
	ps.setString(4, request.getParameter("rm_date"));
	
	ps.executeUpdate();
%>
	<script type="text/javascript">
		alert("영화예매가 완료되었습니다.");
		location.href = "select.jsp";
	</script>
<%
	}// else if(flag == 2)문
}catch(Exception e){
	e.printStackTrace();
%>
	<script type="text/javascript">
		alert("영화예매를 실패하였습니다.");
		history.back();
	</script>
<%	
}finally{
	try{
		if(con != null) con.close();
		if(stmt != null) stmt.close();
		if(ps != null) ps.close();
		if(rs != null) rs.close();
		if(rs2 != null) rs2.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
%>