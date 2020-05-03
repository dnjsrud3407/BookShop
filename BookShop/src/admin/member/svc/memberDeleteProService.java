package admin.member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class memberDeleteProService {

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

	public boolean removeMember(String uID) {
		System.out.println("MemberModifyProService - removeMember");

		boolean isRemoveSuccess = false;
	
		Connection con = getConnection();
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.setConnection(con);;
		
		int deleteCount = memberDAO.deleteMember(uID);
		
		if(deleteCount > 0) {
			commit(con);
			isRemoveSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRemoveSuccess;
	}

}
