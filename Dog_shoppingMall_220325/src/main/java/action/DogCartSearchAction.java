package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		
		ArrayList<Cart> searchCartList = dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		
		//request.setAttribute("cartList", searchCartList); // request에 셋팅하기
		//request.setAttribute("startMoney", startMoney); // "=최하="대신 화면에 표시될 값 셋팅 
		//request.setAttribute("endMoney", endMoney); // "=최고="대신 화면에 표시될 값 셋팅 
		
		/*--- [교재 p.768] 지정한 범위에 해당하는 장바구니 목록에 존재하는 전체 상품에 대한 "총 금액 계산"을 다시 해야함 ---------------*/
		
		int totalMoney = 0; // 지불한 총 금액
		// int money = 0; // 장바구니 항목 하나에 대한 지불 금액
		
		for(int i=0; i <searchCartList.size(); i++) {
			totalMoney += searchCartList.get(i).getPrice()*searchCartList.get(i).getQty();
		}
		
		request.setAttribute("cartList", searchCartList); // request에 셋팅하기
		request.setAttribute("startMoney", startMoney); // "=최하="대신 화면에 표시될 값 셋팅 
		request.setAttribute("endMoney", endMoney); // "=최고="대신 화면에 표시될 값 셋팅
		request.setAttribute("totalMoney", totalMoney);
		
		forward =  new ActionForward("dogCartList.jsp", false); // 기존요청 사용하도록 디스패치 방식으로 포워딩
		
		return forward;
	}

}
