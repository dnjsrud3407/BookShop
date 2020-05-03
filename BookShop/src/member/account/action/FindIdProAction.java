package member.account.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.FindIdProService;
import member.account.svc.FindPassProService;
import vo.ActionForward;

public class FindIdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("FindIdProAction");
		
		String u_name = request.getParameter("u_name");
		String phone_num = request.getParameter("phone_num");
		
		System.out.println("12345 : " +u_name + phone_num);
		
		
		HashMap hash = new HashMap();
		hash.put("u_name", u_name);
		hash.put("phone_num",phone_num);
		
		FindIdProService findIdProService = new FindIdProService();
		String findId = findIdProService.findId(hash);
		System.out.println(findId);
		int idOk = 0;
		
		if(findId != null) {
			idOk = 1;
		} else {
			idOk = -1;
		}
		
		request.setAttribute("findId", findId);
		request.setAttribute("u_name", u_name);
		request.setAttribute("idOk", idOk);
		forward = new ActionForward();
		forward.setPath("/member/findId.jsp");
		
		return forward;
	}

}
