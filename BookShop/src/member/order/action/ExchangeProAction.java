package member.order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.order.svc.OrderExchangeProService;
import vo.ActionForward;

public class ExchangeProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String changeOrderStatus = "반품요청";
//		String changeOrderStatus = "교환";
//		String changeOrderStatus = "취소";
//		String changeOrderStatus = "확정";
		
		System.out.println("ExchangeProAction");
		
		String orderNum=request.getParameter("orderNum");
		
		
		System.out.println("ExchangeProAction"+orderNum);
		
		
		
		OrderExchangeProService exchangeProService = new OrderExchangeProService();
		int right=exchangeProService.updateOrderStatus(orderNum,changeOrderStatus);
		
		
		if(right == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('반품신청완료!')");
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
