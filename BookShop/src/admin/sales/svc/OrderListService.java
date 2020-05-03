package admin.sales.svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import dao.OrderDAO;
import static db.JdbcUtil.*;
import vo.MemberBean;
import vo.OrderBean;

public class OrderListService {

	public OrderBean getOrder(int num) {
		System.out.println("OrderListService - getOrder");
		
//		OrderDAO orderDAO = OrderDAO.getInstance();
		
		OrderBean order = null;
//		order = orderDAO.selectOrder(num);
		
		
		return order;	
		}
	
}
