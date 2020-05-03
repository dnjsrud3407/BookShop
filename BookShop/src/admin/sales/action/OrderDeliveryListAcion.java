package admin.sales.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.sales.svc.OrderDeliveryListService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderDeliveryListAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderDeliveryListAcion");
		
//		int num = Integer.parseInt(request.getParameter("num"));
//
//		OrderDeliveryListService orderDeliveryListService = new OrderDeliveryListService();
//		OrderBean order = orderDeliveryListService.getOrder(num);
		
		ActionForward action = new ActionForward();
		action.setPath("/admin/order/order_delivery_list.jsp");
		
		return action;
	}

}
