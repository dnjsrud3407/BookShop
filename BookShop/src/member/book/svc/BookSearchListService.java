package member.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.BookBean;

import static db.JdbcUtil.*;

public class BookSearchListService {

	// 검색한 책 목록 가져오기
	public ArrayList<BookBean> getBookSearchList(int page, int limit, String bookTitle) {
		ArrayList<BookBean> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookList = bookDAO.selectSearchBookList(page, limit, bookTitle);
		
		close(con);
		
		return bookList;
	}

	// 검색한 책 목록 개수 
	public int getSearchListCountMem(String bookTitle) {
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int listCount = 0;
		
		listCount = bookDAO.selectSearchListCountMem(bookTitle);
		
		close(con);
		
		return listCount;
	}

}
