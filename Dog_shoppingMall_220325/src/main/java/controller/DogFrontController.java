package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.DogCartAddAction;
import action.DogCartListAction;
import action.DogCartQtyDownAction;
import action.DogCartQtyUpAction;
import action.DogCartRemoveAction;
import action.DogCartSearchAction;
import action.DogListAction;
import action.DogRegistAction;
import action.DogViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */
// 확장자가 .dog면 무조건 DogFrontController로 이동하여 
@WebServlet("*.dog")
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response); // doGet이든 doPost든 doProcess에서 처리함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response); // doGet이든 doPost든 doProcess에서 처리함
	}
	
	// 이 서블릿으로 들어오는 모든 요청은 doProcess()를 호출하여 처리
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // ★ 반드시 첫줄
		
		// 요청객체로부터 '프로젝트명+파일경로'를 가져옴 (예) /project/dogList.dog 
		String requestURI = request.getRequestURI();
		
		// 요청객체로부터 'contextPath'를 가져옴 (예) /project
		String contextPath = request.getContextPath();
		
		/*
		 * [ dogList.dog(어떤요청이 왔는지 판단을 하는 단서)만 추출하기 위한 작업 ]
		 * 
		 *    /project/dogList.dog  - /project =  /dogList.dog 
		 * 
		 */
		
		String command = requestURI.substring(contextPath.length()); // 시작인덱스(index : 8 (두번째 / )) ~ 끝까지 부분문자열 생성
		
		/*
		 * 컨트롤러에서 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해서 동일한 타입인 Action으로 참조하기 위해
		 */
		Action action = null;  // Action부모 인터페이스 = Action을 구현한 객체
		ActionForward forward = null;
		
		/*
		 * 
		 */
		if(command.equals("/dogList.dog")) { // '개 상품 목록보기'요청이면
			action = new DogListAction(); // Action 인터페이스를 부모로해서 실행메서드(DogListAction.java의 execute())를 재정의함
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogView.dog")) { // '특정 개 상품의 상세 정보 보기'요청이면
			action = new DogViewAction(); // Action 인터페이스를 부모로해서 실행메서드(DogListAction.java의 execute())를 재정의함
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogRegistForm.dog")) { // '새로운 개 상품 정보 등록 폼인 뷰 페이지 보기'요청이면
			/*
			action = new DogViewAction(); // Action 인터페이스를 부모로해서 실행메서드(DogListAction.java의 execute())를 재정의함
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			forward = new ActionForward("dogRegistForm.jsp", true); // 리다이렉트 방식(새요청)-> DB작업이 없으므로, ※ false로 해도됨 (기존의 요청을 전달해도 무방함)
		}
		else if(command.equals("/dogRegist.dog")) { // '새로운 개 상품 정보 등록 처리하는'요청이면
			action = new DogRegistAction(); 
			
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*** 장바구니 추가 *******************************************************************************/
		else if(command.equals("/dogCartAdd.dog")) { // '새로운 장바구니 항목을 추가하는 장바구니 담기'요청이면
			action = new DogCartAddAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dogCartList.dog")) { // '장바구니 목록보기'요청이면
			action = new DogCartListAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartSearch.dog")) { // '최하가격 ~ 최고가격으로 장바구니 항목을 검색'요청이면
			action = new DogCartSearchAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dogCartQtyUp.dog")) { // '장바구니 항목 수량 증가'요청이면
			action = new DogCartQtyUpAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dogCartQtyDown.dog")) { // '장바구니 항목 수량 감소'요청이면
			action = new DogCartQtyDownAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dogCartRemove.dog")) { // '장바구니 항목 삭제'요청이면
			action = new DogCartRemoveAction(); 
			
			try {
				forward = action.execute(request, response); // DB로 들어가서 INSERT작업을 해야하므로 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/******************************************************************
		 **   포워딩
		 *******************************************************************/
		if(forward != null) {
			//if(forward.isRedirect())
			if(forward.isRedirect() == true) { // isRedirect() == true이면 리다이렉트(새요청) 방식 -> 기존 request 공유 못함
				response.sendRedirect(forward.getPath()); // dogRegistForm.jsp
			}else {
				//RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // dogList.jsp
				// 기존요청, 기존응답을 매개값으로 그대로 전달하므로 request 공유할 수 있다.
				//dispatcher.forward(request, response);
				
				request.getRequestDispatcher(forward.getPath()).forward(request, response); // dogView.jsp로 이동 (한줄로 표현하기)
			}
		}
		
	}

}
