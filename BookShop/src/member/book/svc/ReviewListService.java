package member.book.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;
import vo.BookBean;
import vo.PageInfo;

public class ReviewListService {
	
	public BookBean getBooks(int num) {
		
		BookDAO bookDAO = BookDAO.getInstance();
		
		BookBean reviews = bookDAO.selectreviews(num);
		
		
		return reviews;
	}
	
	
	// 책 하나의 상품 후기 리스트 개수 가져오기
	public int reviewListCount(int bookID, String k1) {
		int listCount = 0;

		Connection con = getConnection();

		BoardDAO boardDAO = BoardDAO.getInstance();

		boardDAO.setConnection(con);

		int kID = boardDAO.get_kID(k1);

		if (kID == 103) {
			listCount = boardDAO.reviewUserListCount(bookID, kID);
		} 
		
		close(con);
		
		return listCount;
	}
	
	// 책 하나의 상품에  상품후기 글 10개씩 가져오기
	public ArrayList<BoardBean> getReviewBoard(PageInfo pageInfoReview, int bookID) {
		ArrayList<BoardBean> articleReviewList = null;
		
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		articleReviewList = boardDAO.selectUserReviewList(pageInfoReview, bookID);
		
		for(int i=0; i<articleReviewList.size(); i++) {
			System.out.println("Reivewboard"+articleReviewList.get(i).getBoardNum());
		}

		close(con);
		
		return articleReviewList;
	}


}
