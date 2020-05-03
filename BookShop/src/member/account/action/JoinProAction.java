package member.account.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.JoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
		System.out.println("JoinProAction");
		
		// 파라미터 가져오기
		String uID = request.getParameter("uID");
		String pw = request.getParameter("pw");
		String u_name = request.getParameter("u_name");
		String address_post = request.getParameter("address_post");
		String address_1 = request.getParameter("address_1");
		String address_2 = request.getParameter("address_2");
		String phone_num = request.getParameter("phone_num");
		String email = request.getParameter("email");
		String withdrawal = "n";
		
		int point = 0;
		int grade = Integer.parseInt(request.getParameter("grade"));
		System.out.println(uID);
		// 파라미터 -> MemberBean 객체에 저장
		MemberBean member = new MemberBean(uID, pw, u_name, address_post, address_1, address_2, phone_num, email, point, grade, withdrawal);
				
		// MemberJoinProService 클래스의 joinMember() 메서드 호출하여 추가 작업 요청
				// => 파라미터 : MemberBean      리턴타입 : boolean
		
		JoinProService joinService = new JoinProService();
		
		boolean isJoinSuccess = joinService.joinMember(member);
	
		if(!isJoinSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
			// out 객체의 println() 메서드를 호출하여 HTML 태그 작성
			out.println("<script>"); // 자바스크립트 실행을 위한 <script> 시작 태그
			out.println("alert('회원 가입 실패!')"); // alert dialog 출력
			out.println("history.back()"); // 또는 out.println("history.go(-1)");  // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 종료 위한 <script> 끝 태그
		} else {		
			forward = new ActionForward();
			forward.setPath("");
			forward.setRedirect(true);
		
		}
		return forward;
	}

}
