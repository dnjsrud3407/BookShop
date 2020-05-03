package member.account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.svc.JoinCheckService;
import vo.ActionForward;

public class JoinCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
		//String id = wid 파라미터 가져오기
		String uID=request.getParameter("uID");
		boolean flag = true;
		String str ="";

		if(uID == null){
			uID ="";
		}
		
		JoinCheckService joinCheckService = new JoinCheckService();
		
		if(!uID.equals("")){
			flag = joinCheckService.idCheck(uID);
		}

		if(flag){ // 이미 존재하는 계정
			str = "NO";
			out.print(str);
		} else { // 사용가능한 계정
			str = "YES";
			out.print(str);
		}
		
		return null;
	}

}
