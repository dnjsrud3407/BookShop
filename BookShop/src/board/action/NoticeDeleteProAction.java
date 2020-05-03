package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeDeleteProService;
import vo.ActionForward;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		NoticeDeleteProService notice_DeleteProService = new NoticeDeleteProService();
		notice_DeleteProService.deleteArticle();
		
		forward = new ActionForward();
		forward.setPath("NoticeList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
