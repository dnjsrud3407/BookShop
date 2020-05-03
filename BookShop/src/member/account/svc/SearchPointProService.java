package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import vo.MemberBean;

public class SearchPointProService {

	public List<MemberBean> getPointInfo(String uID, String startDate, String endDate) {
		
		System.out.println("PointInfoService.getPointInfo(String uID");
		
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		
		
		ArrayList<MemberBean> list = dao.getPointInfo(uID,startDate,endDate);
		
		close(con);
		
		
		return list;
		
		
	}

}
