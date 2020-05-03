package member.order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderCancelProService;
import vo.ActionForward;

public class OrderCancelProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		System.out.println("OrderCancelProAction");
		String orderNum=request.getParameter("orderNum");
		String changeOrderStatus = "취소요청";
		
		
		OrderCancelProService  orderCancelProService = new OrderCancelProService();
		int right=orderCancelProService.updateOrderStatus(orderNum,changeOrderStatus);
		
		
		
		
		if(right == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('취소신청 완료!')");
			forward = new ActionForward(); 
			forward.setPath("OrderList.mo");
			forward.setRedirect(true);
			out.println("</script>");
		} else if(right == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류발생!')");
			out.println("history.back()"); 
			out.println("</script>");
		} 
	
		return forward;
	}

}
