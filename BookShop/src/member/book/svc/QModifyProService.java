package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import vo.BookBean;

public class QModifyProService {

	public BoardBean getQuestion(int boardNum) throws Exception {
		System.out.println("QModifyProService - isWriter");
		BoardBean boardBean = null;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		boardBean = boardDAO.getQuestion(boardNum);
//		isWriter = bookDAO.isWriter(num, pass);
//		
//		close(con);
		return boardBean;
	}

	public boolean modifyQuestion(BoardBean boardBean) {
		System.out.println("QModifyProService - isQWriter");
		int updateCount = 0;
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
//		
		updateCount = boardDAO.updateQuestion(boardBean);
		
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
