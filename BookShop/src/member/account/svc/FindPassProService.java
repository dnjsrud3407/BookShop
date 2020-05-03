package member.account.svc;

import java.sql.Connection;
import java.util.HashMap;

import dao.MemberDAO;

import static db.JdbcUtil.*;


public class FindPassProService {

	public String findPass(HashMap hash) {
		String findPass = null;
		
		Connection con = getConnection();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		mDAO.setConnection(con);
		
		findPass = mDAO.findPass(hash);
		
		close(con);
		
		return findPass;
	}

}
