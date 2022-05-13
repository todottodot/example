package svc;

import static db.JdbcUtil.*;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog; // vo : DTO 역할

public class DogViewService {
	// 멤버변수
	// 기본생성자
	// 메서드
	
	// 클릭한 해당 개 상품의 '조회수 1 증가' -> 'id로 조회한 개 상품 정보를 Dog객체로 반환'
	public Dog getDogView(int id){
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // JdbcUtil.getConnection(); 클래스명(JdbcUtil)생략가능 -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 DogDAO 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 DogDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		dogDAO.setConnection(con);
		
		/*--- DogDAO의 해당 메서드를 호출하여 처리 ---*/
		int updateCount = dogDAO.updateReadCount(id); // 클릭한 해당 개 상품의 '조회수 1 증가'
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		             (select 제외)                                        ---*/
		
		if(updateCount > 0) { // update를 성공했으면
			commit(con);
		}else {
			rollback(con);
		}
		
		// 'id로 조회한 개 상품 정보를 Dog객체로 반환'
		Dog dog = dogDAO.selectDog(id);
		
		
		// 4. 해제
		close(con); // Connection객체 해제
		
		return dog;
	}
}
