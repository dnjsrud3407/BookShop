package member.order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.order.svc.OrderRefundProService;
import vo.ActionForward;

public class OrderRefundProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("OrderRefundProAction");
		String changeOrderStatus = "교환요청";
		
		
		String orderNum=request.getParameter("orderNum");
		
		System.out.println("OrderRefundProAction"+orderNum);
		
		
		
		OrderRefundProService orderRefundProService= new OrderRefundProService();
		int right=orderRefundProService.updateOrderStatus(orderNum,changeOrderStatus);
		
		if(right == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('교환신청완료!')");
			out.println("self.close()"); 
			out.println("</script>");
		} else if(right == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류발생!')");
			out.println("history.back()"); 
			out.println("</script>");
		} 
		
		return null;
	}

}
