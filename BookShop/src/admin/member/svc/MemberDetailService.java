package admin.member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;


import dao.MemberDAO;
import vo.MemberBean;

public class MemberDetailService {

	public MemberBean getMember(String members) throws Exception {
		System.out.println("MemberListService - getMember");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean member = null;
		
		member = memberDAO.selectMember(members);
				
		close(con);
		
		return member;
	}

}
