<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.sql.*"%>


<% // 자바코드 작성
request.setCharacterEncoding("UTF-8"); // 한글깨짐 방지 (form에서 get방식으로 데이터를 전송할때 깨짐을 방지하기위해)

// 1. 오라클 드라이버 로딩
Class.forName("oracle.jdbc.OracleDriver");

// 2. Connectin 객체 생성 (DB와 JAVA사이의 통로생성)
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234"); // (url, user, password)

// 3. 구문객체 생성
Statement stmt = con.createStatement();
PreparedStatement ps = null;

// 4. 결과셋 선언 
ResultSet rs = null;

// 5. 쿼리문 선언
String sql ="";

%>