package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;


public class QnReWriteProService {

	// boardNum 생성
	public int getBoardNum(int kID) { 
		int boardNum = 0;
		BoardDAO boardDAO = new BoardDAO();
        Connection con = getConnection();
        boardDAO.setConnection(con);
        
        boardNum = boardDAO.selectMaxNum(kID);
        
        close(con);
		
		return boardNum;
	}
	

	// 답변 글 적기
	public int writeAnswerBoard(BoardBean board) {
		BoardDAO boardDAO = new BoardDAO();
        Connection con = getConnection();
        boardDAO.setConnection(con);
		
        int insertCount = boardDAO.insertAnswerBoard(board);
        int updateCount = 0;
        
        // 답변 글 작성 성공 시 문의, 답변글 boardReSeq 를 1로 바꿔야함
        if(insertCount > 0) {
        	updateCount = boardDAO.updateReSeqPlus(board);
        	if(updateCount > 0) {
        		commit(con);
        	} else {
        		rollback(con);
        	}
        } else {
        	rollback(con);
        }
        
        close(con);
        
        return updateCount;
	}


	public void plusMemberPoint(String boardWriter) {
		BoardDAO boardDAO = new BoardDAO();
        Connection con = getConnection();
        boardDAO.setConnection(con);
        int updateCount = 0;
        int insertPointHistory = 0;
        
        updateCount = boardDAO.updateMemberPoint(boardWriter);
        
        if(updateCount > 0) {
        	
        	// pointhistory 테이블의 식별자 최대값 가져오기
        	int pID = boardDAO.selectpIDMaxNum()+1;
        	insertPointHistory = boardDAO.insertPointHistory(pID, boardWriter);
        	if(insertPointHistory > 0) {
        		commit(con);
        	} else {
        		rollback(con);
        	}
    	} else {
    		rollback(con);
    	}
        
        close(con);
	}

}
