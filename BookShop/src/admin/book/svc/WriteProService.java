package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BookDAO;
import vo.BookBean;

public class WriteProService {

	// BKID 찾기
	public int getBKID(String BK1, String BK2, String BK3) {
		int BKID = 0;
		BookDAO bookDAO = new BookDAO();
        Connection con = getConnection();
        bookDAO.setConnection(con);
        
        BKID = bookDAO.selectBKID(BK1, BK2, BK3);
        close(con);
        
		return BKID;
	}
	
	// bookID 생성
    public int getBookID() {
        int bookID = 0;
        BookDAO bookDAO = new BookDAO();
        Connection con = getConnection();
        bookDAO.setConnection(con);
        
        bookID = bookDAO.selectMaxNum();
        close(con);
        
        return bookID;
    }
    
    // 책 등록
	public boolean writeArticle(BookBean book) {
	    boolean iswriteArticleSuccess = false;
	    BookDAO bookDAO = new BookDAO();
	    Connection con = getConnection();
	    bookDAO.setConnection(con);
	    
	    int insertCount = bookDAO.insertBook(book);
	    
	    if(insertCount > 0) {
	        iswriteArticleSuccess = true;
	        commit(con);
	    } else {
	        iswriteArticleSuccess = false;
	        rollback(con);
	    }
	    close(con);
	    return iswriteArticleSuccess;
	}

	


}
