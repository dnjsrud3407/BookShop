package member.book.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.Action;
import member.account.svc.CouponInfoService;
import member.account.svc.ModifyFormService;
import member.book.svc.CartAddService;

import vo.ActionForward;
import vo.BookBean;
import vo.CartBean;
import vo.MemberBean;

public class CartAddAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartAddAction222");
		
		ActionForward forward = null;
		
		int bookID =Integer.parseInt(request.getParameter("bookID"));
		int qty = 1;
		System.out.println(request.getParameter("qty"));
		if(request.getParameter("qty") != null) {
			qty = Integer.parseInt(request.getParameter("qty"));
		}
		

		// 세션의 속성을 가져와서 uID에 저장
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		System.out.println("사용자이름 :" + uID);
		

	
	
		System.out.println("선택한 북 아이디"+bookID);
		
		//구매하기 누르면 북 아이디 가져옴
		//북아이디에 해당하는 북의 정보를 빈에 담아서 가져옴
		//유저아이디도 가져옴
		//수량도 가져옴
		//이걸 카트에 추가하는게 아님
		//북빈,사용자이름,수량 이 있어야 함
		
		//=-=========================================================================
		//1북정보,유저아이디,수량,가격 이 있기때문에..바로 주문으로 넘어가도됨!!!!
		//여기서 총 주문금액 멤버 쿠폰리스트,포인트, 하면됨
		//===========================================================================
		
		
		//리스트를 넘기는게 아니라 
		//구매 하나만 하니까 빈에 저장해와서 빈을 넘긴다 북 바이에
		//북 바이에서 갯수와 가격을 가져와서 총가격을 가져온다
		
		//5총 주문금액
		int totalPrice=0;
		
		
		
		CartAddService bookCartProService = new CartAddService();
		//1 bookID에  해당하는 상품 정보 가져오기 가격.이미지.이름.정보
		BookBean bookBean= bookCartProService.getCartBook(bookID);
		//2 	// ==== 유저 정보(포인트 포함) 가져오기
		ModifyFormService modifyFormService =new ModifyFormService();
		MemberBean memberBean = modifyFormService.getMemberInfo(uID);
		//3  유저 사용가능 쿠폰가져오기
		CouponInfoService couponInfoService = new CouponInfoService();
		List<MemberBean> couponList =  new ArrayList<MemberBean>();
		couponList = couponInfoService.getCouponList(uID);
		//4 	수량가져오기
		System.out.println("가지고온 수량"+qty);
		
		//총 금액 계산하기
		int bookprice=bookBean.getBookPrice()*qty;

		totalPrice=bookprice*qty;
		
		
		int shipPrice = 2500;
		if(totalPrice >= 50000) {
			shipPrice = 0;
		}
		
		
		//쿠폰리스트
		request.setAttribute("couponList", couponList);
		//멤버정보
		request.setAttribute("memberBean", memberBean);
		//배송비
		request.setAttribute("shipPrice", shipPrice);
		//총가격
		request.setAttribute("totalPrice", totalPrice);
		//구매창에 표시해줄 정보 cartList 대신임
		request.setAttribute("bookBean", bookBean);
		request.setAttribute("qty", qty); //buy2.jsp 에 표시해줄 갯수임!
		
		
		
//		// 카트에 상품 추가
//		ArrayList<CartBean> cartList = bookCartProService.addCart(bookBean, uID, qty);
//		
//		session.setAttribute("cartList", cartList);
//		
		
		
		forward = new ActionForward();
		forward.setPath("./book/book_buy2.jsp");
//			
	
		return forward;
	}

}
