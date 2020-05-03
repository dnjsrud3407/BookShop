package access;

import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class Access {
	
	public static boolean isAdmin(HttpSession session) {
	
		boolean result = false;
		
		String uID = (String)session.getAttribute("uID");
		
		if(uID != null) {
			// access 패키지의 MemberCheckService 클래스
			MemberCheckService MCS = new MemberCheckService();
			int memberGrade = MCS.getMemberGrade(uID);
			if(memberGrade > 0 && memberGrade < 6) {
				result = true;
			}
		}
		
		return result;
	}
	
	public static ActionForward deniedAccess(HttpSession session) {
		ActionForward forward = new ActionForward();
		session.setAttribute("errMsg", "경고 : 해당 페이지 접근권한 없음!");
		forward.setPath("/errMsg.jsp");

		return forward;
	}
}
