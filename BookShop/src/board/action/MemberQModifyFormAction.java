package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.MemberQModifyFormService;
import vo.ActionForward;

public class MemberQModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("BoardQModifyFormAction");
		
		MemberQModifyFormService boardQModifyFormService = new MemberQModifyFormService();
		
		
		
		
		forward = new ActionForward();
		forward.setPath("/member/QModifyForm.jsp");
		
		return forward;
	}

}
