package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

public class QDetailService {


	public BoardBean getOneonOnegetArticle(int boardNum, int kID) {
		//1:1문의 상세내용
		System.out.println("QDetailService getOneonOnegetArticle(int boardNum)");
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		
		BoardBean boardBean = dao.getOneonOnegetArticle(boardNum,kID);
		 
		
		close(con);
		
		return boardBean;
	}


	public BoardBean getOneonOnegetAnswer(int boardNum, int kID) {
		//1:1문의 상세내용 답변
		System.out.println("QDetailService getOneonOnegetAnswer(int boardNum)");
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		BoardBean boardBean2 = dao.getOneonOnegetAnswer(boardNum,kID);
		
		close(con);
		return boardBean2;
	}


	
	public void getArticle() {
		
	}


	public int updateReadCount(int boardNum) {
		int result=0;
		
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		dao.setConnection(con);
		result=dao.updateReadCount(boardNum);
		
		if (result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}
}
