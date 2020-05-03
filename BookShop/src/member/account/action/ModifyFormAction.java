package member.account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.ModifyFormService;
import vo.ActionForward;
import vo.MemberBean;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("ModifyFormActionddddddddddddddddddddddddddd");
		
		String pw=request.getParameter("pw");
		HttpSession session = request.getSession();
		String uID=(String) session.getAttribute("uID");
		
		
		ModifyFormService modifyFormService =new ModifyFormService();
		int pwCheck=modifyFormService.checkPass2(uID,pw);
		
		
		
		if (pwCheck>0) {
			
			MemberBean memberBean=modifyFormService.getMemberInfo(uID);
			request.setAttribute("memberBean",memberBean);
			forward = new ActionForward();
			forward.setPath("/member/modify.jsp");
			
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('비밀번호를 확인 하세요')");
			out.println("history.back()");
			out.println("</script>"); 
		}
		
		
		
		
		return forward;
	}

}
