package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		DogCartAddService dogCartAddService = new DogCartAddService(); // DAO작업을 할수 있도록 도와주는 역할
		int id = Integer.parseInt(request.getParameter("id"));
		Dog cartDog =  dogCartAddService.getCartDog(id); // Dog객체를 얻어와서 cartDog에 추가
		
		dogCartAddService.addCart(request, cartDog); // 장바구니 항목에 추가
		
		// 주의 : dogCartList.dog 요청("장바구니 목록보기 요청") 을 리다이렉트 방식으로 포워딩 함 (새로운 요청인 이유? 기존의 요청이 아닌 장바구니에 추가를한 내용을 보여줘야하기때문) 
		forward = new ActionForward("dogCartList.dog", true); 
		
		return forward;
	}

}
