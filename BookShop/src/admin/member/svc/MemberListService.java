package admin.member.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import static db.JdbcUtil.*; 
import vo.MemberBean;

public class MemberListService {
 
	public List<MemberBean> getMemberList() {
		System.out.println("MemberListService - getMemberList");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		List<MemberBean> memberList = memberDAO.getMemberList();
//		memberDAO.selectMemberList();
		
		close(con);
		
		return memberList;
	}

//	public ArrayList<MemberBean> getMemberList(int page, int limit) {
//		ArrayList<MemberBean> memberList = null;
//		Connection con = getConnection();
//		MemberDAO memberDAO = MemberDAO.getInstance();
//		memberDAO.setConnection(con);
//		
//		memberList = memberDAO.selectMemberList(page, limit);
//		
//		close(con);
//		return memberList;
//	}


}
