package member.account.svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;
import static db.JdbcUtil.*;

public class LoginProService {

	public int isLoginMember(MemberBean member) {
		int loginResult = -2;	
		
		Connection con = getConnection();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		mDAO.setConnection(con);
		
		loginResult = mDAO.isLogin(member);
				
		close(con);
		
		return loginResult;
	}

}
