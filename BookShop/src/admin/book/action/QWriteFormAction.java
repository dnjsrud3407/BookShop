package admin.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReWriteService;
import vo.ActionForward;
import vo.BoardBean;

public class QWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 문의글 번호, 페이지값 들고오기
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String page = request.getParameter("page");
		
		// 사용자가 상품 문의한 것을 불러옴
		int kID = 102;
		QnReWriteService qWriteService = new QnReWriteService();
		BoardBean board = qWriteService.getBoard(boardNum, kID);

		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("./admin/book/qWriteForm.jsp");
		return forward;
	}

}
