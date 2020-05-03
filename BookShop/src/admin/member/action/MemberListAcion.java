package admin.member.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;
 
public class MemberListAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberListAcion");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
//		int page = 1;
//		int limit = 10;
		
//		if(request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		}
		 
		String uId = request.getParameter("uId");
//		int num = Integer.parseInt(request.getParameter("num"));

		MemberListService memberListService = new MemberListService();
		List<MemberBean> memberList = memberListService.getMemberList();
		
//		ArrayList<MemberBean> memberList = null; 
//		memberList = memberListService.getMemberList(page, limit);
//		
//		int maxPage = (int)((double)memListCount / limit + 0.95);
//		int startPage = (((int)((double)page / 10 + 0.9)) -1 ) * 10 + 1;
//		int endPage = startPage + 10 - 1;
//		
//		if(endPage > maxPage) {
//			endPage = maxPage;
//		}
//		
//		PageInfo pageInfo = new PageInfo(endPage, maxPage, startPage, endPage, memListCount);
//		
//		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("memberList", memberList);
		
		forward = new ActionForward();
		forward.setPath("/admin/member/member_list.jsp");
		
		return forward;
	}

}
