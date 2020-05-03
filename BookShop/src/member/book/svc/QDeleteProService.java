package member.book.svc;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

import static db.JdbcUtil.*;

public class QDeleteProService {

	public boolean isQuestionWriter(int num, String parameter) {
		System.out.println("QDeleteProService - isQuestionWriter");

		return false;
	}

	// 상품문의 삭제
	public boolean removeQuestion(BoardBean boardBean) {
		System.out.println("QDeleteProService - removeQuestion");
		int delectCount = 0;
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		delectCount = boardDAO.removeQuestion(boardBean);
		
		if(delectCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}



}
