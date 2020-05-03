package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.FAQDeleteProService;
import board.svc.QDeleteProService;
import vo.ActionForward;

public class FAQDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		FAQDeleteProService faq_DeleteProService = new FAQDeleteProService();
		faq_DeleteProService.deleteArticle();
		
		forward = new ActionForward();
		forward.setPath("FAQList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
