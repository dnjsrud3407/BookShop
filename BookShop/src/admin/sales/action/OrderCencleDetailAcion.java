package admin.sales.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.member.svc.MemberDetailService;
import admin.sales.svc.OrderCencelDetailService;
import admin.sales.svc.OrderDetailService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderCencleDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderCencleDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		OrderCencelDetailService orderCencelDetailService = new OrderCencelDetailService();
//		OrderBean order = orderCencelDetailService.getOrder(num);
//		
//		if(book != null) {
//			orderCencelDetailService.plusReadcount(board_num);
//		}
		
//		request.setAttribute("book", book);
//		request.setAttribute("page", page);

		ActionForward forward = null;
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
		forward.setPath("./admin/order/order_cancel_detail.jsp");
		
		return forward;
	}

}
