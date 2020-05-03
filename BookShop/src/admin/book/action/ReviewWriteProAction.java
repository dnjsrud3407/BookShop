package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.book.svc.QnReWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// uID, 페이지, 후기글 ReRef, bookID 파라미터와 답변 제목,내용 불러오기
		String boardWriter = request.getParameter("boardWriter");
		String page = request.getParameter("page");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		int boardReRef = Integer.parseInt(request.getParameter("boardReRef"));
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		
		// 관리자 id 가져오기 
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		
		QnReWriteProService reviewWriteProService = new QnReWriteProService();
		
		// 게시글 번호 생성
		int kID = 103; 
		int boardNum = reviewWriteProService.getBoardNum(kID);
		
		BoardBean board = new BoardBean(
				boardNum, 
				103, 
				uID, 
				boardTitle, 
				boardContent, 
				boardReRef, 
				1, 
				bookID);
		
		int updateCount = reviewWriteProService.writeAnswerBoard(board);
		
		// 포인트 올리는 작업
		if(updateCount > 0) {
			reviewWriteProService.plusMemberPoint(boardWriter);
		}
		
		forward = new ActionForward();
		
		// 후기 작성한거 상세보기
		forward.setPath("ReviewDetail.abook?boardReRef=" + boardReRef + "&page=" + page);
		forward.setRedirect(true);
		
		return forward;
	}

}
