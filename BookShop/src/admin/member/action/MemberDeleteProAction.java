package admin.member.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.memberDeleteProService;
import vo.ActionForward;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDeleteProAction");
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String uID = request.getParameter("uID");
//		String page = request.getParameter("page");
////		
		memberDeleteProService memberDeleteProService = new memberDeleteProService();
//		boolean isAdminUser = memberDeleteProService.isAdminWriter(uID, request.getParameter("pw"));

//		String[] strAdmin = {"admin","admin1","admin2","admin3","admin4"};
				
//		if(uID.equals(strAdmin)) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//	     	out.println("alert('삭제 권한이 없습니다!')");
//			out.println("history.back()");
//			out.println("</script>");
//		} else {
////			삭제작업진행 
//			System.out.println("삭제 완료!");
			boolean isDeleteSuccess = memberDeleteProService.removeMember(uID);
//			
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 삭제 실패!!')");
				out.println("histroy.back()");
				out.println("</script>");

			}else {				
				forward = new ActionForward();
				forward.setPath("MemberList.adm");
				forward.setRedirect(true);
			}
//		}
		
		forward = new ActionForward();
		forward.setPath("MemberList.adm");
		forward.setRedirect(true);

		return forward;

	}

}
