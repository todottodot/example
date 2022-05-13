package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import db.JdbcUtil;
import vo.Dog;

public class DogListService {
	// 멤버변수
	
	// 기본생성자
	
	// 메서드
	public ArrayList<Dog> getDogList(){ // insert되어있는 강아지 종류에 대한 값들을 얻어오는 메서드
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // JdbcUtil.getConnection(); 클래스명(JdbcUtil)생략가능 -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 DogDAO 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 DogDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		dogDAO.setConnection(con);
		
		/*--- DogDAO의 해당 메서드를 호출하여 처리 ---*/
		ArrayList<Dog> dogList = dogDAO.selectDogList();
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		             (select 제외)                                        ---*/
		
		// 4. 해제
		close(con); // Connection객체 해제
		
		return dogList; //  insert되어있는 강아지 종류 반환
	}

}
