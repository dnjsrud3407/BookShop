package member.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class LogoutProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("LogoutProAction");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		forward = new ActionForward();
		forward.setPath("");
		forward.setRedirect(true);
		
		return forward;
	}

}
