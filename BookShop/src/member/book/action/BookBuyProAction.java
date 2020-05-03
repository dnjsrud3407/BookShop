package member.book.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.CouponInfoService;
import member.account.svc.ModifyFormService;
import member.book.svc.BookBuyProService;
import member.book.svc.CartListService;
import member.book.svc.CartRemoveService;
import vo.ActionForward;
import vo.CartBean;
import vo.MemberBean;
import vo.OrderBean;
import vo.OrderDetailBean;

public class BookBuyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		
		String point = request.getParameter("point");
		int usedPoint = 0;
		if(point != null && !point.equals("")) {
			usedPoint = Integer.parseInt(request.getParameter("point"));
		}
		
		HttpSession session = request.getSession();
		
		OrderBean orderBean = null;
		
		
		String order_ID = (String)session.getAttribute("uID");
		String recName = request.getParameter("recName");
		String recPhone = request.getParameter("recPhone");
		// orderRec = 수령인
		String orderRec = recName + "/" + recPhone;
		int finalPrice = Integer.parseInt(request.getParameter("finalPrice"));
		int shipPrice = Integer.parseInt(request.getParameter("shipPrice"));
		
		// 배송지 정보 구하기
		String address2_post = request.getParameter("orderAddress_post");
		String address2_1 = request.getParameter("orderAddress_1");
		String address2_2 = request.getParameter("orderAddress_2");
		// 최근 배송지로 저장시키기
		String address2 = address2_post + address2_1 + address2_2;
		
		
		String orderStatus = "결제완료";
		Date orderTime = new Date(System.currentTimeMillis());
		Date lastModTime = orderTime;
		
		int coupon_num = Integer.parseInt(request.getParameter("couponList"));
		
		String paymentType = "Card";
		
		// 주문번호 생성
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // 시간 형식
		
		String[] timeArray = timeFormat.format(orderTime).toString().split("-");					  // 시간을 문자열로 변경 후 연,월,일,시,분,초 추출
		
		String orderNum = "";
		
		for(String time : timeArray) {	// 추출한 연,월,일,시,분,초 를 하나의 문자열로 합침
			orderNum += time;
		}
		orderNum += order_ID;   // 위에서 합친 문자열에 uID 를 합하여 주문번호 생성
		
		
		
		
		
		// 주문 상세 생성
		OrderDetailBean orderDetail = null;
		List<OrderDetailBean> orderList = new ArrayList<OrderDetailBean>();
		
		ArrayList<Integer> cartNum = new ArrayList<Integer>();
		ArrayList<Integer> bookID = new ArrayList<Integer>();
		ArrayList<String> bookTitle = new ArrayList<String>();
		ArrayList<Integer> bookPrice = new ArrayList<Integer>();
		ArrayList<Integer> bookEA = new ArrayList<Integer>();

		Enumeration<String> nameList = request.getParameterNames();
		
		while(nameList.hasMoreElements()) {
			String paramName = nameList.nextElement();
			if(paramName == null || paramName == "") {
				break;
			}
			
			if(paramName.contains("cartNum")) {
				cartNum.add(Integer.parseInt(request.getParameter(paramName)));
			}
			if(paramName.contains("bookID")) {
				bookID.add(Integer.parseInt(request.getParameter(paramName)));
			}
			if(paramName.contains("bookTitle")) {
				bookTitle.add(request.getParameter(paramName));
			}
			if(paramName.contains("bookPrice")) {
				bookPrice.add(Integer.parseInt(request.getParameter(paramName)));
			}
			if(paramName.contains("bookEA")) {
				bookEA.add(Integer.parseInt(request.getParameter(paramName)));
			}
			
		}
		
		// 주문 상세 리스트 생성
		for(int i = 0; i < bookID.size(); i++) {
			orderDetail = new OrderDetailBean(bookID.get(i), orderNum, bookTitle.get(i), bookPrice.get(i), bookEA.get(i));
			orderList.add(orderDetail);
		}
		
		// OrderBean 완성 
		orderBean = new OrderBean(orderNum, order_ID, finalPrice, shipPrice, orderRec, address2, recName, address2_post, address2_1, address2_2, recPhone , orderTime, orderStatus, lastModTime, coupon_num, paymentType, orderList);
		
		BookBuyProService bookBuyProService = new BookBuyProService();
		int successOrder = bookBuyProService.insertOrder(orderBean, usedPoint);
		
		
		// 쿠폰 사용 (업데이트 구문)
		
		// 포인트 사용 기록 및 user 접근
		
		// 장바구니 비우기
		CartRemoveService cartRemoveService = new CartRemoveService();
		cartRemoveService.cartRemove(cartNum, order_ID);
		System.out.println("==========");
		for (int i = 0; i < cartNum.size(); i++) {
			System.out.println(cartNum.get(i));
		}
		
		forward = new ActionForward();
		if(successOrder != 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");        
			out.println("alert('결제가 완료되었습니다')");
			out.println("</script>");			
			forward.setPath("./OrderList.mo");
			forward.setRedirect(true);
		} else {
			forward.setPath("./Main");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
