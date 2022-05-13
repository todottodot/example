package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	// 기본생성자
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; // 먼저 이렇게 만들어 놓기
		
		// 교재 p.753 두번째 그림
		
		// [위쪽] 개 상품 목록
		// 개 상품 목록보기 요청을 처리하는 서비스 객체 생성 후
		DogListService dogListService = new DogListService();
		
		ArrayList<Dog> dogList = dogListService.getDogList(); // dogList 객체를 뷰까지 전달해야함 (디스패치 방식으로)
		// request.setAttribute("dogList", dogList); 
		
		// [아래쪽] 오늘본 개 상품 목록
		// 오늘 본 개 상품 목록의 이미지를 저장할 ArrayList 객체 생성한 후
		ArrayList<String> todayImageList = new ArrayList<String>();
		
		// 오늘 하루동안 저장해야하므로 쿠키에 저장 (보안이 필요 없으므로 세션에 저장할 필요없음)
		Cookie[] cookieArray = request.getCookies();
		
		if(cookieArray != null) { // 오늘 본 개 상품 목록이 있다면
			for(int i=0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().startsWith("today")) { // 오늘 본 개 상품 목록의 쿠키명이 today로 시작하니?
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		request.setAttribute("dogList", dogList); // '개상품 목록'을 request에 셋팅
		request.setAttribute("todayImageList", todayImageList); // '오늘 본 개 상품 목록'을 request에 셋팅
		
		forward = new ActionForward("dogList.jsp", false); // false : 디스패치방식(기존요청을 그대로 전달하여 공유)으로 전달하겠다
		return forward; // 먼저 이렇게 만들어 놓기
		
		// return new ActionForward("dogList.jsp", false); // 한줄로 표현하기
	}

}
