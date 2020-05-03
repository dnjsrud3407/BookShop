package admin.sales.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.sales.svc.OrderRefundListService;
import member.order.svc.OrderListService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderRefundListAcion implements Action {
    
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("OrderRefundListAcion");
		ActionForward forward = null;

//		int num = Integer.parseInt(request.getParameter("num"));
//
//		OrderRefundListService OoderRefundListService = new OrderRefundListService();
//		OrderBean order = OoderRefundListService.getOrder(num);
		
		ActionForward action = new ActionForward();
		String orderNum = request.getParameter("order_ID");
		String orderStatus = request.getParameter("orderStatus");

		//삭제필요한지확인
//		admin.sales.svc.OrderListService mind = null;
		
	
		OrderListService orderListService = new OrderListService();
		List<OrderBean> orderList = orderListService.orderList();
//		OrderBean order = new OrderBean(); 
		request.setAttribute("orderList", orderList);
//		request.setAttribute("order", order);

		forward = new ActionForward();
		forward.setPath("/admin/order/order_refund_list.jsp");
		
		return forward;
	}

}
