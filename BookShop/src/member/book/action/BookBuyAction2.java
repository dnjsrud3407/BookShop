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

public class BookBuyAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		//상품개별구매임
		System.out.println("북바이 액션 2 ");
		
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
			//여기에 북 아이디를 넘겨줄 무언가가 필요함
			
			
			//카트리스트에 가격과 갯수가 있다
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
			//쿠폰리스트
			request.setAttribute("couponList", couponList);
			//멤버정보
			request.setAttribute("memberBean", memberBean);
			//배송비
			request.setAttribute("shipPrice", shipPrice);
			//총가격
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("cartList", cartList); //주문창에 표시해주기위한 
			//상품명 이미지 가격 갯수 를 표시하기위함임 없애면안됨
			
			
			forward = new ActionForward();
			forward.setPath("./book/book_buy.jsp");
		}
		
		return forward;
	}
	}
	


		
