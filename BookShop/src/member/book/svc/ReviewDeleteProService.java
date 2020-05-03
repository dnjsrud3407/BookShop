package member.book.svc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;

public class ReviewDeleteProService {

	public boolean isReviewWriter(int num, String parameter) {
		System.out.println("ReviewDeleteProService - isReviewWriter");

		return false;
	}

	public boolean removeReview(int boardNum, String boardWriter, String k1) {
		System.out.println("ReviewDeleteProService - removeReview");
		boolean isDeleteSuccess = false;
		int deleteCount = 0;
		
		Connection con = getConnection();
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		deleteCount = boardDAO.removeReview(boardNum, boardWriter, k1);
		
		if(deleteCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
			close(con);
		
		return isDeleteSuccess;
	}

}
