package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeWriteProService;
import vo.ActionForward;

public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		NoticeWriteProService notice_WriteProService = new NoticeWriteProService();
		notice_WriteProService.writeArtice();
		
		forward = new ActionForward();
		// Notice 작성한거 상세보기
		forward.setPath("./board/NoticeDetail.jsp");
		
		return forward;
	}

}
