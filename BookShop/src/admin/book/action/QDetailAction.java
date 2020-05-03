package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReWriteService;
import vo.ActionForward;
import vo.BoardBean;

public class QDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		// boardReRef와 page 파라미터 불러옴
		int boardReRef = Integer.parseInt(request.getParameter("boardReRef"));
		String page = request.getParameter("page");
		System.out.println("QDetailAction까지 왔음");
		
		// 사용자가 문의 작성한 것을 불러옴 and 관리자가 작성한 것도 불러옴
		int kID = 102;
		QnReWriteService qDetailService = new QnReWriteService();
		ArrayList<BoardBean> qList = qDetailService.getqnaList(boardReRef, kID);
		
		// i = 0   =>   사용자 문의 글
		// i = 1   =>   관리자 문의 답변 글
		request.setAttribute("page", page);
		request.setAttribute("board_q", qList.get(0));
		request.setAttribute("board_answer", qList.get(1));
		
		
		forward = new ActionForward();
		forward.setPath("./admin/book/qDetail.jsp");
		return forward;
	}

}
