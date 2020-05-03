package member.book.svc;

import vo.BookBean;
import vo.CartBean;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.CartDAO;

public class CartListService {

	// 카트에 담긴 책 정보를 가져오는 메서드
	public BookBean getCartBook(int bookID) {

		Connection con = getConnection();

		BookDAO bookDAO = BookDAO.getInstance();

		bookDAO.setConnection(con);

		BookBean bookBean = bookDAO.selectUserBook(bookID);
		

		close(con);

		return bookBean;
	}


	public ArrayList<CartBean> getCartList(String uID) {
		// TODO Auto-generated method stub
		// HttpSession 객체 가져와서 세션 내의 ArrayList<CartBean> 객체 가져오기
		Connection con = getConnection();
		
		CartDAO cartDAO = CartDAO.getInstance();
		
		cartDAO.setConnection(con);
		
		ArrayList<CartBean> cartList = cartDAO.getCartList(uID);
			
		close(con);	
		
		return cartList;
	}


	public ArrayList<CartBean> getCartList(String uID, String cartNumStr) {
		Connection con = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		
		cartDAO.setConnection(con);
		
		ArrayList<CartBean> cartList = cartDAO.getCartList(uID, cartNumStr);
			
		close(con);
		return cartList;
	}

}
