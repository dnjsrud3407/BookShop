package member.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.account.svc.CouponInfoService;
import member.account.svc.ModifyFormService;
import member.book.svc.CartListService;
import vo.ActionForward;
import vo.CartBean;
import vo.MemberBean;

public class BookBuyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		//구매하기 누르면 ->addcark 가면서 유저아이디 북 아이디 갯수를 들고옴
		//이 정보들로 구매가 가능하기때문에 리스트 추가가 없이 바로 이쪽으로 온다
		
		
		// 장바구니에서 상품 1~n 개 선택한 경우
		if(request.getParameterValues("cartNumList") != null) {
			
			String[] cartNumList = request.getParameterValues("cartNumList");
			// ==== 장바구니에서 선택한 장바구니 번호
			String cartNumStr = "";
			for(int i = 0; i < cartNumList.length; i++) {
				cartNumStr += cartNumList[i];
				if(i < cartNumList.length - 1) {
					cartNumStr += ",";
				}
			}
					
			HttpSession session = request.getSession();
			String uID = (String)session.getAttribute("uID");
	
			// ==== 카트 목록 정보 가져오기
			CartListService bookCartListService = new CartListService();
			ArrayList<CartBean> cartList = bookCartListService.getCartList(uID, cartNumStr);
			
			
			// ==== 유저 쿠폰 리스트  가져오기
			CouponInfoService couponInfoService = new CouponInfoService();
			List<MemberBean> couponList =  new ArrayList<MemberBean>();
			couponList = couponInfoService.getCouponList(uID);
			
			
			// ==== 유저 정보(포인트 포함) 가져오기
			ModifyFormService modifyFormService =new ModifyFormService();
			MemberBean memberBean = modifyFormService.getMemberInfo(uID);
			
			
			// ==== 상품 금액 계산 - totalPrice, shipPrice 값 지정
			int totalPrice = 0;
			int bookPrice = 0;

			// 장바구니 항목 각각의 금액 계산한 후 각 금액을 전체 합계 금액에 누적
			if (cartList != null) {
				for (int i = 0; i < cartList.size(); i++) {
					bookPrice = cartList.get(i).getBookPrice() * cartList.get(i).getBookEA(); // 상품 금액 * 수량
					totalPrice += bookPrice; // 전체 금액
				}
			}
			
			int shipPrice = 2500;
			if(totalPrice >= 50000) {
				shipPrice = 0;
			}
			
			
			// request 객체 유저 정보, 유저의 할인쿠폰 리스트, 배송비, 총 합계, 주문 리스트 넘기기 
			request.setAttribute("couponList", couponList);
			request.setAttribute("memberBean", memberBean);
			request.setAttribute("shipPrice", shipPrice);
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("cartList", cartList);
			
			forward = new ActionForward();
			forward.setPath("./book/book_buy.jsp");
		} else {	// cartNumList가 null이라면
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<script>");        
            out.println("alert('주문할 상품을 선택해주세요')");
            // 이전 페이지로 돌아가기
            out.println("history.back()");       
            out.println("</script>");
		}
		
		return forward;
	}

}
