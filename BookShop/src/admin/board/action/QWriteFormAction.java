package admin.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import static access.Access.*;
public class QWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("Q_WriteFormAction");
		// 사용자가 1:1 작성한 것을 불러옴
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		forward = new ActionForward();
		forward.setPath("/board/QWriteForm.jsp");
		return forward;
	}

}
