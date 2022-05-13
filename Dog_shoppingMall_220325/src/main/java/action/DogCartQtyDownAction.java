package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String kind = request.getParameter("kind");
		DogCartQtyDownService dogCartQtyDownService = new DogCartQtyDownService();
		dogCartQtyDownService.downCartQty(kind, request);
		
		forward = new ActionForward("dogCartList.dog", true); // 리다이렉트 방식(=새요청)으로 포워딩 (개 상품 항목을 1 감소 시킨 새로운 요청으로 장바구니 내역을 가져와야하기 때문)
		
		return forward;
		// return new ActionForward("dogCartList.dog", true);
	}

}
