package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	
	// 1. 멤버변수
	
	// 2. 기본생성자
	
	// 3. 메서드
	// 3-1. 매개값으로 전달받은 id로 조회한 개 상품 정보 하나를 Dog객체로 반환
	public Dog getCartDog(int id){
		// 1. 커넥션 풀에서 Connection객체 얻어와
		Connection con =  getConnection(); // JdbcUtil.getConnection(); 클래스명(JdbcUtil)생략가능 -> 이유: import static db.JdbcUtil.*; 해줬기 때문
		
		// 2. 싱글톤 패턴 DogDAO 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		
		// 3. DB작업에 사용될 Connection객체를 DogDAO의 멤버변수에 전달하여 DB와 연결하여 작업하도록 서비스 해줌
		dogDAO.setConnection(con);
		
		/*--- DogDAO의 해당 메서드를 호출하여 처리 ---*/
		
		// 'id로 조회한 개 상품 정보를 Dog객체로 반환'
		Dog dog = dogDAO.selectDog(id);
		
		/*--- [insert, update, delete 경우] 성공하면 COMMIT 실패하면 ROLLBACK 
		             (select 제외)                                        ---*/
		
		// 4. 해제
		close(con); // Connection객체 해제
		
		return dog;
	}
	
	/*
	 *  3-2. 위에서 얻어온 Dog객체를 장바구니 항목에 추가하는 메서드
	 *  ★★ 장바구니 항목을 유지할 수 있도록 SESSION영역에 장바구니 항목을 추가해야하므로
	 *      매개값으로 "request 객체"와 장바구니에 추가할 개 정보 객체를 전송받아야 함
	 */
	public void addCart(HttpServletRequest request, Dog cartDog){ // cartDog : 장바구니에 넣을 Dog객체
		
		HttpSession session = request.getSession(); // request로부터 SESSION영역 얻어오기 (기존의 세션영역이 있으면 기존의 영역을 가져오고 없으면 새로운 세션영역 생성)
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		// 장바구니 담기가 처음이라 session영역에 cartList가 참조하는 내용이 없으면
		if(cartList == null) {
			cartList = new ArrayList<Cart>(); // cartList를 생성해서
			session.setAttribute("cartList", cartList); // 세션영역에 담겠다.
		}
		
		boolean isNewCart = true; // 지금 장바구니에 담는 개 상품이 새로 추가되는 개 상품인지를 저장할 변수 (true : 처음으로 넣은 개 상품)
		
		// ★★ 순서 중요
		// 1. 기존에 장바구니 항목(cartList)이 존재하면 같은 개 상품을 찾아서 수량을 1증가
		for(int i=0; i<cartList.size(); i++) {
			// 찾는 기준 : 개 품종 = "개 상품명" (kind)
			if(cartList.get(i).getKind().equals(cartDog.getKind())) {
				isNewCart = false; // 장바구니 항목에 존재하면 false로 만들어서 "순서2."부분 실행을 막아야함
				cartList.get(i).setQty(cartList.get(i).getQty() + 1); // 기존의 수량(getQty())을 불러와 1을 추가시킴
			}
		}
		
		// 2. 
		if(isNewCart == true) { // 장바구니에 추가하는 개 상품이 중복된게 아니라면
			Cart cart = new Cart(); // 기본값으로 채워짐
			cart.setImage(cartDog.getImage()); // 매개값으로 전달받은 Dog객체의 값들을 cart에 셋팅시켜줌
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1); // 수량은 처음이므로 1로 셋팅
			
			cartList.add(cart); // 셋팅한 값들을 cartList에 추가함
		}
		
		
	}

}
