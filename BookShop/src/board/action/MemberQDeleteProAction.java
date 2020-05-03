package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.MemberQDeleteProService;
import vo.ActionForward;

public class MemberQDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("BoardQDeleteAction");
	
		
		
		MemberQDeleteProService boardQDeleteService = new MemberQDeleteProService();
		
		
		forward = new ActionForward();
		forward.setPath("/member/QList.jsp");
		return forward;
	}

}
