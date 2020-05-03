package board.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.QDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class QDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("Q_DetailAction");
		
		// 사용자가 1:1 작성한 것을 불러옴
		// and 관리자가 작성한 것도 불러옴
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		int kID=Integer.parseInt(request.getParameter("kID"));
		System.out.println(kID);
		
		QDetailService q_DetailService = new QDetailService();
		
		BoardBean boardBean=q_DetailService.getOneonOnegetArticle(boardNum,kID); //1:1문의내역 상세
		BoardBean boardBean2=q_DetailService.getOneonOnegetAnswer(boardNum,kID); //1:1문의 답변
		q_DetailService.updateReadCount(boardNum);
		
		
		
		request.setAttribute("boardBean",boardBean);
		request.setAttribute("boardBean2",boardBean2);
		
		
//		boardNum, kID, boardWriter, boarTitle, boardContent, boardRegTime, boardReRef, boardReLev, boardReSeq, boardReadcount, bookID
		
		
		forward = new ActionForward();
		forward.setPath("/board/QDetail.jsp");
		return forward;
	}

}
