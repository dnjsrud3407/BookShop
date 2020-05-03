package admin.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;


public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberModifyFormAction");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//		
//		MemberBean member = null;
//		MemberModifyProService memberModifyProService = new MemberModifyProService();
//		member = memberModifyProService.getMember(num);
	
//		request.setAttribute("article", article);
//		request.setAttribute("page", page);

		ActionForward forward = new ActionForward();
		forward.setPath("/admin/member/member_modify.jsp");
		
		return forward;
	}

}
