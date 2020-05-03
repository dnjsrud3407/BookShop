package member.book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookDAO;
import vo.BookBean;

import static db.JdbcUtil.*;

public class BookListService {

	// 책 목록 가져오기
	public ArrayList<BookBean> getBookList(int page, int limit) {
		ArrayList<BookBean> bookList = null;
		
		Connection con = getConnection();
		
		BookDAO bookDAO = BookDAO.getInstance();
		
		bookDAO.setConnection(con);
		
		bookList = bookDAO.selectUserBookList(page, limit);
		
		close(con);
		
		return bookList;
	}
	
	
	// 단계 별 책 목록 가져오기
	public ArrayList<BookBean> userbk2BookList(int page, int limit, String bk2) {
		ArrayList<BookBean> bookList = null;
		
		Connection con = getConnection();
		
		BookDAO bookDAO = BookDAO.getInstance();
		
		bookDAO.setConnection(con);
		
		bookList = bookDAO.userbk2BookList(page, limit, bk2);
		
		close(con);
		
		return bookList;
	}
	
	// 과목별 책 목록 가져오기
	public ArrayList<BookBean> userbkIDBookList(int page, int limit, int bkID) {
		ArrayList<BookBean> bookList = null;
		
		Connection con = getConnection();
		
		BookDAO bookDAO = BookDAO.getInstance();
		
		bookDAO.setConnection(con);
		
		bookList = bookDAO.userbkIDBookList(page, limit, bkID);
		
		close(con);
		
		return bookList;
	}
	
	
	// 책 목록 개수
		public int getListCount() {
			int listCount = 0;
			Connection con = getConnection();
			BookDAO bookDAO = BookDAO.getInstance();
			bookDAO.setConnection(con);
			
			listCount = bookDAO.selectUserListCount();
			
			close(con);
			
			return listCount;
		}

//전체 책 조회
	public ArrayList<BookBean> getAllBookList(int page, int limit) {
	ArrayList<BookBean> bookAllList = null;
		
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookAllList=bookDAO.selectUserBookList(page,limit);
		
		close(con);
		return bookAllList;
	}

	// 단계별 책 카운트 갯수
	public int userbk2ListCount(String bk2) {
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		listCount = bookDAO.userbk2ListCount(bk2);
		
		close(con);
		
		return listCount;
		
	}

	// 과목별 책 카운트 갯수
	public int userbkIDListCount(int bkID) {
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		listCount = bookDAO.userbkIDListCount(bkID);
		
		close(con);
		
		return listCount;

	}

	// 책 카테고리 구하기
	public ArrayList<BookBean> getKategorie(String bk2) {
		ArrayList<BookBean> bookKategorie = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		bookKategorie = bookDAO.getKategorie(bk2);
		
		close(con);
		
		return bookKategorie;
	}

	

}
