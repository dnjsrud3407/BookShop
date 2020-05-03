package member.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class OrderQListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("OrderQListAction");
		
		forward = new ActionForward();
		forward.setPath("/member/orderQList.jsp");
		
		return forward;
	}

}
