package member.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CartDAO;

import static db.JdbcUtil.*;

public class CartRemoveService {

	public void cartRemove(int cartNum, String uID) {
		// TODO Auto-generated method stub
		int cartRemoveResult = 0;
		
		Connection con = getConnection();
		
		CartDAO cartDAO = CartDAO.getInstance();
		
		cartDAO.setConnection(con);
		
		cartRemoveResult = cartDAO.cartRemove(cartNum, uID);
				
		if (cartRemoveResult > 0) {
			commit(con);
					
		} else {
			rollback(con);			
		}

			close(con);

			
	}

	// 주문 완료시 장바구니 비우기
	public void cartRemove(ArrayList<Integer> cartNum, String order_ID) {
		int cartRemoveResult = 0;
		Connection con = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(con);
		
		for(int i = 0; i < cartNum.size(); i++) {
			cartRemoveResult = cartDAO.cartRemove(cartNum.get(i), order_ID);
		}
				
		if (cartRemoveResult > 0) {
			commit(con);
			System.out.println("카트 삭제 완료");
					
		} else {
			rollback(con);			
		}

		close(con);
		
	}



}
