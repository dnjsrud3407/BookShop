package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;

public class QDeleteProService {



	public int deleteOneOnOne(int boardNum, int kID) {
		
		
		System.out.println("QDeleteProService 의 deleteOneOnOne()");
		
		Connection con = getConnection();
		int result=0;
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		result=dao.oneOnOneDelete(boardNum,kID);
		
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return boardNum;
	}

}
