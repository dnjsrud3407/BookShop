package member.account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.BookBean;

public class MiddleBookService {

	// Main에서 새로운 책 들고옴 (신간)
	public ArrayList<BookBean> getMiddleBookList() {
		ArrayList<BookBean> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookList = bookDAO.selectMiddleBookList();
		
		close(con);
		
		return bookList;
	}

	// Main에서 새로운 책 들고옴 (1,2,3단계 책)
	public ArrayList<BookBean> getMiddleBookList(String bK2) {
		ArrayList<BookBean> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookList = bookDAO.selectMiddleBookList(bK2);
		
		close(con);
		
		return bookList;
	}

	// 베스트 셀러 5개 들고옴
	public ArrayList<BookBean> getBestBookList() {
		ArrayList<BookBean> bestList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bestList = bookDAO.selectBestBookList();
		
		close(con);
		
		return bestList;
	}
	
}
