package member.account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.DeleteProService;
import member.account.svc.ModifyFormService;
import member.account.svc.PwCheckBeforeModifyService;
import vo.ActionForward;
import vo.MemberBean;

public class PwCheckBeforeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		System.out.println("PwCheckBeforeModifyAction");
		
		
		
		
		
		
		
		
		forward = new ActionForward();
		forward.setPath("/member/pwCheckBeforeModify.jsp");
		
		return forward;
	}

}
