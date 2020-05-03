package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class ModifyProService {

	public int memberInfoModify(MemberBean memberBean) {
		
System.out.println("ModifyProService.memberInfoModify(MemberBean memberBea)");
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		int modifySuccess=0;
		
		modifySuccess=dao.memberInfoModify(memberBean);
		
		if(modifySuccess > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return modifySuccess;
	}

}
