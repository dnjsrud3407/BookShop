package member.order.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.OrderDAO;

public class OrderRefundProService {

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
