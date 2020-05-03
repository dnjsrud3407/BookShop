package admin.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import board.svc.NoticeDetailService;
import vo.ActionForward;
import vo.BoardBean;
import static access.Access.*;
public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("공지사항 내용보기");
		
		HttpSession session = request.getSession();
		// 관리자 체크
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		// 서블릿에 리퀘스트 객체로 카테고리 정보를 포함되어있다고 가정
		String k1 = "공지사항";
		// DB 작업을 위한 BoardService 객체와 글 정보를 담아올 BoardBean 객체 선언
		BoardService boardService = new BoardService();
		BoardBean bb = null;
		// BoardService 객체의 getArticle() 메서드를 호출(BoardBean 객체 반환)
		bb = boardService.getArticle(boardNum, k1);
		
		// 
		// 
		forward = new ActionForward();
		
		if(bb != null) {
			// 받아온 글 정보가 있다면 해당 글 정보를 표시할 jsp 파일로 이동
			//
			request.setAttribute("article", bb);
			forward.setPath("/admin/board/NoticeModifyForm.jsp");
		} else {
			// 받아온 글 정보가 없다면 메시지 호출 후 글 목록으로 보내기
			//
			//
			session.setAttribute("ErrorMSG", "존재하지 않는 게시물입니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
