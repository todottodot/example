package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartRemoveService {
	// 1. 멤버변수
	
	// 2. 기본생성자
	
	// 3. 메서드
	public void cartRemove(HttpServletRequest request, String[] kindArray) {
		//request객체에서 session영역을 얻어온 후
		HttpSession session = request.getSession();
		// session영역에 공유한 cartList 속성값인 "장바구니 목록 객체"를 얻어와
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		// 바같 for:삭제할 대상을
		for(int i =0; i<kindArray.length; i++) {
			for(int k=0; k<cartList.size(); k++) { //안쪽 for : 장바구니 항목에서 찾아서
				if(kindArray[i].equals(cartList.get(k).getKind())) { // 삭제할 kind
					cartList.remove(cartList.get(k)); // 장바구니 목록에서 제거한 후
					break; // 안쪽 반복문을 빠져나감(kind가 각 1가지씩만 있을때)
				}
			}// 안쪽 for문 끝
		}// 바깥쪽 for문 끝
	}
}
