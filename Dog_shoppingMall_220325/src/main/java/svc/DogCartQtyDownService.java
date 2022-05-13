package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartQtyDownService {
	// 멤버변수
	
	// 기본생성자
	
	// 메서드
	public void downCartQty(String kind, HttpServletRequest request){
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		/* 장바구니 항목 객체에서 수량을 증가시킬 대상을 kind값으로 비교하여 검색한 후 해당 객체의 수량 감소 */
		for(int i=0; i<cartList.size(); i++) {
			/*
			if(cartList.get(i).getKind().equals(kind)) {
				cartList.get(i).setQty(cartList.get(i).getQty()-1); // 기존 Qty값을 얻어와서 1감소
			}
			*/
			
			Cart cart = cartList.get(i);
			if(cart.getKind().equals(kind)) {
				cart.setQty(cartList.get(i).getQty()-1); // 기존 Qty값을 얻어와서 1감소 후
				break; // 해당하는 kind값을 찾았으면 방복문 빠져나감 (kind는 중복된 값이 없기 때문)
			}
			
		}
	}
}
