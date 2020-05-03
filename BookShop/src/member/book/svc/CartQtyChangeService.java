package member.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import vo.BookBean;
import vo.CartBean;
import static db.JdbcUtil.*;

public class CartQtyChangeService {

	public void qtyChange(int cartNum, int qty, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int qtyUpdateResult = 0;
		Connection con = getConnection();

		CartDAO cartDAO = CartDAO.getInstance();

		cartDAO.setConnection(con);

		HttpSession session = request.getSession();
		String uID = (String) session.getAttribute("uID");

		
//		if (cartList != null) { // db에서 장바구니에 대한 데이터가 있으면
//			for (CartBean cartBean : cartList) { // cartBean 객체에 담음
//				if (cartBean.getCartNum() == cartNum) { // cartBean 객체의 cartNum과 cartNum을 비교
					BookBean bookBean = new BookBean();
					bookBean.setBookID(cartNum);
					qtyUpdateResult = cartDAO.changeQtyCart(bookBean, uID, qty);

					if (qtyUpdateResult > 0) {
						commit(con);
						//cartBean.setBookEA(qty);
					} else {
						rollback(con);
					}
//				}
//			}

//		}
			close(con);

			
	}

}
