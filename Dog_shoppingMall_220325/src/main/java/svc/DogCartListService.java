package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartListService {
	// 1. 멤버변수
	
	// 2. 생성자
	
	// 주의 : DB작업하는 것이 아니므로 일련의 순서1234(커넥션풀 연결, 해제하는 순서 )할 필요 없다.
	// 3. 메서드 : request로부터 session영역을 얻어와 그안의 cartList 속성값을 반환 (cartList는 DB안에 있는 내용이 아니라 SESSION영역에 존재함)
	public ArrayList<Cart> getCartList(HttpServletRequest request){ // 주의 : 매개값으로 반드시 request (session영역에 있는 값을 얻어와야하기 때문에 매개값으로 request를 얻어와야함)
		// request로부터 session영역을 얻어와
		HttpSession session = request.getSession(); 
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList"); // 세션영역안에 있는 cartList를 얻어옴 (cartList는 ArrayList이므로 형변환 해줘야함)
		
		return cartList;
	}
}
