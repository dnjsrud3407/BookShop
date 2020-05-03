package member.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderConFirmProService;
import member.order.svc.OrderExchangeProService;
import vo.ActionForward;

public class OrderConFirmProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		String changeOrderStatus = "구매확정";
		
		System.out.println("OrderConFirmProAction");
		
		String orderNum=request.getParameter("orderNum");
		

		OrderConFirmProService orderConFirmProService = new OrderConFirmProService();
		int right=orderConFirmProService.updateOrderStatus(orderNum,changeOrderStatus);
		
		forward = new ActionForward(); 
		forward.setPath("OrderList.mo");
		forward.setRedirect(true);
		

		return forward;
	}

}
