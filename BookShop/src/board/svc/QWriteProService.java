package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.MemberDAO;
import vo.BoardBean;
public class QWriteProService {

	public boolean registQuestions(String kakegoire, BoardBean boardBean) {
		
		System.out.println("QWriteProService.registQuestions(String kakegoire,String uID");
		Boolean isSuccess = false;
		
		
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		
		isSuccess=dao.insertOneOnOne(kakegoire,boardBean);
		
		
		
		
		
		
		if(isSuccess) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return isSuccess;
	}
	
	
	
	
	
}
