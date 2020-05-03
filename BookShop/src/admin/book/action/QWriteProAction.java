package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.book.svc.QnReWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class QWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		// 페이지, 문의글 ReRef, bookID 파라미터와 답변 제목,내용 불러오기
		String page = request.getParameter("page");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		int boardReRef = Integer.parseInt(request.getParameter("boardReRef"));
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		
		// 관리자 id 가져오기 
		HttpSession session = request.getSession();
		String boardWriter = (String)session.getAttribute("uID");
		
		QnReWriteProService qWriteProService = new QnReWriteProService();
		
		// 게시글 번호 생성
		int kID = 102;
		int boardNum = qWriteProService.getBoardNum(kID);
		
		BoardBean board = new BoardBean(
				boardNum, 
				kID, 
				boardWriter, 
				boardTitle, 
				boardContent, 
				boardReRef, 
				1, 
				bookID);
		
		qWriteProService.writeAnswerBoard(board);
		
		forward = new ActionForward();
		
		// 문의 답변 작성한거 상세보기 창으로 이동
		forward.setPath("QDetail.abook?boardReRef=" + boardReRef + "&page=" + page);
		forward.setRedirect(true);
		
		return forward;
	}

}
