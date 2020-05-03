package member.account.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class JoinCheckService {

	public boolean idCheck(String uID) {
		boolean idCheck = true;
		Connection con = getConnection();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		mDAO.setConnection(con);
		
		idCheck = mDAO.idCheck(uID);
		
		close(con);
		
		return idCheck;
	}

}
