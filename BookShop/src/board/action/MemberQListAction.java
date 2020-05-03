package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import vo.ActionForward;

public class MemberQListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		System.out.println("QWriteListAction");
		
		
		forward = new ActionForward();
		forward.setPath("/member/QList.jsp");
		return forward;
		
	}

}
