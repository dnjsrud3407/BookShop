package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.member.action.AdminMainAction;
import admin.member.action.MainBoardAction;
import admin.member.action.MemberDeleteProAction;
import admin.member.action.MemberDetailAcion;
import admin.member.action.MemberListAcion;
import admin.member.action.MemberModifyProAcion;
import admin.sales.action.OrderCencleDetailAcion;
import admin.sales.action.OrderCompDetailAcion;
import admin.sales.action.OrderCompDetailModifyPro;
import admin.sales.action.OrderCompListAcion;
import admin.sales.action.OrderCancleListAcion;
import admin.sales.action.OrderDeliveryDetailAcion;
import admin.sales.action.OrderDeliveryListAcion;
import admin.sales.action.OrderDetailAcion;
import admin.sales.action.OrderExchangeDetailAcion;
import admin.sales.action.OrderExchangeListAcion;
import admin.sales.action.OrderListAcion;
import admin.sales.action.OrderModifyProAcion;
import admin.sales.action.OrderRefundDetailAcion;
import admin.sales.action.OrderRefundListAcion;
import vo.ActionForward;


@WebServlet("*.adm")
public class AdminMemberController extends HttpServlet {       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 
		String command = request.getServletPath();
		System.out.println("BFC - command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/AdminMain.adm")) { //------------------
			action = new AdminMainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MainBoard.adm")) { //-------- adminMain에서 ajax으로 들고오기
			action = new MainBoardAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberList.adm")) { //------------------
			action = new MemberListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		else if(command.equals("/MemberModifyForm.adm")) {
//			action = new MemberModifyFormAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}		
//		} 
	else if(command.equals("/MemberModifyPro.adm")) { //------------------
			action = new MemberModifyProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberDetail.adm")) { //------------------
			action = new MemberDetailAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
	
		} 
//		else if(command.equals("/MemberDeleteForm.adm")){
//			forward = new ActionForward();
//			forward.setPath("./admin/member/member_delete.jsp");
//			
//		} 
		else if(command.equals("/MemberDeletePro.adm")) { //------------------
			action = new MemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		// 매출관리 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//		else if(command.equals("/Order.adm")) { //------------------
//			forward = new ActionForward();
//			forward.setPath("./admin/order/order.jsp");
//
//		} 
//		else if(command.equals("/OrderList.adm")) {
//			forward = new ActionForward();
//			forward.setPath("./admin/order/order_list.jsp");
//
//		} 
		 else if(command.equals("/OrderList.adm")) { //------------------ 
			action = new OrderListAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/OrderDetail.adm")) { //------------------//
			System.out.println("---OrderDetail.adm");
			action = new OrderDetailAcion();
			
			try { 
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		} 
		
		else if(command.equals("/OrderModifyPro.adm")) { //------------------
			action = new OrderModifyProAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		else if(command.equals("/Sales.adm")) {
//			forward = new ActionForward();
//			forward.setPath("./admin/order/sales.jsp");
//
//		}
		// cancel / exchange / refund / delivery >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Details>>>>>>>>>>>>>>>>>>>>>
//		else if(command.equals("/OrderDeliveryDetail.adm")) {
//			action = new OrderDeliveryDetailAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			
//		} 
//		else if(command.equals("/OrderCencleDetail.adm")) {//---------------
//			action = new OrderCencleDetailAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			
//		} else if(command.equals("/OrderRefundDetail.adm")) {//---------------
//			action = new OrderRefundDetailAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			
//		} else if(command.equals("/OrderExchangeDetail.adm")) {//---------------
//			action = new OrderExchangeDetailAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//
//		}
		
		 else if(command.equals("/OrderCompDetail.adm")) { //---------------
			action = new OrderCompDetailAcion();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		 else if(command.equals("/OrderCompDetailModifyPro.adm")) { //------------------
				action = new OrderCompDetailModifyPro();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		// cancel / exchange / refund / delivery >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Lists>>>>>>>>>>>>

//		else if(command.equals("/OrderDeliveryList.adm")) {
//			action = new OrderDeliveryListAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} 
//		else if(command.equals("/OrderCencleList.adm")) { //------------------
//			action = new OrderCancleListAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} 
//		else if(command.equals("/OrderRefundList.adm")) {//------------------
//			action = new OrderRefundListAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if(command.equals("/OrderExchangeList.adm")) {//------------------
//			action = new OrderExchangeListAcion();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) { 
//				e.printStackTrace();
//			}
//		} 
		else if(command.equals("/OrderCompList.adm")) { //------------------
			System.out.println("orderStatus AdminMemberControlloer : " + request.getParameter("orderStatus"));
			action = new OrderCompListAcion();
			try {    
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}else {
			System.out.println("-- ActionForward object 값이 null입니 !!!! --");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}