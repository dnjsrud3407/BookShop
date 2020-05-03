package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import vo.MemberBean;
import vo.OrderBean;

public class CouponInfoService {

	public ArrayList<MemberBean> getCouponInfo(String uID) {
		
		System.out.println("CouponInfoService.getCouponInfo(String uID");
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<MemberBean> list = dao.getCouponInfo(uID);
	
		close(con);
		
		
		return list;
		
	}

	public List<MemberBean> getCouponList(String uID) {
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<MemberBean> list = dao.getCouponList(uID);
		
		close(con);
		
		return list;
	}
	
	
	
	

}
