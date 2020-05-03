package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;
import vo.BookBean;

public class ReviewDetailService {

	public BookBean getBook(int num) {

System.out.println("ReviewDetailService - getBook");
		
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance(); 
//		bookDAO.setConnection(con);
//		
//		BookBean book = null;
//
//		book = bookDAO.selectBook(num);
//
//		
//		
		
//		int updateCount = boardDAO.updateReadcount(board_num);
//		System.out.println("BoardDetailService - BoardDAO: updateReadcount");

		//		if(updateCount > 0) {
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}
		
//		close(con);
		
		return null;
	}

	// 상품후기 상세보기
	public BoardBean getReviewDtBoard(int boardNum, int kID, String boardWriter) {
		BoardBean reviewDetail = null;
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		reviewDetail = boardDAO.getReviews(boardNum, kID, boardWriter);
		
		close(con);
		
		return reviewDetail;
	}
	
	// 상품후기 조회수 증가
	public void plusReadcount(int boardNum, int kID, String boardWriter) throws Exception {
		//게시글중 한개 클릭시
		System.out.println("ReviewDetailService - getArticle");
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance(); 
		boardDAO.setConnection(con);
//		
		int updateCount = boardDAO.updateReadcount(boardNum,kID,boardWriter);
//
		if(updateCount > 0) {
			commit(con);
			
		}else {
			rollback(con);
		}
//		
		close(con);

	}

}
