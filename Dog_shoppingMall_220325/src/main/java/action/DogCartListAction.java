package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		DogCartListService dogCartListService = new DogCartListService();
		ArrayList<Cart> cartList = dogCartListService.getCartList(request);
		// request.setAttribute("cartList", cartList); // request에 셋팅
		
		/*--- [교재 p.758] 장바구니 목록에 존재하는 전체 상품에 대한 "총 금액 계산" ---------------*/
		
		int totalMoney = 0; // 지불한 총 금액
		// int money = 0; // 장바구니 항목 하나에 대한 지불 금액
		
		for(int i=0; i <cartList.size(); i++) {
			totalMoney += cartList.get(i).getPrice()*cartList.get(i).getQty();
		}
		
		request.setAttribute("cartList", cartList); // request에 셋팅
		request.setAttribute("totalMoney", totalMoney);
		
		forward = new ActionForward("dogCartList.jsp", false);// 뷰에 뿌리기 위해 (false : 디스패치방식 -> request에 저장된 값들을 받아와야하기때문)
		return forward;
	}

}
