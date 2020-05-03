package admin.sales.action;

import static access.Access.deniedAccess;
import static access.Access.isAdmin;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.sales.svc.OrderCompDetailModifyService;
import admin.sales.svc.OrderModifyProService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderCompDetailModifyPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("OrderModifyProAcion");
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String orderNum = request.getParameter("orderNum");
		OrderCompDetailModifyService orderCompDetailModifyService = new OrderCompDetailModifyService();
//		OrderModifyProService orderModifyProService = new OrderModifyProService();

		OrderBean order = new OrderBean();
		order.setOrderNum(orderNum);
		order.setOrderStatus(request.getParameter("orderStatus"));
		System.out.println("getOrderNum : " + order.getOrderNum());
		System.out.println("DetailForm에서 가져온 getOrderStatus : " + order.getOrderStatus());
		
//      
//		boolean isModifySuccess = orderModifyProService.modifyOrder(order);
		boolean isModifySuccess = orderCompDetailModifyService.modifyOrder(order);
		
		System.out.println("isModifySuccess : " + isModifySuccess);
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
//			out.println("location.href='Login.me'");
			out.println("</script>");
			
		} else {
			
			System.out.println("전 OrderCompList.adm?orderStatus=" + order.getOrderStatus());
//			String status = order.getOrderStatus();
			
//			String status = null;
//			if(order.getOrderStatus().equals("결제완료")) {
//				 status = "paid";
//			} else if(order.getOrderStatus().equals("반품요청")) {
//				 status = "return";
//			}
//			 else if(order.getOrderStatus().equals("교환요청")) {
//				 status = "exchange";
//			}
//			 else if(order.getOrderStatus().equals("취소요청")) {
//				 status = "cencel";
//			}
			
			
			if(order.getOrderStatus().equals("결제완료") || order.getOrderStatus().equals("배송중") || order.getOrderStatus().equals("배송완료")) {
				order.setOrderStatus("결제완료");
			} else if(order.getOrderStatus().equals("취소요청") || order.getOrderStatus().equals("취소완료")) {
				order.setOrderStatus("취소요청");
			} else if(order.getOrderStatus().equals("반품요청") || order.getOrderStatus().equals("반품완료")) {
				order.setOrderStatus("반품요청");
			} else if(order.getOrderStatus().equals("교환요청") || order.getOrderStatus().equals("교환완료") || order.getOrderStatus().equals("배송완료")) {
				order.setOrderStatus("교환요청");
			}
			
			//브라우저 별로 한글처리 
			//User-Agent가 무슨 브라우저스는지 알아오는것 
			    String userAgent = request.getHeader("User-Agent");
			   //그브라우저가 MSIE거나 Trident거나 = MSIE 익스플로러 브라우저이면 트로 / 나머지는 false 
			    boolean ie = (userAgent.indexOf("MSIE") > -1)|| (userAgent.indexOf("Trident") > -1);
			    String sEncoding=null;
			    if(ie) { //fileName이름만 경로처리한거
			    	//익스플로러 브라우저 "\\+", "%20"형태로 정리 > 한글처리
				  sEncoding = URLEncoder.encode(order.getOrderStatus(), "utf-8").replaceAll("\\+", "%20");
				  System.out.println(sEncoding);
			    }else{
			    	//나머지 브라우저 "8859_1"형태로 처리 > 한글처리해서 sEncoding변수에 저장 
				  sEncoding = new String(order.getOrderStatus().getBytes("utf-8"),"8859_1");
				  System.out.println(sEncoding);
			   }
			    
			     
			    
//			String sEncoding = new String(order.getOrderStatus().getBytes("utf-8"),"8859_1");

			forward = new ActionForward();
//			forward.setPath("OrderCompList.adm?orderStatus=" + order.getOrderStatus());
			System.out.println("전 OrderCompList.adm?orderStatus=request.getParameter(\"orderStatus\")" + order.getOrderStatus());
//			forward.setPath("OrderCompList.adm?orderStatus=" + order.getOrderStatus());
			forward.setPath("OrderCompList.adm?orderStatus=" + sEncoding);

//			forward.setPath("OrderCompList.adm?orderStatus=" + status);
//			System.out.println("후 OrderCompList.adm?orderStatus=" + URLEncoder.encode(status, "UTF-8"));
			System.out.println("후 OrderCompList.adm?orderStatus=" + order.getOrderStatus());

//			forward.setPath("OrderDetail.adm?orderNum=" + orderNum + "&orderStatus=" + request.getParameter("orderStatus"));
			forward.setRedirect(true);
		}
//	}
	
	System.out.println("이동 경로 : " + forward.getPath());
	
	return forward;
	}
	

}
