package member.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderRefundFormService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderRefundFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("OrderRefundFormAction");
		
		String orderNum=request.getParameter("orderNum");
		int orderDetailNum=Integer.parseInt(orderNum);
		
		
		OrderRefundFormService orderRefundFormService= new  OrderRefundFormService();
		OrderBean orderBean=orderRefundFormService.getDeliveryDetail(orderDetailNum);
		
		
		
		request.setAttribute("orderBean",orderBean);
		
		forward = new ActionForward();
		forward.setPath("/member/orderRefundForm.jsp");
		return forward;
	}

}
