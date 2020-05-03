package admin.sales.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberModifyProService;
import admin.sales.svc.OrderModifyProService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderModifyProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderModifyProAcion");
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String orderNum = request.getParameter("orderNum");

		OrderModifyProService orderModifyProService = new OrderModifyProService();

		OrderBean order = new OrderBean();
		order.setOrderNum(orderNum);
		order.setOrderStatus(request.getParameter("orderStatus"));
		System.out.println("getOrderNum : " + order.getOrderNum());
		System.out.println("DetailForm에서 가져온 getOrderStatus : " + order.getOrderStatus());
//      
		
		boolean isModifySuccess = orderModifyProService.modifyOrder(order);
		System.out.println("isModifySuccess : " + isModifySuccess);
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
//			out.println("location.href='Login.me'");
			out.println("</script>");
		} else {
			 
			forward = new ActionForward();
//			forward.setPath("OrderCompList.adm?orderStatus=" + order.getOrderStatus());
			forward.setPath("OrderList.adm?orderStatus=" + request.getParameter("orderStatus"));

//			forward.setPath("OrderDetail.adm?orderNum=" + orderNum + "&orderStatus=" + request.getParameter("orderStatus"));
			forward.setRedirect(true);
		}
//	}
	
	return forward;
	}

}
