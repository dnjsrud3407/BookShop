package admin.sales.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.member.svc.MemberDetailService;
import admin.sales.svc.OrderDetailService;
import admin.sales.svc.OrderExchangeDetailService;
import vo.ActionForward;
import vo.MemberBean;
import vo.OrderBean;

public class OrderExchangeDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderDeliveryDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		OrderExchangeDetailService orderExchangeDetailService = new OrderExchangeDetailService();
//		OrderBean order = orderExchangeDetailService.getOrder(num);
//		
//		if(book != null) {
//			orderExchangeDetailService.plusReadcount(board_num);
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
		forward.setPath("./admin/order/order_ex_detail.jsp");
		
		return forward;
	}

}
