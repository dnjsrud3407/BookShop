package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BookBean;
import vo.CartBean;

import static db.JdbcUtil.*;

public class CartDAO {
	
	public CartDAO() {}
	
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}

	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; 
	
	public void setConnection(Connection con) {
		this.con = con;
	}


	// 세션에 해당하는 카트(상품리스트) 가져오는 메서드
	public ArrayList<CartBean> getCartList(String uID) {
		// TODO Auto-generated method stub
		ArrayList<CartBean> cartList = new ArrayList<CartBean>();
		
		String sql = " select cartNum, bookImage, bookTitle, cart.bookEA, bookPrice "
				+ "from cart join book on book.bookID = cart.book_bookID where user_uID=?";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartBean cartBean = new CartBean();
				cartBean.setCartNum(rs.getInt("cartNum"));

				cartBean.setBookImage(rs.getString("bookImage"));
				cartBean.setBookTitle(rs.getString("bookTitle"));
				cartBean.setBookEA(rs.getInt("cart.bookEA"));
				cartBean.setBookPrice(rs.getInt("bookPrice"));
				
				cartList.add(cartBean);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return cartList;
	}
	
	//달라지는 점은 카트넘이 필요없다는것
	public ArrayList<CartBean> getCartList(String uID, String cartNumStr) {
		ArrayList<CartBean> cartList = new ArrayList<CartBean>();
		
		String sql = "SELECT cartNum, cart.book_bookID, bookImage, bookTitle, cart.bookEA, bookPrice "
				+ "FROM cart JOIN book ON book.bookID = cart.book_bookID "
				+ "WHERE user_uID=? AND cartNum In (" + cartNumStr + ")";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartBean cartBean = new CartBean();
				cartBean.setCartNum(rs.getInt("cartNum"));
				cartBean.setBookID(rs.getInt("book_bookID"));
				cartBean.setBookImage(rs.getString("bookImage"));
				cartBean.setBookTitle(rs.getString("bookTitle"));
				cartBean.setBookEA(rs.getInt("cart.bookEA"));
				cartBean.setBookPrice(rs.getInt("bookPrice"));
				
				cartList.add(cartBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cartList;
	}

	// 카트에 상품 추가
	public int addCart(BookBean bookBean, String uID, int qty) {
		// TODO Auto-generated method stub
		int cartAddResult = 0;
		String sql = "insert into cart values(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookBean.getBookID());
			pstmt.setString(2, uID);
			pstmt.setInt(3, bookBean.getBookID());
			pstmt.setInt(4, qty);
			
			cartAddResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return cartAddResult;
		
	}


	// 카트 상품 수량 추가
	public int updateQtyCart(BookBean bookBean, String uID, int qty) {
		// TODO Auto-generated method stub
		int qtyUpdateResult = 0;
		
		String sql = "update cart set bookEA =  bookEA + ? where cartNum = ? and user_uID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qty);
			pstmt.setInt(2, bookBean.getBookID());
			pstmt.setString(3, uID);
			
			qtyUpdateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return qtyUpdateResult;
	}

	// 카트 상품 수량 변경
		public int changeQtyCart(BookBean bookBean, String uID, int qty) {
			// TODO Auto-generated method stub
			int qtyUpdateResult = 0;
			
			String sql = "update cart set bookEA = ? where cartNum = ? and user_uID = ?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, qty);
				pstmt.setInt(2, bookBean.getBookID());
				pstmt.setString(3, uID);
				
				qtyUpdateResult = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			 
			return qtyUpdateResult;
		}

		// 카트상품 삭제
		public int cartRemove(int cartNum, String uID) {
			// TODO Auto-generated method stub
			int cartRemoveResult = 0;
			
			String sql ="delete from cart where cartNum=? and user_uID=?";
					
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cartNum);
				pstmt.setString(2, uID);
				cartRemoveResult = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return cartRemoveResult;
		}


		


		

	


	
	
	
	
}
