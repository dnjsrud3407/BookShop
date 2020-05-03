package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminBoardDAO;
import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;
import vo.BookBean;

public class ReviewWriteProService {

	public boolean registReview(BoardBean boardBean) {
		System.out.println("ReviewWriteProService - registReview");
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();

		boardDAO.setConnection(con);
		
		int insertCount = boardDAO.registReview(boardBean);
//		int insertCount = bookDAO.insertBooks(bookBean);
		boolean isWriteSuccess = false;
//		
		if(insertCount > 0) {
			commit(con);
//			bookDAO.updateBoard_re_ref(bookBean);
//			commit(con);
			
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return false;

	}

	// 해당 대분류 내의 가장 큰 글 번호를 가져오는 메서드
	public int getMaxNum(String k1) {
		System.out.println("ReviewWriteProService의 getMaxNum() 메서드");
		Connection con = null;
		// DB 연결
		con = getConnection();
		
		// BoardDAO 객체 생성(싱글톤 패턴)
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		// DB 작업
		int maxNum = boardDAO.getMaxNum(k1);	// k1 카테고리의 글 중 최신글의 글 번호(가장 큼)를 가져옮
		// DB 연결 종료
		close(con);
		return maxNum; // 가져온 최신글의 글 번호 반환
	}

}
