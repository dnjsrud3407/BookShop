package member.account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;
import static access.Access.*;
public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("LoginProAction");
		
		String uID = request.getParameter("uID");
		String pw = request.getParameter("pw");
//			
		
		MemberBean member = new MemberBean(uID, pw);
			
		LoginProService LoginService= new LoginProService();
//		memberLoginProService.Login(id,pass);
		
		
		int loginResult = LoginService.isLoginMember(member);

		if(loginResult == -1) {
//			System.out.println("아이디 없음!");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴한 회원입니다!')");
			out.println("history.back()"); 
			out.println("</script>");
			
		} else if(loginResult == -2){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음!')");
			out.println("history.back()"); 
			out.println("</script>");
			
		} else if(loginResult == 0) {
//			System.out.println("패스워드 틀림!");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드 틀림!')");
			out.println("history.back()"); 
			out.println("</script>");
		} else {
			System.out.println("로그인 성공!");
			// 세션을 사용하여 로그인 한 ID 정보를 저장
			// JSP 페이지에서는 내장객체가 존재하므로 session.XXX() 메서드를 호출하면 되지만
			// 자바에서는 HttpSession 인터페이스를 사용하여 request 객체로부터 session 객체를 가져와야함
			HttpSession session = request.getSession();
			// HttpSession 객체의 setAttiribute() 메서드를 호출하여 세션 정보 저장
			session.setAttribute("uID", uID);
			session.setAttribute("isAdmin", isAdmin(session));
			forward = new ActionForward();
			forward.setPath("Main.me");
			forward.setRedirect(true);
			
		}
		
		return forward;
	}

}
