package admin.book.svc;

import java.sql.Connection;
import java.util.ArrayList;


import dao.BookDAO;

import static db.JdbcUtil.*;


public class BKService {

	public ArrayList<String> getBKList(String col, String type) {
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		ArrayList<String> BKList = bookDAO.selectBKList(col, type);
		
		close(con);
		
		return BKList;
	}

}
