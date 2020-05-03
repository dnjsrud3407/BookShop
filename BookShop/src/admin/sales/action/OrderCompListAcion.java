package admin.sales.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.sales.svc.OrderCompListService;
import member.order.svc.OrderListService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderCompListAcion implements Action {
    
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		System.out.println("OrderCompListAcion"); 
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
//		String orderNum = request.getParameter("order_ID");
//		String orderStatus = request.getParameter("orderStatus");
		OrderBean orderStatus = new OrderBean();
		// 담아오기 - 리퀘스트받아온거 페이지 위에 오더빈에 저장하기위함 셋 - 아래없으면 값은 비어있음
		String orderStatus2 = request.getParameter("orderStatus");
		orderStatus.setOrderStatus(request.getParameter("orderStatus"));
		System.out.println("OrderCompListAction ++ OrderCompListAcion orderStatus.setOrderStatus(): " + request.getParameter("orderStatus"));
		System.out.println("OrderCompListAction ++ OrderCompListAcion String orderStatus2 : " + orderStatus2);
		// 삭제필요한지확인
//		admin.sales.svc.OrderListService mind = null;

		OrderCompListService orderCompListService = new OrderCompListService();
//		OrderListService orderListService = new OrderListService();
//		List<OrderBean> orderList = orderListService.orderList();
		List<OrderBean> orderList = orderCompListService.orderComplList(orderStatus);

//		OrderBean order = new OrderBean();
		request.setAttribute("orderList", orderList);
//		request.setAttribute("order", order);
//		System.out.println("orderStatus : " + orderStatus);
		forward = new ActionForward();
		forward.setPath("/admin/order/order_comp_list.jsp");
//		forward.setPath("/admin/order/order_comp_list.jsp?type=" + orderStatus);

		return forward;
	}

}
