package admin.sales.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.member.svc.MemberDetailService;
import admin.sales.svc.OrderCompDetailService;
import admin.sales.svc.OrderDetailService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderCompDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderCompDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				    
//		OrderCompDetailService orderCompDetailService = new OrderCompDetailService();
//		OrderBean order = orderCompDetailService.getOrder(num);
////		
//		if(book != null) {
//			orderCompDetailService.plusReadcount(board_num);
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

		OrderDetailService orderDetailService = new OrderDetailService();
		MemberDetailService memberDetailService = new MemberDetailService();
		
		List<OrderBean> order = orderDetailService.selectOrder(orderNum);
		
		OrderBean orderDetaile = orderDetailService.orderDetaile(orderNum);
		MemberBean member = memberDetailService.getMember(orderDetaile.getOrder_ID());
		
		request.setAttribute("order", order);
		request.setAttribute("orderDetaile", orderDetaile);
		request.setAttribute("member", member);

		forward = new ActionForward();
		forward.setPath("./admin/order/order_comp_detail.jsp");
		
		return forward;
	}

}
