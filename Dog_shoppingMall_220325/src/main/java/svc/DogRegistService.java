package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogRegistService {

	public boolean registDog(Dog dog) {
		
		// boolean isRegistSuccess = false; // 지역변수 초기화
		
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // JdbcUtil.getConnection(); 클래스명(JdbcUtil)생략가능 -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 DogDAO 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 DogDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		dogDAO.setConnection(con);
		
		/*--- DogDAO의 해당 메서드를 호출하여 처리 ---*/
		int insertCount = dogDAO.insertDog(dog); // 새로운 개 상품의 insert 성공하면
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		             (select 제외)                                        ---*/
		
		boolean isRegistSuccess = false; // 지역변수 초기화
		
		if(insertCount > 0) { // update를 성공했으면
			commit(con);
			isRegistSuccess = true;
		}else {
			rollback(con);
		}
		
		// 4. 해제
		close(con); // Connection객체 해제
		
		return isRegistSuccess;
	}

}
