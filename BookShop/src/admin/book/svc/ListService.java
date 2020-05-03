package admin.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;

import static db.JdbcUtil.*;

import vo.BookBean;

public class ListService {

	// 책 목록 개수
	public int getListCount() {
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		listCount = bookDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	// 책 목록 들고오기
	public ArrayList<BookBean> getBookList(int page, int limit) {
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		ArrayList<BookBean> bookList = bookDAO.selectBookList(page, limit);
		
		close(con);
		
		return bookList;
	}

}
