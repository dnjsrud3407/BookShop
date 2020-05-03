package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.MemberQModifyProService;
import vo.ActionForward;

public class MemberQModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("BoardQModifyProAction");
		
		
		MemberQModifyProService boardQModifyProService = new MemberQModifyProService();
		
		
		
		forward = new ActionForward();
		forward.setPath("/member/QList.jsp");
		return forward;
	}

}
