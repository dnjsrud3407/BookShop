package member.order.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderBean;


public class OrderListService {

	public ArrayList<OrderBean> getOrderList(String uId) {
		
		System.out.println("OrderListService.getOrderList");
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<OrderBean> list = dao.getOrderList(uId);
		
	
		
		close(con);
		
		return list;
	}


	public List<OrderBean> getMypagePointInfo(String uId) {
		System.out.println("OrderListService.getMypagePointInfo");
		 Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<OrderBean> list2=dao.getMypagePointInfo(uId);
		
		close(con);
		return list2;
	}


	public List<MemberBean> getCouponList(String uId) {
		System.out.println("OrderListService.getMypagePointInfo");
		 Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		
		ArrayList<MemberBean> couponList=dao.getCouponList(uId);
		close(con);
		return couponList;
	}


	public List<OrderBean> orderList() {
		System.out.println("OrderListService - orderList");
		
		 Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		List<OrderBean> orderList = orderDAO.orderList();
//		System.out.println("orderList : " + orderList);
		close(con);
		return orderList;	
	}
	
		public ArrayList<OrderBean> orderBookTotal(HttpServletRequest request, String orderNum) {
		
		System.out.println("OrderListService - orderBookTotal");
		
		 Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		System.out.println("orderNum : " + orderNum);
		ArrayList<OrderBean> orderTotal = orderDAO.orderTotal(orderNum);
		return orderTotal;
	}
		public List<OrderBean> selectOrder(String orderNum) {
			System.out.println("OrderListService - orderBookTotal");
			
			 Connection con = getConnection();
			OrderDAO orderDAO = OrderDAO.getInstance();
			orderDAO.setConnection(con);
			
			List<OrderBean> order = orderDAO.selectOrder(orderNum);
					
			close(con);
			
			return order;
		}


		//배송중만 
		public List<OrderBean> getDeliveryList(String uId) {
			 Connection con = getConnection();
				OrderDAO orderDAO = OrderDAO.getInstance();
				orderDAO.setConnection(con);
				List<OrderBean> getDeliveryList = orderDAO.getDeliveryList(uId);
				close(con);
			return getDeliveryList;
		}

		//취소반품교환
		public List<OrderBean> getorderCanCelReFundExCangeList(String uId) {
			 Connection con = getConnection();
			OrderDAO orderDAO = OrderDAO.getInstance();
			orderDAO.setConnection(con);
			List<OrderBean> getorderCanCelReFundExCangeList = orderDAO.getorderCanCelReFundExCangeList(uId);
			close(con);
		return getorderCanCelReFundExCangeList;
		}

}
