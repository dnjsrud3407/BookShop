package admin.sales.action;

import java.util.ArrayList;
import java.util.List;

import static access.Access.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.ForwardingFileObject;

import action.Action;
import member.order.svc.OrderListService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderListAcion implements Action {
    
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderListAcion");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		
//		int num = Integer.parseInt(request.getParameter("num"));
////
//		OrderListService orderListService = new OrderListService();
//		OrderBean order = orderListService.getOrder(num);
		
		ActionForward action = new ActionForward();
//		String uId = request.getParameter("order_ID");
		String orderNum = request.getParameter("orderNum");

		
		//삭제필요한지확인
//		admin.sales.svc.OrderListService mind = null;
		
	
		OrderListService orderListService = new OrderListService();
		List<OrderBean> orderList = orderListService.orderList();
		System.out.println(orderList);
		request.setAttribute("orderList", orderList);
		
		forward = new ActionForward();
		forward.setPath("/admin/order/order_list.jsp");

		return forward;
	}

}
