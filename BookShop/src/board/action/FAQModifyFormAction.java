package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.FAQDetailService;
import vo.ActionForward;

public class FAQModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("FAQ_ModifyFormAction");
		
		// 사용자가 FAQ 작성한 것을 불러옴
		FAQDetailService faq_DetailService = new FAQDetailService();
		faq_DetailService.getArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/FAQModifyForm.jsp");
		
		return forward;
	}

}
