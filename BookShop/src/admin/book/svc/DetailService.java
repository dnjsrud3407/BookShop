package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import org.json.simple.JSONArray;

import dao.BookDAO;
import vo.BookBean;

public class DetailService {

	// 책 상세보기
	public BookBean getArticle(int bookID) {
	    Connection con = getConnection();
	    BookDAO bookDAO = BookDAO.getInstance();
	    bookDAO.setConnection(con);
	    
	    BookBean book = bookDAO.selectBook(bookID);
	    
	    close(con);

	    return book;
	}

}
