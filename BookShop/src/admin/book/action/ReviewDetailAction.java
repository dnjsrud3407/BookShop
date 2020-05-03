package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReWriteService;
import vo.ActionForward;
import vo.BoardBean;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// boardReRef와 page 파라미터 불러옴
		int boardReRef = Integer.parseInt(request.getParameter("boardReRef"));
		String page = request.getParameter("page");
		
		// 사용자가 후기 작성한 것을 불러옴 and 관리자가 작성한 것도 불러옴
		int kID = 103;
		QnReWriteService reviewDetailService = new QnReWriteService();
		ArrayList<BoardBean> qnaList = reviewDetailService.getqnaList(boardReRef, kID);
		
		// i = 0   =>   사용자 후기 글
		// i = 1   =>   관리자 후기 답변 글
		request.setAttribute("page", page);
		request.setAttribute("board_review", qnaList.get(0));
		request.setAttribute("board_answer", qnaList.get(1));
		
		
		forward = new ActionForward();
		forward.setPath("./admin/book/reviewDetail.jsp");
		return forward;
	}

}
