package admin.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import dao.BoardDAO;
import vo.BoardBean;

public class QnReWriteService {

	// 사용자가 상품 문의한 것을 불러옴
	public BoardBean getBoard(int boardNum, int kID) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		BoardBean board = boardDAO.selectBoard(boardNum, kID);
				
		close(con);
		
		return board;
	}

	// 사용자가 상품 문의한 것과 관리자가 답변한 것을 모두 불러옴
	public ArrayList<BoardBean> getqnaList(int boardReRef, int kID) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> qnaList = boardDAO.selectqnaList(boardReRef, kID);
		
		close(con);
		
		return qnaList;
	}

}
