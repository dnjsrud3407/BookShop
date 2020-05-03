package member.order.svc;
import static db.JdbcUtil.*;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OrderDAO;

public class OrderExchangeProService {

	public int updateOrderStatus(String orderNum, String changeOrderStatus) {
		int right=0;
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		right=dao.updateOrderStatus(orderNum,changeOrderStatus);
		
		if (right>=1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		close(con);
		return right;
	}

}
