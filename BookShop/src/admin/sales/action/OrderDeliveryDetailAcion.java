package admin.sales.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.sales.svc.OrderDeliveryDetailService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderDeliveryDetailAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderDeliveryDetailAcion");
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		OrderDeliveryDetailService orderDeliveryDetailService = new OrderDeliveryDetailService();
//		OrderBean order = orderDeliveryDetailService.getOrder(num);
//		
//		if(book != null) {
//			orderDeliveryDetailService.plusReadcount(board_num);
//		}
		
//		request.setAttribute("book", book);
//		request.setAttribute("page", page);

		ActionForward forward = null;
		
		forward = new ActionForward();
		forward.setPath("./admin/order/order_delivery_detail.jsp");
		
		return forward;
	}

}
