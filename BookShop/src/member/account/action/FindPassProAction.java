package member.account.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.FindPassProService;
import vo.ActionForward;

public class FindPassProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("FindPassProAction");
		
		String uID = request.getParameter("uID");
		String phone_num = request.getParameter("Phone_num");
		
		System.out.println(uID + ",  " + phone_num );
		
		HashMap hash = new HashMap();
		hash.put("uID", uID);
		hash.put("phone_num",phone_num);
		
		FindPassProService findPassProService = new FindPassProService();
		String findPass = findPassProService.findPass(hash);
		System.out.println(findPass);
		int passOk = 0;
		
		if(findPass != null) {
			passOk = 1;
		} else {
			passOk = -1;
		}
		
		request.setAttribute("findPass", findPass);
		request.setAttribute("uID", uID);
		request.setAttribute("passOk", passOk);
		forward = new ActionForward();
		forward.setPath("/member/findPass.jsp");
		
		return forward;
	}

}
