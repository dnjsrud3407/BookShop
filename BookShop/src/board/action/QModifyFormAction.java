package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.QDetailService;
import board.svc.QModifyFormService;
import vo.ActionForward;
import vo.BoardBean;

public class QModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("1:1수정폼");
		// 사용자가 1:1 작성한 것을 불러옴
		// and 관리자가 작성한 것도 불러옴
		QModifyFormService qModifyFormService = new QModifyFormService();
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		int kID=Integer.parseInt(request.getParameter("kID"));
		
		
		
		BoardBean boardBean=qModifyFormService.getOneonOnegetArticle(boardNum,kID);
		
		
		
		request.setAttribute("boardBean",boardBean);
		
		forward = new ActionForward();
		forward.setPath("board/QModifyForm.jsp");
		return forward;
	}

}
