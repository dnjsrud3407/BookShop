package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.FAQDetailService;
import board.svc.QDetailService;
import vo.ActionForward;

public class FAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// FAQ 작성한 것을 불러옴
		FAQDetailService faq_DetailService = new FAQDetailService();
		faq_DetailService.getArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/FAQDetail.jsp");
		return forward;
	}

}
