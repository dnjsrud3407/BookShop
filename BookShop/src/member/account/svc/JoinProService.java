package member.account.svc;


import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

import static db.JdbcUtil.*;


public class JoinProService {

	
	public boolean joinMember(MemberBean member) {
		boolean isJoinSuccess = false;
		
		Connection con = getConnection();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		mDAO.setConnection(con);
		
		int insertCount = mDAO.insertMember(member);
		
		if(insertCount > 0) {
			commit(con);
			isJoinSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isJoinSuccess;
	}

}	
	
	
	

