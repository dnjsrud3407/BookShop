package member.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import member.book.svc.CartQtyChangeService;
import vo.ActionForward;
import vo.CartBean;


public class CartQtyChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartQtyChangeAction");
		
		ActionForward forward = null;
		
		int cartNum = Integer.parseInt(request.getParameter("cartNum"));
		System.out.println(cartNum);
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.println(qty);
		
		
		CartQtyChangeService cartQtyChangeService = new CartQtyChangeService();
		cartQtyChangeService.qtyChange(cartNum, qty, request);
		
		
		forward = new ActionForward(); 
		forward.setPath("CartList.book"); 
		forward.setRedirect(true);
		return forward;
	}

}
