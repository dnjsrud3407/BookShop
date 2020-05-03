package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class PwCheckBeforeModifyService {

	public int checkPass(String uID, String pw) {
		
		System.out.println("PwCheckBeforeModifyService checkPass(String uID, String pw)");
		

		int pwCheck =0;
		
		Connection con  = getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		
		 pwCheck=dao.checkPass(uID,pw);
		 
		
		if(pwCheck > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwCheck;
	}
}
