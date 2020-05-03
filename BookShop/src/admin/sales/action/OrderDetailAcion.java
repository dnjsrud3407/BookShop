package admin.sales.action;

import java.util.List;

import static access.Access.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberDetailService;
import admin.member.svc.MemberListService;
import admin.sales.svc.OrderDetailService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		OrderDetailService orderDetailService = new OrderDetailService();
//		OrderBean book = orderDetailService.getOrder(num);
		
//		if(book != null) {
//			reviewDetailService.plusReadcount(board_num);
//		}
		
//		request.setAttribute("book", book);
//		request.setAttribute("page", page); 
    
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String orderNum = request.getParameter("orderNum");
//		System.out.println("memberID : " + members);
		
		OrderDetailService orderDetailService = new OrderDetailService();
		MemberDetailService memberDetailService = new MemberDetailService();
//		List<OrderBean> orderList = orderListService.orderList();
		
		List<OrderBean> order = orderDetailService.selectOrder(orderNum);
		OrderBean orderDetaile = orderDetailService.orderDetaile(orderNum);
		MemberBean member = memberDetailService.getMember(orderDetaile.getOrder_ID());
		System.out.println("status : " + orderDetaile.getOrderStatus());
		request.setAttribute("order", order);
//		System.out.println("ordersize : " + order.size());
		request.setAttribute("orderDetaile", orderDetaile);
//		System.out.println("username : " + orderDetaile.getU_name());
		request.setAttribute("member", member);
//		System.out.println("userID : " + member.getcID());

		
		forward = new ActionForward();
		forward.setPath("./admin/order/order_detail.jsp");
		
		return forward;
	}

}
