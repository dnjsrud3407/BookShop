package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import vo.BookBean;

public class BookDAO {
	public BookDAO() {}
	
	private static BookDAO instance = new BookDAO();

	public static BookDAO getInstance() { 
		return instance;
	}
	// ----------------------------------------------------
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}


	public BookBean selectreviews(int num) {
		BookBean books = null;

		return books;
	}
	
	public ArrayList<String> selectBKList(String col, String type) {
		ArrayList<String> BKList = new ArrayList<String>();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		String sql = "select distinct " + col + " from bookkategorie";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(type.equals("BK1")) {
				while(rs.next()){
					BKList.add(rs.getString("BK1"));
				}
			} else if(type.equals("BK2")) {
				while(rs.next()){
					BKList.add(rs.getString("BK1"));
					BKList.add(rs.getString("BK2"));
				}
			} else if(type.equals("BK3")) {
				while(rs.next()){
					BKList.add(rs.getString("BK1"));
					BKList.add(rs.getString("BK2"));
					BKList.add(rs.getString("BK3"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        if(rs != null) {close(rs);}
	        if(pstmt != null) {close(pstmt);}
	    }
		return BKList;
	}
	
	// 책 등록 시 카테고리 번호 구하기
	public int selectBKID(String BK1, String BK2, String BK3) {
		int BKID = 0;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT BKID FROM bookkategorie WHERE BK1=? AND BK2=? AND BK3=?";
	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, BK1);
	        pstmt.setString(2, BK2);
	        pstmt.setString(3, BK3);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	BKID = rs.getInt(1);  // 책 번호 최대값 + 1
	        	 System.out.println(BKID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if(rs != null) {close(rs);}
	        if(pstmt != null) {close(pstmt);}
	    }
	   
	    return BKID;
	}
	
	// 책 등록 시 책 번호 구하기(책 번호 중 최대값 구하기)
	public int selectMaxNum() {
	    int maxNum = 0;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT MAX(bookID) FROM book";
	    try {
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	            maxNum = rs.getInt(1) + 1;  // 책 번호 최대값 + 1
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if(rs != null) {close(rs);}
	        if(pstmt != null) {close(pstmt);}
	    }
	    
	    return maxNum;
	}

	// 책 등록하는 작업
	public int insertBook(BookBean book) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO book(bookID,bookTitle,bookOriginImage,bookImage,bookPublisher,bookPublishedDate,"
		        + "bookPrice,bookEA,salesVolume,bookIntroduce,bookisView,saveRatio,bookKategorie_BKID)"
		        + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, book.getBookID());
            pstmt.setString(2, book.getBookTitle());
            pstmt.setString(3, book.getBookOriginImage());
            pstmt.setString(4, book.getBookImage());
            pstmt.setString(5, book.getBookPublisher());
            // 자바의 Date 를 Mysql 의 Date 형으로 바로 넣을 수 없기 때문에 변환 필요
            if(book.getBookPublishedDate() == null) {	// 날짜 입력 못 받았을 경우
            	pstmt.setDate(6, null);
            } else {
            	pstmt.setDate(6, new java.sql.Date(book.getBookPublishedDate().getTime()));
            }
            pstmt.setInt(7, book.getBookPrice());
            pstmt.setInt(8, book.getBookEA());
            pstmt.setInt(9, book.getSalesVolume());
            pstmt.setString(10, book.getBookIntroduce());
            pstmt.setBoolean(11, book.isBookisView());
            pstmt.setFloat(12, book.getSaveRatio());
            pstmt.setInt(13, book.getBookKategorie_BKID());
            insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {close(pstmt);}
        }
		
		return insertCount;
	}
	
	// 책 상세보기에서 사용하는 selectBook
	public BookBean selectBook(int bookID) {
	    BookBean book = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM book JOIN bookkategorie ON book.bookKategorie_BKID = bookkategorie.BKID WHERE bookID=?";
	    try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookID);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                book = new BookBean(
                        rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"), 
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
            }
	    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
	    
	    return book;
	}
	
	
		
	// 책 삭제 시 비밀번호 확인
	public boolean isRightUser(String uID, String pw) {
	    boolean isRightUser = false;
	    PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT uID FROM user WHERE uID=? AND pw=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, uID);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                isRightUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
	    return isRightUser;
	}

	// 책 삭제하기
	public int deleteBook(int bookID) {
	    int deleteCount = 0;
	    PreparedStatement pstmt = null;
        String sql = "UPDATE book SET bookisView=? WHERE bookID=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, bookID);
            deleteCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {close(pstmt);}
        }
        
	    return deleteCount;
	}

	// 책 수정하기 
	public int updateBook(BookBean book) {
	    int updateCount = 0;
	    PreparedStatement pstmt = null;
        String sql = "UPDATE book SET bookTitle=?,bookOriginImage=?,bookImage=?,"
                + "bookPublisher=?,bookPublishedDate=?,bookPrice=?,bookEA=?,"
                + "bookIntroduce=?,bookisView=?,saveRatio=?,bookKategorie_BKID=? "
                + "WHERE bookID=?";
	    try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, book.getBookTitle());
            pstmt.setString(2, book.getBookOriginImage());
            pstmt.setString(3, book.getBookImage());
            pstmt.setString(4, book.getBookPublisher());
            // 자바의 Date 를 Mysql 의 Date 형으로 바로 넣을 수 없기 때문에 변환 필요
            if(book.getBookPublishedDate() == null) {	// 날짜 입력 못 받았을 경우
            	pstmt.setDate(5, null);
            } else {
            	pstmt.setDate(5, new java.sql.Date(book.getBookPublishedDate().getTime()));
            }
            pstmt.setInt(6, book.getBookPrice());
            pstmt.setInt(7, book.getBookEA());
            pstmt.setString(8, book.getBookIntroduce());
            pstmt.setBoolean(9, book.isBookisView());
            pstmt.setFloat(10, book.getSaveRatio());
            pstmt.setInt(11, book.getBookKategorie_BKID());
            pstmt.setInt(12, book.getBookID());
            updateCount = pstmt.executeUpdate();
	    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {close(pstmt);}
        }
	    
	    return updateCount;
	}
	
	// 책 목록 개수
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM book";
        try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return listCount;
	}
	
	// 책 목록 가져오기 
	public ArrayList<BookBean> selectBookList(int page, int limit) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "on book.bookKategorie_BKID = bookkategorie.BKID ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        
        int startRow = (page - 1) * limit;
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
				
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return bookList;
	}
	
	
	// 단계별 책 목록 가져오기 
	public ArrayList<BookBean> selectBookList(int page, int limit, int bk2) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "on book.bookKategorie_BKID = bookkategorie.BKID where bk2=? ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        
        int startRow = (page - 1) * limit;
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bk2);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
				
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return bookList;
	}
		
	// 검색한 결과 들고오기 (책 정보 and 책 리스트 사이즈)
	public ArrayList<BookBean> selectSearchBookList(String searchSql, int page, int limit) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book JOIN bookkategorie "
        		+ "ON book.bookKategorie_BKID = bookkategorie.BKID WHERE 1=1"+ searchSql 
        		+ " ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        int startRow = (page - 1) * limit;

        try {
        	// 페이징 처리////
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		return bookList;
	}
	
	// 검색한 결과 사이즈 구하기
	public int selectSearchListCount(String searchSql) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        int listCount = 0;
        String sql = "SELECT COUNT(*) FROM book JOIN bookkategorie "
        		+ "ON book.bookKategorie_BKID = bookkategorie.BKID WHERE 1=1" + searchSql;
        try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return listCount;
	}


	// 책 검색한 목록 가져오기
	public ArrayList<BookBean> selectSearchBookList(int page, int limit, String bookTitle) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book JOIN bookkategorie "
        		+ "ON book.bookKategorie_BKID = bookkategorie.BKID WHERE bookisView=? AND book.bookTitle LIKE ?" 
        		+ " ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        int startRow = (page - 1) * limit;

        try {
        	// 페이징 처리////
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, "%" + bookTitle + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		return bookList;
	}

	// 책 검색한 목록 사이즈 구하기
	public int selectSearchListCountMem(String bookTitle) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        int listCount = 0;
        String sql = "SELECT COUNT(*) FROM book JOIN bookkategorie "
        		+ "ON book.bookKategorie_BKID = bookkategorie.BKID WHERE bookisView=? AND book.bookTitle LIKE ?";
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, "%" + bookTitle + "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return listCount;
	}
	
	// Main에서 중간 배너 (신권 8권)가져오기
	public ArrayList<BookBean> selectMiddleBookList() {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book WHERE bookisView=? "
        		+ "ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;

        try {
        	// 페이징 처리////
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 8);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice")
                        );
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		return bookList;
	}


	// 사용자 전체 책 목록 개수
		public int selectUserListCount() {
			int listCount = 0;
			PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String sql = "SELECT COUNT(*) FROM book where bookisView=true";
	        try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
	        } catch (SQLException e) {
				e.printStackTrace();
			} finally {
	            if(rs != null) {close(rs);}
	            if(pstmt != null) {close(pstmt);}
	        }
	        
			return listCount;
		}
		
	// 단계별로 카운트 갯수
	public int userbk2ListCount(String bk2) {
		int listCount = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM book join bookkategorie k "
        		+ "on book.bookKategorie_BKID=k.bkid where bk2=? and bookisView=true";
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bk2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return listCount;
	}
	// 과목별로 카운트 갯수
	public int userbkIDListCount(int bkID) {
		int listCount = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM book join bookkategorie k "
        		+ "on book.bookKategorie_BKID=k.bkid where bookisView=true and bookkategorie_BKID=?";
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bkID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return listCount;
	}
		
	// Main에서 중간 배너 (1,2,3 단계 8권)가져오기
	public ArrayList<BookBean> selectMiddleBookList(String bK2) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "ON book.bookKategorie_BKID = bookkategorie.BKID WHERE BK2=? AND bookisView=? "
        		+ "ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;

        try {
        	// 페이징 처리////
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bK2);
			pstmt.setBoolean(2, true);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 8);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice")
                        );
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		return bookList;
	}

	// main에서 베스트셀러 5개 들고옴
	public ArrayList<BookBean> selectBestBookList() {
		ArrayList<BookBean> bestList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT bookID, bookTitle, bookImage FROM book "
        		+ "WHERE bookisView=? "
        		+ "ORDER BY salesVolume DESC, bookID DESC LIMIT ?,?";
        BookBean book = null;

        try {
        	// 페이징 처리////
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 5);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookImage")
                        );
				bestList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		return bestList;
	}

	
	// 사용자 책 상세보기에서 사용하는 selectBook
	public BookBean selectUserBook(int bookID) {
	    BookBean book = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM book JOIN bookkategorie ON book.bookKategorie_BKID = bookkategorie.BKID WHERE bookID=? and bookisView=true";
	    try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookID);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                book = new BookBean(
                        rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"), 
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
            }
	    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
	    
	    return book;
	}

		
	// 사용자 책 목록 전체 가져오기 (페이징처리 갯수만큼)
	public ArrayList<BookBean> selectUserBookList(int page, int limit) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "on book.bookKategorie_BKID = bookkategorie.BKID where bookisView=true ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        
        int startRow = (page - 1) * limit;
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
				
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return bookList;
	}
	
	

	// 사용자 단계별 책 목록 가져오기 
	public ArrayList<BookBean> userbk2BookList(int page, int limit, String bk2) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "on book.bookKategorie_BKID = bookkategorie.BKID where bk2=? and bookisView=true ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        
        int startRow = (page - 1) * limit;
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bk2);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
				
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return bookList;
	}
	
	// 사용자 과목별 책 목록 가져오기 
		public ArrayList<BookBean> userbkIDBookList(int page, int limit, int bkID) {
			ArrayList<BookBean> bookList = new ArrayList<BookBean>();
			PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String sql = "SELECT * FROM book join bookkategorie "
	        		+ "on book.bookKategorie_BKID = bookkategorie.BKID where bookkategorie_BKID=? and bookisView=true ORDER BY bookID DESC LIMIT ?,?";
	        BookBean book = null;
	        
	        int startRow = (page - 1) * limit;
	        
	        try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bkID);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					book = new BookBean(
							rs.getInt("bookID"), 
	                        rs.getString("bookTitle"), 
	                        rs.getString("bookOriginImage"), 
	                        rs.getString("bookImage"), 
	                        rs.getString("bookPublisher"), 
	                        rs.getDate("bookPublishedDate"), 
	                        rs.getInt("bookPrice"), 
	                        rs.getInt("bookEA"), 
	                        rs.getInt("salesVolume"),
	                        rs.getString("bookIntroduce"), 
	                        rs.getBoolean("bookisView"), 
	                        rs.getFloat("saveRatio"),
	                        rs.getInt("bookKategorie_BKID"),
	                        rs.getString("BK1"),
	                        rs.getString("BK2"),
	                        rs.getString("BK3")
	                        );
					bookList.add(book);
					
				}
	        } catch (SQLException e) {
				e.printStackTrace();
			} finally {
	            if(rs != null) {close(rs);}
	            if(pstmt != null) {close(pstmt);}
	        }
	        
			return bookList;
		}
		
	// 메인에서 판매중, 수정중 상품 개수 구하기
	public ArrayList<Integer> selectBookEA() {
		ArrayList<Integer> bookEAList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        int eaAll = 0, ea = 0;
        
        String sql = "SELECT COUNT(*) FROM book";
        try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {	// 전체 상품 개수
				eaAll = rs.getInt(1);
			}
			
			sql = "SELECT COUNT(*) FROM book WHERE bookisView=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			rs = pstmt.executeQuery();
			if (rs.next()) {	// 판매 중 책 개수
				ea = rs.getInt(1);
			}
			bookEAList.add(ea);
			bookEAList.add(eaAll-ea);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookEAList;
	}

	


	// 전체 책 목록을 위해 가져오기 
	public ArrayList<BookBean> selectUserBookAllList(int page, int limit) {
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM book join bookkategorie "
        		+ "on book.bookKategorie_BKID = bookkategorie.BKID where bookisView=true ORDER BY bookID DESC LIMIT ?,?";
        BookBean book = null;
        
        int startRow = (page - 1) * limit;
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new BookBean(
						rs.getInt("bookID"), 
                        rs.getString("bookTitle"), 
                        rs.getString("bookOriginImage"), 
                        rs.getString("bookImage"), 
                        rs.getString("bookPublisher"), 
                        rs.getDate("bookPublishedDate"), 
                        rs.getInt("bookPrice"), 
                        rs.getInt("bookEA"), 
                        rs.getInt("salesVolume"),
                        rs.getString("bookIntroduce"), 
                        rs.getBoolean("bookisView"), 
                        rs.getFloat("saveRatio"),
                        rs.getInt("bookKategorie_BKID"),
                        rs.getString("BK1"),
                        rs.getString("BK2"),
                        rs.getString("BK3")
                        );
				bookList.add(book);
				
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
        
		return bookList;
	}

	// 책 카테고리 구하기
	public ArrayList<BookBean> getKategorie(String bk2) {
		ArrayList<BookBean> bookKategorie = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM bookkategorie WHERE BK2=?";
        
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bk2);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookBean book = new BookBean(
						rs.getInt("BKID"),
						rs.getString("BK1"),
						rs.getString("BK2"),
						rs.getString("BK3"));
				bookKategorie.add(book);
			}
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		
		return bookKategorie;
	}

	


	


	


	


	



}
