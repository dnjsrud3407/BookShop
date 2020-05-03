package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class QnReDeleteProService {

	// 관리자 답변 게시글 삭제
	public boolean deleteBoard(String[] boardRe_refList, int kID) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = 0;
        int boardReref = 0;
		boolean isRemoveBoard = false;
		
		if(boardRe_refList.length > 1) {	// 여러개 삭제하는 경우
			for(int i = 0; i < boardRe_refList.length; i++) {
				boardReref = Integer.parseInt(boardRe_refList[i]);
        		deleteCount = boardDAO.deleteBoard(boardReref, kID);
        	}
		} else {	// 한개 삭제하는 경우
			boardReref = Integer.parseInt(boardRe_refList[0]);
        	deleteCount = boardDAO.deleteBoard(boardReref, kID);
        }
		
		int updateCount = 0;
		
		// 답변 글 삭제 성공 시 문의글 boardReSeq 를 0으로 바꿔야함
		if(deleteCount > 0) {			
			if(boardRe_refList.length > 1) {	// 여러개 삭제하는 경우
				for(int i = 0; i < boardRe_refList.length; i++) {
					boardReref = Integer.parseInt(boardRe_refList[i]);
					updateCount = boardDAO.updateReSeqMinus(boardReref, kID);
	        	}
			} else {	// 한개 삭제하는 경우
				boardReref = Integer.parseInt(boardRe_refList[0]);
				updateCount = boardDAO.updateReSeqMinus(boardReref, kID);
	        }
			
			if(updateCount > 0) {
				isRemoveBoard = true;
				commit(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRemoveBoard;
	}

}
