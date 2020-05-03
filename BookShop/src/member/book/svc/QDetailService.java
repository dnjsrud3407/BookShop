package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BookDAO;
import vo.BookBean;

public class QDetailService {

	public BookBean getQuestion(int num) {

System.out.println("QDetailService - getQuestion");
		
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance(); 
//		bookDAO.setConnection(con);
//		
//		BookBean question = null;

//		question = bookDAO.selectQuestion(num);

//		int updateCount = boardDAO.updateReadcount(board_num);

		//		if(updateCount > 0) {
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}
		
//		close(con);
		
		return null;
	}
	//조회수 증가용
	public void plusReadcount(int num) throws Exception {
		//게시글중 한개 클릭시
		System.out.println("QDetailService - getArticle");
		
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance(); 
//		bookDAO.setConnection(con);
//		
//		int updateCount = bookDAO.updateReadcount(num);
//
//		if(updateCount > 0) {
//			commit(con);
//			
//		}else {
//			rollback(con);
//		}
//		
//		close(con);

	}

}
