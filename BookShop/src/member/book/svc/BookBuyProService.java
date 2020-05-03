package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CartDAO;
import dao.MemberDAO;
import dao.OrderDAO;
import vo.MemberBean;
import vo.OrderBean;

public class BookBuyProService {

	public int insertOrder(OrderBean orderBean, int usedPoint) {
		Connection con = null;
		con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(con);
		
		//
		int updateCount = orderDAO.insertOrder(orderBean);
		
		if(updateCount > 0) {
			// 책 재고 업데이트
			int updateBookCount = orderDAO.updateBookEA(orderBean.getOrderList());
			// 쿠폰 내역 업데이트
			int cHistory_num = orderBean.getCoupon_num(); // cHistory_num 이다.
			String id = orderBean.getOrder_ID();  // 주문자 id(즉 쿠폰쓴 아이디)
			int totalPrice = orderBean.getTotalPrice();
			
			
			if(cHistory_num > 0) {
				int couponUpdateCount = orderDAO.couponUpdate(cHistory_num, id);
			}
			
			// 포인트 내역 인서트
			// user 포인트 양 업데이트 / 사용수치 만큼 차감 / 총 금액의 5% 적립 / 최종적으로 user에 point 수치 등록
			int setPointHistoryCount = orderDAO.setPointHistory(id, usedPoint, totalPrice, orderBean.getOrderNum());
			
			
			
			if(updateBookCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} else {
			rollback(con);
		}
		
		//
		close(con);
		
		return updateCount;
	}

//	public void modifyAddress2(MemberBean member) {
//		Connection con = getConnection();
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		memberDAO.setConnection(con);
//		
//		int updateCount = memberDAO.updateAddress2(member);
//		
//		if(updateCount > 0) {
//			commit(con);
//		} else {
//			rollback(con);
//		}
//		
//		close(con);
//	}

}
