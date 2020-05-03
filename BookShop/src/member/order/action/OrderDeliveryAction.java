package member.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderDeliveryService;
import vo.ActionForward;

public class OrderDeliveryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		System.out.println("OrderDeliveryAction");
	
		OrderDeliveryService orderDeliveryService =new OrderDeliveryService();
		
		
		
		forward = new ActionForward();
		forward.setPath("/member/deliveryDetail.jsp");
		
		return forward;
	}

}
