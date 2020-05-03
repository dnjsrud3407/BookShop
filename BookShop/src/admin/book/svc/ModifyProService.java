package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BookDAO;
import vo.BookBean;

public class ModifyProService {

	public boolean modifyArticle(BookBean book) {
	    int updateCount = 0;
        boolean isModifySuccess = false;
        Connection con = getConnection();
        BookDAO bookDAO = BookDAO.getInstance();
        bookDAO.setConnection(con);
        
        updateCount = bookDAO.updateBook(book);
        if(updateCount > 0) {
            commit(con);
            isModifySuccess = true;
        } else {
            rollback(con);
        }
        
        close(con);
        
        return isModifySuccess;
	}

}
