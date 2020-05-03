package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.FAQWriteProService;
import vo.ActionForward;

public class FAQWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		FAQWriteProService faq_WriteProService = new FAQWriteProService();
		faq_WriteProService.writeArtice();
		
		forward = new ActionForward();
		// FAQ 작성한거 상세보기
		forward.setPath("./board/FAQDetail.jsp");
		
		return forward;
	}

}
