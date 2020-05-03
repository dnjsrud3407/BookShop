package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeDetailService;
import board.svc.QDetailService;
import vo.ActionForward;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// Notice 작성한 것을 불러옴
		NoticeDetailService notice_DetailService = new NoticeDetailService();
		notice_DetailService.getArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/NoticeDetail.jsp");
		return forward;
	}

}
