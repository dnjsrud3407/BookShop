package member.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import vo.ActionForward;
import vo.BoardBean;
import member.book.svc.QModifyProService;;


public class QModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QModifyFormAction");
		// 상품문의 수정

		BoardBean boardBean = null;
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		System.out.println(boardNum);
	
		QModifyProService qModifyProService = new QModifyProService();
		boardBean = qModifyProService.getQuestion(boardNum);
		
		
		ActionForward forward = new ActionForward();
		request.setAttribute("boardBean", boardBean);
		forward.setPath("/book/Q_modify.jsp");
		
		return forward;	
	}

}
