package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import vo.MemberBean;

public class PointInfoService {

	public ArrayList<MemberBean> getPointInfo(String uID) {
		
		
		System.out.println("PointInfoService.getPointInfo(String uID");
		
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		
		
		ArrayList<MemberBean> list = dao.getPointInfo(uID);
		
		close(con);
		
		
		return list;
		
		
		
	}

}
