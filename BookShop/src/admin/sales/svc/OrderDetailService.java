package admin.sales.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import dao.OrderDAO;
import vo.OrderBean;

public class OrderDetailService {

	public List<OrderBean> selectOrder(String orderNum) {
		System.out.println("OrderListService - orderBookTotal");
		    
		 Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		List<OrderBean> order = orderDAO.selectOrder(orderNum);
				
		close(con);
		
		return order;
	}
	
	public OrderBean orderDetaile(String orderNum) {
		System.out.println("OrderListService - orderBookTotal");
		
		 Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		OrderBean orderDetaile = orderDAO.orderDetaile(orderNum);
				
		close(con);
		
		return orderDetaile;
	}
	
	public OrderBean getOrder(int num) {
System.out.println("OrderDetailService - getOrder");
		
//Connection con = getConnection();
//OrderDAO orderDAO = OrderDAO.getInstance(); 
//orderDAO.setConnection(con);
//
////		
//		OrderBean order = null;
////
//		order = orderDAO.selectOrder(num);
//
//		int updateCount = boardDAO.updateReadcount(board_num);
//		System.out.println("BoardDetailService - BoardDAO: updateReadcount");

		//		if(updateCount > 0) {
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}
		
//		close(con);
		
		return null;
	}
	//조회수 증가용
	public void plusReadcount(int num) throws Exception {
		//게시글중 한개 클릭시
		System.out.println("OrderDetailService - getArticle");
		
//		Connection con = getConnection();
//		OrderDAO orderDAO = OrderDAO.getInstance(); 
//		orderDAO.setConnection(con);
		
//		int updateCount = orderDAO.updateReadcount(num);
//
//		if(updateCount > 0) {
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}
//		
//		close(con);
	}

}
