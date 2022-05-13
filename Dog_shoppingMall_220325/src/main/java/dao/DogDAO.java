// DB로 SQL구문을 전송하는 클래스

package dao;

import static db.JdbcUtil.*; // static : 모든 메서드들을 미리 메모리에 올림 -> 클래스명 없이 바로 호출 가능

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.Dog;

public class DogDAO {
	
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	private Connection con;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/** 싱글톤 패턴 : DogDAO객체 단 1개만 생성
	 *  이유? 외부클래스에서 "처음 생성된 DogDAO객체를 공유해서 사용하도록 하기 위해" 
	 **/
	
	// 1. 생성자는 무조건 private을 붙임(이유? 외부 클래스에서 생성자 호출 불가하여 직접적으로 객체생성 불가능하게 하기위해)
	private DogDAO() {}
	
	private static DogDAO dogDAO; // 반드시 static이여야함 (getInstance()메서드에서 dogDAO를 호출하여 사용하는데  getInstance()메서드가 static이기 때문)
	
	// 2. static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 DogDAO객체를 1개만 만들수 있도록 하기 위해서
	public static DogDAO getInstance(){ // 객체를 만드는 생성자는 무조건 static 
		if(dogDAO == null) { // Dog객체가 없으면  (만들어져 있다면 생성되면 안되기때문)
			dogDAO = new DogDAO(); // 객체 생성
		}
		
		return dogDAO; // 생성되어 있다면 기존 객체의 주소를 리턴
	}
	
	/**************************************************************************/
	
	public void setConnection(Connection con){
		// Connection객체 1개를 받아서 멤버변수에 셋팅(Connection객체를 참조할수 있도록) 
		this.con = con;
	}

	// 1. 모든 개 상품 정보를 조회하여 ArrayList<Dog>객체 반환
	public ArrayList<Dog> selectDogList() {
		ArrayList<Dog> dogList = null;
		
		String sql = "SELECT * FROM DOG";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // sql문에서 insert한 값이 1개라도 있으면
				dogList = new ArrayList<Dog>();
				
				do {
					Dog dog = new Dog(rs.getInt("id"), rs.getString("kind"), rs.getString("country"), rs.getInt("price"),
							  rs.getInt("height"), rs.getInt("weight"), rs.getString("content"), rs.getString("image"), rs.getInt("readcount"));
					dogList.add(dog);
				}while(rs.next());
			} // if문 끝
			
		} catch (Exception e) {
			System.out.println("[DogDAO]selectDogList 에러 : " + e); // e: 예외종류 + 예외 메세지
		}finally {
			//JdbcUtil.close(rs);
			//JdbcUtil.close(pstmt);
			close(rs); // import static db.JdbcUtil.*;했기 때문에 JdbcUtil 생략 가능
			close(pstmt);
		}
		
		return dogList;
		
	}
	
	// id에 해당하는 특정 개 상품을 찾아 조회수 1증가
	public int updateReadCount(int id) {
		
		int updateCount = 0; // 지역변수는 초기화
		String sql = "UPDATE DOG SET READCOUNT= READCOUNT+1 WHERE ID =" + id;
		
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate(); // update성공하면 1리턴받음			
			
		} catch (Exception e) {
			System.out.println("[DogDAO]updateReadCount 에러 : " + e); // e: 예외종류 + 예외 메세지
		}finally {
			// close(rs); // import static db.JdbcUtil.*;했기 때문에 JdbcUtil 생략 가능
			close(pstmt);
		}
		
		return updateCount;
	}
	
	// 'id로 조회한 개 상품 정보를 Dog객체로 반환' -> 매개값 반드시 필요
	public Dog selectDog(int id){
		Dog dog = null;
		String sql = "SELECT * FROM DOG WHERE ID = " + id;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // sql문에서 insert한 값이 1개라도 있으면
				dog = new Dog(rs.getInt("id"), rs.getString("kind"), rs.getString("country"), rs.getInt("price"),
						  rs.getInt("height"), rs.getInt("weight"), rs.getString("content"), rs.getString("image"), rs.getInt("readcount"));
			
			} // if문 끝
			
		} catch (Exception e) {
			System.out.println("[DogDAO]selectDog 에러 : " + e); // e: 예외종류 + 예외 메세지
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return dog;
	}
	
	// 새로운 개 상품 정보를 dog테이블에 추가
	public int insertDog(Dog dog) {
		
		int insertCount = 0; // 지역변수는 초기화
		String sql = "INSERT INTO DOG VALUES (dog_seq.nextval,?,?,?,?,?,?,?,? )";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dog.getKind());
			pstmt.setString(2, dog.getCountry());
			
			pstmt.setInt(3, dog.getPrice());
			pstmt.setInt(4, dog.getHeight());
			pstmt.setInt(5, dog.getWeight());
			
			pstmt.setString(6, dog.getContent());
			pstmt.setString(7, dog.getImage());
			
			pstmt.setInt(8, dog.getReadcount());
			
			insertCount = pstmt.executeUpdate(); // insert 성공하면 1리턴받음			
			
		} catch (Exception e) {
			System.out.println("[DogDAO]insertDog 에러 : " + e); // e: 예외종류 + 예외 메세지
		}finally {
			// close(rs); // import static db.JdbcUtil.*;했기 때문에 JdbcUtil 생략 가능
			close(pstmt);
		}
		
		return insertCount;
	}

}
