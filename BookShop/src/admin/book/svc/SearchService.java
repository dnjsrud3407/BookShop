package admin.book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.BookBean;

public class SearchService {

	public int getSearchListCount(String searchSql) {
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		int listCount = bookDAO.selectSearchListCount(searchSql);//
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<BookBean> getSearchBookList(String searchSql, int page, int limit) {
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		ArrayList<BookBean> bookList = bookDAO.selectSearchBookList(searchSql, page, limit);
		
		close(con);
		
		return bookList;
	}
	

}
