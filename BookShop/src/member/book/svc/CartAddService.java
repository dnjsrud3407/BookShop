package member.book.svc;

import vo.BookBean;
import vo.CartBean;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import dao.CartDAO;

public class CartAddService {

	// 카트에 담긴 책 정보를 가져오는 메서드
	public BookBean getCartBook(int bookID) {

		Connection con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();

		bookDAO.setConnection(con);

		BookBean bookBean = bookDAO.selectUserBook(bookID);
		

		close(con);

		return bookBean;
	}

	// 카트 추가 메서드
	public ArrayList<CartBean> addCart(BookBean bookBean, String uID, int qty) {
		// TODO Auto-generated method stub
		ArrayList<CartBean> cartList = null;
		int cartAddResult = 0; // 카트 insert 성공 여부 변수
		int qtyUpdateResult = 0; // 카트 update 성공 여부 변수
		
		Connection con = getConnection();

		CartDAO cartDAO = CartDAO.getInstance();

		cartDAO.setConnection(con);

		// 사용자 카트 정보 조회
		cartList = cartDAO.getCartList(uID);

		System.out.println("장바구니 :" + "," + cartList);

		// 카트 정보가 비어있을 경우 cartList를 새로 만듬
		if (cartList.isEmpty()) {
			cartList = new ArrayList<CartBean>();
		}

		boolean isNewCart = true;// 장바구니 중복 추가를 방지하기 위한 변수(새 상품인지 구분)

		for (int i = 0; i < cartList.size(); i++) {
			if (bookBean.getBookID() == cartList.get(i).getCartNum()) {
				// 기존 장바구니 상품과 중복되므로 isNewCart 를 false 로 변경 후 qty만큼 수량 증가
				System.out.println("상품 중복됨" + bookBean.getBookID() + "," + cartList.get(i).getCartNum());
				isNewCart = false; // 새 상품이 아니므로 false 로 변경
				qtyUpdateResult = cartDAO.updateQtyCart(bookBean, uID, qty);
				if (qtyUpdateResult > 0) {
					commit(con);
					cartList.get(i).setBookEA((cartList.get(i).getBookEA() + qty)); // 기존 카드 상품 수량 qty만큼 증가
					System.out.println(" 수량 변경 된 갯수 " + cartList.get(i).getBookEA());
				} else {
					rollback(con);
				}
				break;
			}
		}

		System.out.println(isNewCart);

		if (isNewCart) { // 중복되는 카트 상품이 없을 경우
			// db에 insert 후 cartList에 카트 정보 추가
			cartAddResult = cartDAO.addCart(bookBean, uID, qty);
			if (cartAddResult > 0) {
				commit(con);
				CartBean cartBean = new CartBean();
				cartBean.setCartNum(bookBean.getBookID());
				cartBean.setBookImage(bookBean.getBookImage());
				cartBean.setBookTitle(bookBean.getBookTitle());
				cartBean.setBookPrice(bookBean.getBookPrice());
				cartBean.setBookEA(qty);
				
				cartList.add(cartBean);
			} else {
				rollback(con);
			}

		}
		
		
//		System.out.println("장바구니 :" + "," + cartList);
//		for (int i = 0; i<cartList.size(); i++) {
//			System.out.println("장바구니 " + cartList.get(i).getBookTitle());
//		}
		
		close(con);
		
		return cartList;
	}

}
