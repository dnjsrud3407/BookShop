package member.book.action;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.Action;
import member.book.svc.CartAddService;

import vo.ActionForward;
import vo.BookBean;
import vo.CartBean;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartAddAction");
		
		ActionForward forward = null;
		// 세션의 속성을 가져와서 uID에 저장
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		System.out.println(uID);
		if(uID == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // HTML 태그 출력을 위한 Writer 객체 가져오기
			// out 객체의 println() 메서드를 호출하여 HTML 태그 작성
			out.println("<script>"); // 자바스크립트 실행을 위한 <script> 시작 태그
			out.println("alert('로그인이 필요한 서비스입니다')"); // alert dialog 출력
			out.println("history.go(-1)"); // 또는 out.println("history.go(-1)");  // 이전 페이지로 돌아가기
			out.println("</script>");
			return forward;// 자바스크립트 종료 위한 <script> 끝 태그
		}
		int bookID =Integer.parseInt(request.getParameter("bookID"));
		int qty = 1;
		System.out.println(request.getParameter("qty"));
		if(request.getParameter("qty") != null) {
			qty = Integer.parseInt(request.getParameter("qty"));
		}

		System.out.println(qty);
	
		System.out.println(bookID);
		
		CartAddService bookCartProService = new CartAddService();
		
		// bookID에  해당하는 상품 정보 가져오기
		BookBean bookBean= bookCartProService.getCartBook(bookID);
		
	
	
		System.out.println("사용자이름 :" + uID);
		
//		// 카트에 상품 추가
		ArrayList<CartBean> cartList = bookCartProService.addCart(bookBean, uID, qty);
		
		session.setAttribute("cartList", cartList);
		
		forward = new ActionForward(); 
		forward.setPath("CartList.book"); 
		forward.setRedirect(true); 
			
	
		return forward;
	}

}
