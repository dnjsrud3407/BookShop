package admin.member.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberDetailService;
import vo.ActionForward;
import vo.MemberBean;


public class MemberDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		ReviewDetailService reviewDetailService = new ReviewDetailService();
//		BookBean book = reviewDetailService.getBook(num);
		
//		if(book != null) {
//			reviewDetailService.plusReadcount(board_num);
//		}
		
//		request.setAttribute("book", book);
//		request.setAttribute("page", page);
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String members = request.getParameter("uID");
//		String user = (String)session.getAttribute("user");
		MemberDetailService memberDetailService = new MemberDetailService();
		MemberBean member = memberDetailService.getMember(members);
		
//		if(member != null) {
//			boardDetailService.plusReadcount(board_num);
//		}
		
		request.setAttribute("member", member);
		
		forward = new ActionForward();
		forward.setPath("./admin/member/member_detail.jsp");
		
		return forward;

	}

}
