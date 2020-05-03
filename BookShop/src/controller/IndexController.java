package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.board.action.EventDeleteAction;
import admin.board.action.EventDetailAction;
import admin.board.action.EventListAction;
import admin.board.action.EventModifyFormAction;
import admin.board.action.EventModifyProAction;
import admin.board.action.EventWriteProAction;
import admin.board.action.FAQDeleteProAction;
import admin.board.action.FAQModifyFormAction;
import admin.board.action.FAQModifyProAction;
import admin.board.action.FAQWriteFormAction;
import admin.board.action.FAQWriteProAction;
import admin.board.action.NoticeDeleteProAction;
import admin.board.action.NoticeDetailAction;
import admin.board.action.NoticeModifyProAction;
import admin.board.action.NoticeWriteProAction;
import admin.board.action.QDeleteProAction;
import admin.board.action.QDetailAction;
import admin.board.action.QListAction;
import admin.board.action.QListNAAction;
import admin.board.action.QModifyProAction;
import admin.board.action.FAQListAction;
import admin.board.action.NoticeListAction;
import admin.board.action.NoticeModifyFormAction;
//import admin.board.action.MemberQListAction;
import admin.board.action.QWriteProAction;
//import admin.board.action.MemberQdetailAction;
import vo.ActionForward;

@WebServlet("/Main")
public class IndexController extends HttpServlet {
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 서블릿 주소에 따라 각각 다른 작업을 수행
		if (command.equals("/Main")) {//	1:1 문의 답변 작성 폼
			forward = new ActionForward();
			
			forward.setPath("./index.jsp");
		} 
	
		

		//=================================================================================
        // ActionForward 객체의 포워딩 방식
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}else {
			System.out.println("ActionFoward 이 null입니다");
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
