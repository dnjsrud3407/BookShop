package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.FAQModifyProService;
import board.svc.QModifyProService;
import vo.ActionForward;

public class FAQModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		FAQModifyProService faq_ModifyProService = new FAQModifyProService();
		faq_ModifyProService.modifyArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/FAQDetail.jsp");
		
		return forward;
	}

}
