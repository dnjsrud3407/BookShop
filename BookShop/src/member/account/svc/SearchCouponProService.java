package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import vo.MemberBean;

public class SearchCouponProService {

	public List<MemberBean> getCouponInfo(String uID, String startDate2, String endDate2) {
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		ArrayList<MemberBean> list = dao.getCouponInfo(uID,startDate2,endDate2);
		close(con);
		
		
		return list;
	}

}
