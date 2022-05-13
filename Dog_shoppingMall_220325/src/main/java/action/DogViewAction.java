package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; // 먼저 이렇게 만들어 놓기
		
		DogViewService dogViewService = new DogViewService();
		
		// 클릭한 해당 개 상품의 '조회수 1 증가' -> 'id로 조회한 개 상품 정보를 Dog객체로 반환'
		int id = Integer.parseInt(request.getParameter("id"));
		Dog dog = dogViewService.getDogView(id); // id는 int타입이므로 형변환 해줘야함
		
		request.setAttribute("dog", dog);
		
		// 교재 p.753 두번째 그림의 하단 부분(doList.jsp) : 오늘 본 개 상품 목록 정보
		Cookie todayImageCookie = new Cookie("today"+id, dog.getImage());
		todayImageCookie.setMaxAge(24*60*60); // 24시간 (=하루) 설정
		response.addCookie(todayImageCookie); // 반드시, 응답에 쿠키 객체를 추가해서 보낸다.
		
		forward = new ActionForward("dogView.jsp", false); // "dogView.jsp"페이지에 디스패치방식으로 보냄
		
		return forward;
	}

}
