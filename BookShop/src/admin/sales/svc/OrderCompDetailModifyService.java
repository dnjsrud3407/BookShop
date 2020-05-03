package admin.sales.svc;

import static db.JdbcUtil.*; 

import java.sql.Connection;

import dao.OrderDAO;
import vo.OrderBean;

public class OrderCompDetailModifyService {
	public boolean modifyOrder(OrderBean order) {
		System.out.println("BoardModifyService - modifyMember");
		int updateCount = 0;
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		 
		updateCount = dao.updateOrder(order);
		System.out.println("updateCount OrderCompDetailModifyService : " + updateCount);
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}


}
