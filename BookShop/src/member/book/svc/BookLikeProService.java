package member.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BookDAO;
import vo.BookBean;

public class BookLikeProService {
	public BookBean getBooks(int num) {
		
		System.out.println("BookListService - getBooks");
//		Connection con = getConnection();
//		BookDAO bookDAO = BookDAO.getInstance();
//		
		BookBean books = null;
//		 books = bookDAO.selectBooks(num);
		
//		close(con);

		return books;
	}

}
