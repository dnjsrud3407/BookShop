package member.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;
import vo.BookBean;
import vo.PageInfo;

import static db.JdbcUtil.*;

public class QListService {
	
//	public BookBean getQuestion(int num) {
//		
//		BookDAO bookDAO = BookDAO.getInstance();
//		
//		BookBean questions = bookDAO.selectQuestion(num);
//		
//		
//		return questions;
//	}

	// 책  하나의 상품 문의 리스트 개수 가져오기
	public int qnaListCount(int bookID, String k1) {
		int listCount = 0;

		Connection con = getConnection();

		BoardDAO boardDAO = BoardDAO.getInstance();

		boardDAO.setConnection(con);

		int kID = boardDAO.get_kID(k1);

		if (kID == 102) {
			listCount = boardDAO.qnaUserListCount(bookID, kID);
		} 
		
		close(con);
		
		return listCount;
	}
	
	// 책 하나의 상품에  상품문의 글 10개씩 가져오기
	public ArrayList<BoardBean> getQnaBoard(PageInfo pageInfoQna, int bookID) {
		ArrayList<BoardBean> articleQnaList = null;
		
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		articleQnaList = boardDAO.selectUserQnaList(pageInfoQna, bookID);
		
		for(int i=0; i<articleQnaList.size(); i++) {
			System.out.println("qnaboard"+articleQnaList.get(i).getBoardNum());
		}

		close(con);
		
		return articleQnaList;
	}

	public BoardBean qnaAnswerBoard(int boardNum, int kID, int bookID) {
		BoardBean answerBoard = null;
		
		Connection con = getConnection();
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		answerBoard = boardDAO.qnaAnswerBoard(boardNum, kID, bookID);
		
		close(con);
		
		return answerBoard;
		
	}

	

}
