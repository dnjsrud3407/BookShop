package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import org.json.simple.JSONArray;

import dao.BookDAO;
import vo.BookBean;

public class BookDetailService {

	// 책 상세보기
	public BookBean getArticle(int bookID) {
	    Connection con = getConnection();
	    BookDAO bookDAO = BookDAO.getInstance();
	    bookDAO.setConnection(con);
	    
	    BookBean book = bookDAO.selectUserBook(bookID);
	    
	    close(con);

	    return book;
	}

	// 카테고리 가져오기//
//	public JSONArray getBKCategorie(int BKID) {
//		Connection con = getConnection();
//	    BookDAO bookDAO = BookDAO.getInstance();
//	    bookDAO.setConnection(con);
//	    JSONArray BKCategorie = bookDAO.selectBKCategorie(BKID);
//	    
//	    close(con);
//		
//	    return BKCategorie;
//	}

}
