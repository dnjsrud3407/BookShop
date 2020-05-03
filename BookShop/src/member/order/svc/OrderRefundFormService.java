package member.order.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OrderDAO;
import vo.OrderBean;

public class OrderRefundFormService {

	public OrderBean getDeliveryDetail(int orderDetailNum) {
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		OrderBean orderBean =dao.orderDeliveryDetail(orderDetailNum);
		close(con);
		
		return orderBean;
	}

}
