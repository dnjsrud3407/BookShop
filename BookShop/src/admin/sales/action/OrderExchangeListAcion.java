package admin.sales.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.sales.svc.OrderExchangeListService;
import member.order.svc.OrderListService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderExchangeListAcion implements Action {
   
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderExchangeListAcion");
		ActionForward forward = null;
		//		int num = Integer.parseInt(request.getParameter("num")); 
		//
		//		OrderExchangeListService orderExchangeListService = new OrderExchangeListService();
		//		OrderBean order = orderExchangeListService.getOrder(num);
				
		ActionForward action = new ActionForward();
		String orderNum = request.getParameter("order_ID");
		String orderStatus = request.getParameter("orderStatus");
		
		//삭제필요한지확인
		//admin.sales.svc.OrderListService mind = null;
		
		
		OrderListService orderListService = new OrderListService();
		List<OrderBean> orderList = orderListService.orderList();
		//OrderBean order = new OrderBean();
		request.setAttribute("orderList", orderList);
		//request.setAttribute("order", order);
		
		forward = new ActionForward();
		forward.setPath("/admin/order/order_ex_list.jsp");
		
		return forward;
	}

}
