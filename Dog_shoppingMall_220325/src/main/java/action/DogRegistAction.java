package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 파일을 업로드 할 서버상의 디렉토리 경로
		String saveFolder = "/images";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		// 한번에 올릴 수 있는 최대용량 5M로 제한
		int maxsize = 5*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxsize, "UTF-8", new DefaultFileRenamePolicy());
		
		Dog dog = new Dog(0, // 0인 이유? insert할때 id값은 dog_seq.nextval로 자동 1증가
			   	  multi.getParameter("kind"), 
				  multi.getParameter("country"), 
				  Integer.parseInt(multi.getParameter("price")), // String -> int 
				  Integer.parseInt(multi.getParameter("height")), 
				  Integer.parseInt(multi.getParameter("weight")), 
				  multi.getParameter("content"), 
				  multi.getFilesystemName("image"), // 시스템상 파일명으로 가져와야함
				  0); // 조회수 : 0부터 시작
		
		DogRegistService dogRegistService = new DogRegistService();
		boolean isRegistSuccess = dogRegistService.registDog(dog); // 새로운 개 정보(dog)를 dog테이블에 insert함
		
		if(isRegistSuccess) {
			forward = new ActionForward("dogList.dog", true); // true : 리다이렉트 (새로 insert된 데이터와 함께 뿌려야하기 때문에 기존의 요청이 아닌 새요청으로 받아야함)
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter(); // 화면에 출력
			out.println("<script>");
			out.println("alert('등록 살패');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

};
