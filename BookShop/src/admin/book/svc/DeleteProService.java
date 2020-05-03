package admin.book.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BookDAO;

public class DeleteProService {

	// 관리자 비밀번호 일치 여부//
    public boolean isRightUser(String uID, String pw) {
        boolean isRightUser = false;
        Connection con = getConnection();
        BookDAO bookDAO = BookDAO.getInstance();
        bookDAO.setConnection(con);
        
        isRightUser = bookDAO.isRightUser(uID, pw);
        
        close(con);
        
        return isRightUser;
    }

    // 책 삭제 
    public boolean removeArticle(String[] bookIDList) {
        boolean isremoveArticleSuccess = false;
        Connection con = getConnection();
        BookDAO bookDAO = BookDAO.getInstance();
        bookDAO.setConnection(con);
        int deleteCount = 0;
        int bookID = 0;
        
        if(bookIDList.length > 1) {	// 여러개 삭제하는 경우
        	for(int i = 0; i < bookIDList.length; i++) {
        		bookID = Integer.parseInt(bookIDList[i]);
        		deleteCount = bookDAO.deleteBook(bookID);
        	}
        } else {	// 한개 삭제하는 경우
        	System.out.println("한개 삭제하는 경우");
        	bookID = Integer.parseInt(bookIDList[0]);
        	deleteCount = bookDAO.deleteBook(bookID);
        }
        
        if (deleteCount > 0) {
            isremoveArticleSuccess = true;
            commit(con);
        } else {
            rollback(con);
        }
        
        close(con);
        
        return isremoveArticleSuccess;
    }

}
