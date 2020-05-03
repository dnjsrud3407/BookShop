package access;

import static db.JdbcUtil.*;

import java.sql.Connection;


public class MemberCheckService {

	public int getMemberGrade(String uID) {
		int memberGrade = 0;
		Connection con = getConnection();
		
		MemberCheckDAO MCD = MemberCheckDAO.getInstance();
		MCD.setConnection(con);
		
		memberGrade = MCD.getMemberGrade(uID);
		
		close(con);
		
		return memberGrade;
	}
}
