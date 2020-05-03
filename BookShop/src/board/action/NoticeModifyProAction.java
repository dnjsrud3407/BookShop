package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeModifyProService;
import vo.ActionForward;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		NoticeModifyProService notice_ModifyProService = new NoticeModifyProService();
		notice_ModifyProService.modifyArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/NoticeDetail.jsp");
		
		return forward;
	}

}
