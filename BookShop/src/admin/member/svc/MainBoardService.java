package admin.member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.BookDAO;
import vo.BoardBean;

public class MainBoardService {

	// 상품 개수 들고오기
	public ArrayList<Integer> getBookEA() {
		ArrayList<Integer> bookEAList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookEAList = bookDAO.selectBookEA();
		
		close(con);
		
		return bookEAList;
	}
	
	// 메인에서 상품문의, 상품후기, 1:1문의 가져오기
	public ArrayList<BoardBean> getBoardList(int kID, int page, int limit) {
		ArrayList<BoardBean> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boardList = boardDAO.selectBoardList(kID, page, limit);
		
		close(con);
		
		return boardList;
	}

	// 메인에서 공지사항, 이벤트 목록 들고오기
	public ArrayList<BoardBean> getNaEBoardList(int kID, int page, int limit) {
		ArrayList<BoardBean> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boardList = boardDAO.selectNaEBoardList(kID, page, limit);
		
		close(con);
		
		return boardList;
	}

	

}
