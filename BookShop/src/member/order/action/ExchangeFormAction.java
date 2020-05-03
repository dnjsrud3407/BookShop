package member.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderExchangeFormService;
import vo.ActionForward;
import vo.OrderBean;

public class ExchangeFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		System.out.println("ExchangeFormAction");
		
		String orderNum=request.getParameter("orderNum");
		int orderDetailNum=Integer.parseInt(orderNum);
		//OrderListAction Service와ㅏ 같음 아직수정하진않을것
		OrderExchangeFormService exchangeFormService = new OrderExchangeFormService();
		OrderBean orderBean=exchangeFormService.getDeliveryDetail(orderDetailNum);
		
		
		request.setAttribute("orderBean",orderBean);
		forward = new ActionForward();
		forward.setPath("member/OrderExchangeForm.jsp");
		return forward;
	}

}
