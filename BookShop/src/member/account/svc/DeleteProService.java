package member.account.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
public class DeleteProService {

	public int checkPass(String uID, String pw) {
		System.out.println("DeleteProService checkPass(String uID, String pw)");
		
		
		int pwCheck =0;
		
		Connection con  = getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		
		 pwCheck=dao.checkPass(uID,pw);
		 
		 System.out.println("딜리트프로서비스 "+pwCheck);
		
		if(pwCheck > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwCheck;
	}
	

}
