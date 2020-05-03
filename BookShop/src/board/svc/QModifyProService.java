package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class QModifyProService {

	public int oneOnOneModify(BoardBean boardBean) {
		System.out.println("Q_ModifyProService 의 oneOnOneModify()");
		Connection con = getConnection();
		int result=0;
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		result=dao.oneOnOneModify(boardBean);
		
	
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
