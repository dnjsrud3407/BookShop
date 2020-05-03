package member.book.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;
import vo.BookBean;

public class QWriteProService {

	public boolean registQuestions(BoardBean bb) {

		System.out.println("QWriteProService - registQuestions");
		boolean isWriteSuccess = false;
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance();
//		
		Connection con = getConnection();

		BoardDAO boardDAO = BoardDAO.getInstance();

		boardDAO.setConnection(con);

		// 책 상품 별로  제일 큰 글 번호 가져옴
		int MaxNum = boardDAO.getMaxNum(bb.getK1());
		
		// 글이 하나도 없는지 판별
		if (MaxNum > 0) { // 하나 이상 있는 경우
			bb.setBoardNum(MaxNum + 1);
			bb.setBoardReRef(bb.getBoardNum());
			bb.setBoardReLev(0);
			bb.setBoardReSeq(0);

		} else { // 글이 하나도 없는 경우
			bb.setBoardNum(1);
			bb.setBoardReRef(bb.getBoardNum());
			bb.setBoardReLev(0);
			bb.setBoardReSeq(0);

		}

		int insertCount = boardDAO.insertArticle(bb);
		System.out.println(insertCount);

		if (insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isWriteSuccess;
	}

}
