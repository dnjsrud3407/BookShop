package admin.member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberModifyProService {

	/* 멤버수정 - 포인트와 그레이드 */
//	public boolean isAdminWriter(String uID, String pw) {
//		System.out.println("MemberModifyProService - isAdminWriter");
//
//		boolean isAdminWriter = false;
//		
//		Connection con = getConnection();
//		MemberDAO memberDAO = new MemberDAO();
//		memberDAO.setConnection(con);;
//		
//		isAdminWriter = memberDAO.isAdminWriter(uID, pw);
//		
//		close(con);
//		return isAdminWriter;
//		
//	}
	

	public boolean modifyMember(MemberBean member) {
		System.out.println("BoardModifyService - modifyMember");
		int updateCount = 0;
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.setConnection(con);
		
		updateCount = memberDAO.updateMember(member);
				
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isModifySuccess;
	}


}
