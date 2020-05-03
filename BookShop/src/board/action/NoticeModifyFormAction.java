package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeDetailService;
import vo.ActionForward;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("FAQ_ModifyFormAction");
		
		// 사용자가 Notice 작성한 것을 불러옴
		NoticeDetailService notice_DetailService = new NoticeDetailService();
		notice_DetailService.getArticle();
		
		forward = new ActionForward();
		forward.setPath("./board/NoticeModifyForm.jsp");
		
		return forward;
	}

}
