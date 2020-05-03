package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

public class QnReListService {

	// 상품 문의 게시글 개수 가져오기
	public int getListCount(int kID) {
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		listCount = boardDAO.selectListCount(kID);
		
		close(con);
		
		return listCount;
	}

	// 상품 문의 게시글 들고오기
	public ArrayList<BoardBean> getList(int kID, int page, int limit) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> qList = boardDAO.selectList(kID, page, limit);
		
		close(con);
		
		return qList;
	}

}
