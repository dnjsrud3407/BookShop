package member.account.svc;

import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import vo.OrderBean;

public class SearchProService {

	public List<OrderBean> getSearchOrderList(String startDate, String endDate, String uId) {
	
		System.out.println("SearchProService.getSearchOrderList(String startDate, String endDate)");
		
		
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		
		ArrayList<OrderBean> orderList = dao.getOrderList(startDate,endDate,uId);
		close(con);
		
		
		return orderList;
		
		
		
		
	}

	public List<OrderBean> getSearchOrderCanCelList(String startDate, String endDate, String uId) {
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);

		ArrayList<OrderBean> orderList = dao.getOrderCenCelList(startDate,endDate,uId);
		
		close(con);
		
		return orderList;
	}
	
	
	

}
