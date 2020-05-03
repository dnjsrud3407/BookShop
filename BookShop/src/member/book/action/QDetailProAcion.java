package member.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.book.svc.QDeleteProService;
import member.book.svc.QDetailService;
import member.book.svc.ReviewDetailService;
import vo.ActionForward;
import vo.BookBean;

public class QDetailProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QDetailProAcion");
		
//		QDetailService qDetailService = new QDetailService();
//		BookBean book = qDetailService.getQuestion(num);
		
//		if(book != null) {
//			reviewDetailService.plusReadcount(board_num);
//		}
		
		
//		request.setAttribute("book", book);
//		request.setAttribute("page", page);

		ActionForward forward = null;
		
		forward = new ActionForward();
		forward.setPath("./book/Q_detail.jsp");
		
		return forward;
	}

}
