package member.book.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import action.Action;

import member.book.svc.CartListService;

import vo.ActionForward;

import vo.CartBean;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartListAction");

		ActionForward forward = null;

		// BookCartListService 의 getCartList() 메서드를 호출하여 카트 목록 정보 가져오기
		// => 파라미터 : request 객체 리턴타입 : ArrayList<CartBean>
		CartListService bookCartListService = new CartListService();
		
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		ArrayList<CartBean> cartList = bookCartListService.getCartList(uID);
		
		// 상품 금액 계산
		int totalPrice = 0;
		int bookPrice = 0;

		// 장바구니 항목 각각의 금액 계산한 후 각 금액을 전체 합계 금액에 누적
		if (cartList != null) {
			for (int i = 0; i < cartList.size(); i++) {
				bookPrice = cartList.get(i).getBookPrice() * cartList.get(i).getBookEA(); // 상품 금액 * 수량
				totalPrice += bookPrice; // 전체 금액
			}
		}

		// request 객체에 상품 총 금액과 카트 목록 저장
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("cartList", cartList);

		// book 폴더의 bookCartList.jsp 페이지로 이동 설정
		forward = new ActionForward();
		forward.setPath("/book/book_cart.jsp");

		return forward;
	}

}
