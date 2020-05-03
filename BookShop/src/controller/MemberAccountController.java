package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.account.action.CouponInfoAction;
import member.account.action.DeleteProAction;
import member.account.action.FindIdProAction;
import member.account.action.FindPassProAction;
import member.account.action.JoinCheckAction;
import member.account.action.JoinProAction;
import member.account.action.LoginProAction;
import member.account.action.LogoutProAction;
import member.account.action.MainAction;
import member.account.action.ModifyFormAction;
import member.account.action.ModifyProAction;
import member.account.action.OffInfoAction;
import member.account.action.PointInfoAction;
import member.account.action.SearchCouponProAction;
import member.account.action.SearchPointProAction;
import member.account.action.SearchProAction;
import member.account.action.SearchProCanCelAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberAccountController extends HttpServlet {
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println(command);
		
		
		Action action = null;
	ActionForward forward = null;
	if (command.equals("/Main.me")) {//
		action = new MainAction();
		
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/JoinCheck.me")){
		action = new JoinCheckAction();
			try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}else if (command.equals("/Login.me")) {//
		forward = new ActionForward();
		forward.setPath("/member/login.jsp");
		
	}else if(command.equals("/LoginPro.me")) {//
		
		action = new LoginProAction();
		
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/LogoutPro.me")) {//占싸그아울옙
		
		action = new LogoutProAction();
		
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/FindId.me")) {//占쏙옙占싱듸옙찾占쏙옙 占쏙옙
		forward = new ActionForward();
		forward.setPath("/member/findId.jsp");
		
	}else if(command.equals("/FindIdPro.me")) {//占쏙옙占싱듸옙찾占쏙옙 占쌓쇽옙
		action = new FindIdProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/FindPass.me")) {//
		forward = new ActionForward();
		forward.setPath("/member/findPass.jsp");
		
	}else if(command.equals("/FindPassPro.me")) {//
		action = new FindPassProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/JoinForm.me")) {//�쉶�썝媛��엯
		forward = new ActionForward();
		forward.setPath("/member/join.jsp");
		
	}else if(command.equals("/JoinPro.me")) {//
		forward = new ActionForward();
		action = new JoinProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/OffInfo.me")) {//
		action = new OffInfoAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/DeleteForm.me")) {//
		forward = new ActionForward();
		forward.setPath("/member/delete.jsp"); //
		
	}else if(command.equals("/DeletePro.me")) {
		action = new DeleteProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else if(command.equals("/Modify.me")) {//
		action = new ModifyFormAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/ModifyProAction.me")) {//
		action = new ModifyProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/ModifyFormAction.me")) {//
		action = new ModifyFormAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/PwCheckBeforeModifyForm.me")) {//
		forward=new ActionForward();
		forward.setPath("/member/pwCheckBeforeModify.jsp");
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/PwCheckBeforeModifyPro.me")) {//
		action = new ModifyFormAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/CouponInfoAction.me")) {//
		action = new CouponInfoAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/PointInfoAction.me")) {//
		action = new PointInfoAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/SearchProCanCelAction.me")) {//
		action = new SearchProCanCelAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/SearchProAction.me")) {//
		action = new SearchProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/SearchPointProAction.me")) {//
		action = new SearchPointProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}else if(command.equals("/SearchCouponProAction.me")) {//
		action = new SearchCouponProAction();
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
		 
		if (forward!=null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}else {
			System.out.println("ActionFoward媛앹껜 �꼸");
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	
	
	
}
