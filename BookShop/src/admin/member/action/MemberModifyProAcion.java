package admin.member.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberModifyProService;
import vo.ActionForward;
import vo.MemberBean;


public class MemberModifyProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberModifyProAcion");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String uID = request.getParameter("uID");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");

//		boolean isRightUser = false;
		
		MemberModifyProService memberModifyProService = new MemberModifyProService();
//		boolean isRightAdmin = memberModifyProService.isAdminWriter(uID, request.getParameter("pw"));
//		
//		if(!isRightAdmin) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
//			// out 객체의 println() 메서드를 호출하여 HTML 태그 작성
//			out.println("<script>"); // 자바스크립트 실행을 위한 <script> 시작 태그
//			out.println("alert('수정 권한이 없습니다!')"); // alert dialog 출력
//			out.println("history.back()");// 이전 페이지로 돌아가기
//			out.println("</script>"); // 자바스크립트 종료
//		} else {
			
			MemberBean member = new MemberBean();
			member.setuID(uID);
			member.setPoint(Integer.parseInt(request.getParameter("point")));
			member.setGrade(Integer.parseInt(request.getParameter("grade")));
			System.out.println(member.getuID());

			System.out.println(member.getPoint());
			System.out.println(member.getGrade());

			boolean isModifySuccess = memberModifyProService.modifyMember(member);
		
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패!')");
				out.println("history.back()");
//				out.println("location.href='Login.me'");
				out.println("</script>");
			} else {
				
				forward = new ActionForward();
				forward.setPath("MemberDetail.adm?uID=" + uID);
				forward.setRedirect(true);
			}
//		}
		
		return forward;

	}

}
