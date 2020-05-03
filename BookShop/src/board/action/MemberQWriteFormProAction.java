package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.MemberQWriteFormProService;
import vo.ActionForward;

public class MemberQWriteFormProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("BoardQWriteFormProAction");
		
		
		MemberQWriteFormProService boardQWriteFormProService = new MemberQWriteFormProService();
		
		forward = new ActionForward();
		forward.setPath("/member/QList.jsp");
		return forward;
	}

}
