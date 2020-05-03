package member.account.svc;

import java.sql.Connection;
import java.util.HashMap;

import dao.MemberDAO;

import static db.JdbcUtil.*; 

public class FindIdProService {

	public String findId(HashMap hash) {
		// TODO Auto-generated method stub
		String findId = null;
		
		Connection con = getConnection();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		mDAO.setConnection(con);
		
		findId = mDAO.findId(hash);
		
		close(con);
		
	
		
		return findId;
	}


}
