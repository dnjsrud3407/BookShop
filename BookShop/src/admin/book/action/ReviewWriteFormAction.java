package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReWriteService;
import vo.ActionForward;
import vo.BoardBean;

public class ReviewWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 후기글 번호, 페이지값 들고오기
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String page = request.getParameter("page");
		
		// 사용자 상품 후기 글을 불러옴
		int kID = 103;
		QnReWriteService reviewWriteService = new QnReWriteService();
		BoardBean board = reviewWriteService.getBoard(boardNum, kID);

		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("./admin/book/reviewWriteForm.jsp");
		return forward;
	}

}
