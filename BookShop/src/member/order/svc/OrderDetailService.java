package member.order.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderBean;

public class OrderDetailService {

	public ArrayList<OrderBean> orderDetail(String orderNum) {
		
		//디비연결
		System.out.println("OrderDetailService.orderDetail(int orderNum)");
		
		Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);

		ArrayList<OrderBean> list = dao.orderDetail(orderNum);
		
		
		close(con);
		return list;
				
		
	}

	public List<OrderBean> getMypagePointInfo(String uId) {
		System.out.println("OrderDetailService.getMypagePointInfo");
		 Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<OrderBean> list2=dao.getMypagePointInfo(uId);
		
		
		
		
		close(con);
		return list2;
	}

	
	
	public MemberBean getCouponInfo(String uId, String orderNum) {
		System.out.println("OrderDetailService.getMypagePointInfo");
		 Connection con = getConnection();
		OrderDAO dao = OrderDAO.getInstance();
		dao.setConnection(con);
		
		MemberBean memberBean = dao.getCouponInfo(uId,orderNum);
		close(con);
		return memberBean;
	}
	
	
	
}
