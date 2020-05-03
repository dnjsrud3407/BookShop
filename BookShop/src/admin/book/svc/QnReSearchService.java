package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

public class QnReSearchService {

	// 상품 문의 글 검색한 게시글 개수 구하기
	public int getSearchListCount(int kID, String boardRegTime_Before, String boardRegTime_After, String searchSql) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = boardDAO.getSearchListCount(kID, boardRegTime_Before, boardRegTime_After, searchSql);
				
		close(con);
		
		return listCount;
	}

	// 상품 문의 글 검색한 게시글 리스트
	public ArrayList<BoardBean> getSearchBoardList(int kID, String boardRegTime_Before, String boardRegTime_After,
			String searchSql, int page, int limit) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> qSearchList = boardDAO.getSearchBoardList(kID, boardRegTime_Before, boardRegTime_After, searchSql, page, limit);
		
		close(con);
		
		return qSearchList;
	}

}
