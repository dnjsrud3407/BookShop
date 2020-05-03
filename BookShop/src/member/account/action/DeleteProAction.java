package member.account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.DeleteProService;
import vo.ActionForward;

public class DeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("DeleteProAction");
		
		String pw=request.getParameter("pw");
		HttpSession session = request.getSession();
		String uID=(String) session.getAttribute("uID");
		
		DeleteProService deleteProService=new DeleteProService();
		
		int pwCheck=deleteProService.checkPass(uID,pw);
		
		if (pwCheck>0) {
			session.invalidate();
			System.out.println("pwcheck =1로 뜸");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('회원정보 탈퇴 완료')");
			out.println("</script>"); 
			forward = new ActionForward();
			forward.setPath("index.jsp");
			forward.setRedirect(true);
		}else {
			System.out.println("pwcheck =0로 뜸");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('비밀번호 틀림')");
			out.println("history.back()");
			out.println("</script>"); 
		}
		

		return forward;
	}

}
