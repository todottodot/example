package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartSearchService {
	// 멤버변수
	
	// 기본생성자
	
	// 메서드
	public ArrayList<Cart> getCartSearchList(int startMoney, int endMoney, HttpServletRequest request){
		
		HttpSession session =  request.getSession(); // 세션이 존재하므로 기존 세션을 돌려줌
		ArrayList<Cart> oldCartList = (ArrayList<Cart>) session.getAttribute("cartList"); // oldCartList: 검색하기전 장바구니 목록
		
		ArrayList<Cart> searchCartList = new ArrayList<Cart>(); // 검색 후 장바구니 목록
		
		for(int i =0; i<oldCartList.size(); i++) {
			
			/*
			if(startMoney <= oldCartList.get(i).getPrice() && oldCartList.get(i).getPrice() >= endMoney) {
				searchCartList.add(oldCartList.get(i));
			}
			*/
			
			Cart cart = oldCartList.get(i);
			if(startMoney <= cart.getPrice() && cart.getPrice() <= endMoney) {
				searchCartList.add(cart);
			}
			
		}
		return searchCartList;
	}
}
