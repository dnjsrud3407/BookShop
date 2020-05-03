package admin.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import static access.Access.*;
public class QDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		// 서블릿에 리퀘스트 객체로 카테고리 정보를 포함되어있다고 가정
		String k1 = "1:1문의";
		System.out.println(k1);
		// DB 작업을 위한 BoardService 객체와 글 정보를 담아올 BoardBean 객체 선언
		BoardService boardService = new BoardService();
		BoardBean question = null;
		BoardBean answer = null;
		
		// 원 문의글과 답변글을 동시에 불러오기 위한 메서드 getQ(), 반환은 BoardBean 의 ArrayList 객체 QDeatails 
		ArrayList<BoardBean> QDeatails = boardService.getQ(boardNum, k1);
		
		// BoardService 객체의 getArticle() 메서드를 호출(BoardBean 객체 반환)
//		bb = boardService.getArticle(boardNum, k1);
		
		// 
		// 
		ArrayList<String> k2List = boardService.getk2List(k1);
		
		// FAQ 카테고리 목록
		request.setAttribute("k2List", k2List);
		
		forward = new ActionForward();
		if(QDeatails.size() > 0) {
			// 받아온 글 정보가 있다면 해당 글 정보를 표시할 jsp 파일로 이동
			//
			question = QDeatails.get(0);
			request.setAttribute("question", question);
			if(QDeatails.size() > 1) {
				answer = QDeatails.get(1);
				request.setAttribute("answer", answer);
			}
			forward.setPath("/admin/board/qDetail.jsp");
			
		} else {
			// 받아온 글 정보가 없다면 메시지 호출 후 글 목록으로 보내기
			session.setAttribute("ErrorMSG", "존재하지 않는 글입니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}