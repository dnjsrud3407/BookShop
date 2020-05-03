package board.svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.BoardDAO;
import vo.BoardBean;

import static db.JdbcUtil.*;
public class QListService {

	public ArrayList<BoardBean> getList(String uID, int startRow, int limit) {
		System.out.println("Q_ListService.getList( String uID)");
		
		
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		
			ArrayList<BoardBean> QList = dao.getOneonOneQList(uID,startRow,limit);
		
			close(con);
		
		return QList;
	}


	public int getOneonOneQListCount(String uID) {
		int count=0;
		

		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		count=dao.getOneonOneQListCount(uID);
		

		close(con);
		return count;
	}
}
