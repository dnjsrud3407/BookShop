package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BoardBean;
import vo.CouponBean;
import vo.FileBean;
import vo.PageInfo;

import static db.JdbcUtil.*;

public class AdminBoardDAO {
	public AdminBoardDAO() {}
	
	private static AdminBoardDAO instance = new AdminBoardDAO();
	
	public static AdminBoardDAO getInstance() {
		return instance;
	}
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public void setConnection(Connection con) {
		this.con = con;
	}
	////////////////////////////////////////////////////////////////////////////

	// ************카테고리 번호(kID) 를 가져오는 메서드 get_kId
		public int get_kID(String k1, String k2) {
			int kID = -1;
			String sql = "";
			
			if(k2 != null) {
				sql = "SELECT kID FROM kategorie Where k1=? and k2=?";
			} else {
				sql = "SELECT kID FROM kategorie WHERE k1=? and k2<=>?";
			}
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k1);
				pstmt.setString(2, k2);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					kID = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs); close(pstmt);
			}
			
			return kID;
		}
	// ***************해당 대분류(k1)에서 가장 큰 글 번호를 가져오는 메서드 getMaxNum
	public int getMaxNum(String k1) {
		int maxNum = 0;
		
		String sql = "SELECT MAX(boardNum) FROM board WHERE kID IN (SELECT kID FROM kategorie WHERE k1=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, k1);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxNum = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return maxNum;
	}
	//
	//
	//
	//
	//  글 작성
	
	public int insertArticle(BoardBean bb) {
		int insertCount = 0;
		
		int kID = get_kID(bb.getK1(), bb.getK2()); //  1:1 문의 답변 용으로 따로 체크해야함
		String sql = "INSERT INTO board(boardNum, kID, boardWriter, boardTitle, boardContent, boardRegTime, boardReRef, boardReLev, boardReSeq, boardReadcount) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getBoardNum()); pstmt.setInt(2, kID); pstmt.setString(3, bb.getBoardWriter());
			pstmt.setString(4, bb.getBoardTitle()); pstmt.setString(5, bb.getBoardContent());
			pstmt.setTimestamp(6, bb.getBoardRegTime()); pstmt.setInt(7, bb.getBoardReRef()); 
			pstmt.setInt(8, bb.getBoardReLev()); pstmt.setInt(9, bb.getBoardReSeq()); 
			pstmt.setInt(10, bb.getBoardReadcount());
			int update = pstmt.executeUpdate();
			if(update != 0 && (bb.getFileList() != null && bb.getFileList().size() != 0)) {
				if(bb.getFileList().size() != 0) {
					insertCount = insertFile(bb, kID);
				} else {
					insertCount = update;
				}
			} else {
				insertCount = update;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	
	// 글 쓸때 파일을 같이 업로드 하는 insertFile 메서드
	public int insertFile(BoardBean  bb, int kID) {
		int insertCount = 0;
		int count = 0;
		int listSize = bb.getFileList().size();
		try {
			for(FileBean file : bb.getFileList()) {
				String sql = "INSERT INTO boardFile VALUES(null, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, file.getOriginFilename());
				pstmt.setString(2, file.getstoredFileName());
				pstmt.setString(3, file.getFileType());
				pstmt.setInt(4, bb.getBoardNum());
				pstmt.setInt(5, kID);
				pstmt.setString(6, bb.getBoardWriter());
			
				count += pstmt.executeUpdate();
			}
			if(count != listSize) {
				// DB에 업로드 되지 않은 파일이 있을 경우 insertCount는 0이다.
			} else {
				insertCount = 1; // DB에 모든 파일이 업로드 되었을 경우 1을 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	
	// 글작성
	//
	//
	//
	//
	//
	//
	//
	//
	//-------------------------------------------------------------------------------
	// 글 내용 보는 메서드들
	
	public BoardBean selectArticle(int boardNum, String k1) {
		BoardBean bb = null;
		String sql = "";
		
		try {
			sql = "SELECT b.*, k.k1, k.k2 FROM board b JOIN kategorie k ON b.kID=k.kID WHERE b.boardNum=? AND k.k1=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bb = new BoardBean();
						bb.setBoardNum(rs.getInt("b.boardNum")); bb.setK1(rs.getString("k.k1")); bb.setK2(rs.getString("k.k2"));
						bb.setBoardWriter(rs.getString("b.boardWriter")); bb.setBoardTitle(rs.getString("b.boardTitle")); bb.setBoardContent(rs.getString("b.boardContent"));
						bb.setBoardRegTime(rs.getTimestamp("b.boardRegTime")); bb.setBoardReRef(rs.getInt("b.boardReRef")); bb.setBoardReLev(rs.getInt("b.boardReLev"));
						bb.setBoardReSeq(rs.getInt("b.boardReSeq")); bb.setBoardReadcount(rs.getInt("b.boardReadcount")); bb.setBookID(rs.getInt("b.bookID"));
				bb.setFileList(selectFileList(boardNum, k1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return bb;
	}
	// 파일 가져오기
	public List<FileBean> selectFileList(int boardNum, String k1) {
		
		List<FileBean> fileList = new ArrayList<FileBean>();
		FileBean file = null;
		ResultSet rsFile = null;
		PreparedStatement pstmtFile = null;
		
		String sql = "SELECT * FROM boardFile WHERE board_boardNum=? AND board_kID IN (SELECT kID FROM kategorie WHERE k1=?)";
		
		try {
			pstmtFile = con.prepareStatement(sql);
			pstmtFile.setInt(1, boardNum);
			pstmtFile.setString(2, k1);
			
			rsFile = pstmtFile.executeQuery();
			
//			System.out.println(rsFile.next() + "selectFileList");
			while(rsFile.next()) {
				file = new  FileBean(rsFile.getInt("fileNum"), rsFile.getString("originFilename"),
						rsFile.getString("storedFileName"), rsFile.getString("fileType"),
						rsFile.getInt("board_boardNum"), rsFile.getInt("board_kID"),
						rsFile.getString("board_boardWriter"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rsFile); close(pstmtFile);
		}
		
		
		return fileList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public int get_kID(int boardNum, String k1) {
//		int kID = -1;
//		
//		String sql = "SELECT b.kID FROM board b JOIN kategorie k ON b.kID=k.kID WHERE b.boardNum=? AND k.k1=?";
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				kID = rs.getInt("b.kID");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs); close(pstmt);
//		}
//		
//		
//		return kID;
//	}
	
	// 조회수 증가 메서드
		public int increaseReadCount(int boardNum, int kID) {
			int updateCount = 0;
			
			String sql = "UPDATE board SET readCount=readCount+1 WHERE boardNum=? AND kID=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum); pstmt.setInt(2, kID);
				updateCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}
	
	
	

	
	
	
	//
	//
	//-------------------------------------------------------------------------------
	// 글 내용 보는 메서드들
	
	// 글 삭제하는 메서드들
	
		public int deleteArticle(int boardNum, String k1) {
			int deleteCount = 0;
			int boardReRef = 0;
//			System.out.println("deleteArticle  번호 : " + boardNum + " // 카테고리1 : " + k1 );
			
			List<FileBean> fileList = selectFileList(boardNum, k1);
			
			if(fileList.size() > 0) {
				int deleteFile = deleteAllFile(boardNum, k1);
				if(deleteFile == 0) {
					return 0;
				}
			}
			String sql = "";
			
			try {
				sql = "SELECT boardReRef FROM board WHERE boardNum=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?) AND boardNum != boardReRef";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {boardReRef = rs.getInt("boardReRef");}
//				System.out.println(boardReRef + " : reRef");
				
				sql = "DELETE FROM board WHERE boardNum=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
				
				deleteCount = pstmt.executeUpdate();
//				System.out.println(deleteCount + " : 삭제여부");
				
				if(deleteCount != 0 && boardReRef != 0) { // 삭제한 것이 답변 글 일 경우 원 질문글의 boardReLev 를 0으로 바꿔주는(미답변 글로 바꿔주는) 작업
					sql = "UPDATE board SET boardReLev=0 WHERE boardReRef=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, boardReRef); pstmt.setString(2, k1);
					
					int levDown = pstmt.executeUpdate();
//					System.out.println(levDown + " : Lev 변경 여부");
					if(levDown != 0) {
						
					} else {
						deleteCount = 0;
					}
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return deleteCount;
		}
		
		
	
		public int deleteAllFile(int boardNum, String k1) {
			int deleteFile = 0;
			
			String sql = "DELETE FROM boardFile WHERE board_boardNum=? AND board_kID In (SELECT kID FROM kategorie WHERE k1=?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
				
				deleteFile = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return deleteFile;
		}
	
	
	
	
	
	//
	//
	// 글 수정하는 메서드들

	public int updateArticle(BoardBean bb, List<String> deleteFileName) {
		int updateCount = 0;
		
		int kID = get_kID(bb.getK1(), bb.getK2());
		System.out.println(kID + " 카테고리번호");
		if(deleteFileName != null && deleteFileName.size() > 0) { deleteFile(bb, deleteFileName, bb.getK1()); } // kID가 바뀌었을 수 있음(k2의 변경)
		
		System.out.println("글번호 : " + bb.getBoardNum() + "\n k1 : " + bb.getK1());
		String sql = "UPDATE board SET kID=?, boardTitle=?, boardContent=? WHERE boardNum=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kID); pstmt.setString(2, bb.getBoardTitle()); 
			pstmt.setString(3, bb.getBoardContent());
			pstmt.setInt(4, bb.getBoardNum()); pstmt.setString(5, bb.getK1());
			
			updateCount = pstmt.executeUpdate();
			System.out.println(updateCount + "파일수정실행");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteFile(BoardBean bb, List<String> deleteFileName, String k1) {
		int deleteCount = 0;
		
		String sql = "DELETE FROM boardFile WHERE storedFileName=? AND board_boardNum=? AND board_kID IN (SELECT kID FROM kategorie WHERE k1=?";
		
		for(String delFileName : deleteFileName) {
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, delFileName);
				pstmt.setInt(2, bb.getBoardNum());
				pstmt.setString(3, k1);
				
				deleteCount += pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		
		if(deleteCount != deleteFileName.size()) {
			deleteCount = 0;
		}
		
		return deleteCount;
	}

	//
	//
	//
	//
	//
	//
	//
	//
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 글 목록 관련 메서드
	
	
	public int selectListCount(String k1, String k2) {
		int listCount = 0;
		String sql = "";
		int kID = get_kID(k1, k2);
		
		try {
			if(k2 != null) {
				sql = "SELECT count(*) FROM board WHERE kID=? AND boardReLev<=1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, kID);
			} else {
				sql = "SELECT count(*) FROM board WHERE kID IN (SELECT kID FROM kategorie WHERE k1=?) AND boardReLev<=1";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k1);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<BoardBean> selectArticleList(PageInfo pageInfo) {
		// 게시물 목록 조회 후 리턴
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		int boardNum=0;
		int page = pageInfo.getPage();
		int limit = pageInfo.getLimit();
		String k1 = pageInfo.getK1();
		String k2 = pageInfo.getK2();
		/* 
		 * 전체 게시물 중 원하는 페이지의 게시물 첫번째 row 번호 설정
		 * - 원본 글 번호(board_re_ref) 기준으로 내림차순 정렬
		 * - 글 순서번호(board_re_seq) 기준으로 오름차순 정렬
		 * - 조회할 게시물 갯수 : 첫번째 게시물 위치 ~ limit 수 만큼
		 *   첫번째 게시물 위치 = (현재페이지 - 1) * 10
		 * 
		 * ex) 현재 페이지(page) 가 1 페이지 일 경우 : 게시물 조회 결과의 0번 행부터 10개 가져오기
		 */
		int startRow = (page - 1) * limit; // 첫번째 게시물 행(row) 번호 계산
		String sql ="";
		int kID = get_kID(k1, k2);
		try {
			// 조회 결과(ResultSet) 객체가 존재할 경우
			// 반복문을 사용하여 1개 게시물 정보(패스워드 제외한 나머지)를 BoardBean 객체에 저장하고
			// BoardBean 객체를 ArrayList<BoardBean> 객체에 저장 반복
			if(k2 != null) {
				sql = "SELECT b.*, k.k1, k.k2 FROM board b JOIN kategorie k ON b.kID=k.kID WHERE b.kID=? AND boardReLev<=1 ORDER BY boardReRef DESC, boardReSeq ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, kID);
				System.out.println(k2);
			} else {
				sql = "SELECT b.*, k.k1, k.k2 FROM board b JOIN kategorie k ON b.kID=k.kID WHERE b.kID IN (SELECT kID FROM kategorie WHERE k1=?) AND boardReLev<=1 "
						+ "ORDER BY boardReRef DESC, boardReSeq ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k1);
			}
			pstmt.setInt(2, startRow); pstmt.setInt(3, limit);
            rs = pstmt.executeQuery();
            // ResultSet 객체 내의 모든 레코드를 각각 레코드별로 BoardBean 에 담아서 ArrayList 객체에 저장
            // => 패스워드 제외 
            while(rs.next()) {
                BoardBean boardBean = new BoardBean();
                boardBean.setBoardNum(rs.getInt("b.boardNum"));
                boardNum=rs.getInt("b.boardNum");
                boardBean.setK1(rs.getString("k.k1"));
                boardBean.setK2(rs.getString("k.k2"));
                boardBean.setBoardWriter(rs.getString("b.boardWriter"));
                boardBean.setBoardTitle(rs.getString("b.boardTitle"));
                boardBean.setBoardContent(rs.getString("b.boardContent"));
                boardBean.setBoardRegTime(rs.getTimestamp("b.boardRegTime"));
                boardBean.setBoardReRef(rs.getInt("b.boardReRef"));
                boardBean.setBoardReLev(rs.getInt("b.boardReLev"));
                boardBean.setBoardReSeq(rs.getInt("b.boardReSeq"));
                boardBean.setBoardReadcount(rs.getInt("b.boardReadcount"));
                boardBean.setFileList(selectFileList(boardNum, k1));
                articleList.add(boardBean);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 미답변 글 용 메서드
	
	public int selectNAListCount(String k1, String k2) {
		int listCount = 0;
		String sql = "";
		
		try {
			if(k2 != null && k2 != "") {
				int kID = get_kID(k1, k2);
				sql = "SELECT count(*) FROM board WHERE kID=? AND boardReLev=0";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, kID);
			} else {
				sql = "SELECT count(*) FROM board WHERE kID IN (Select kID FROM kategorie WHERE k1=?) AND boardReLev=0"; 
						
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k1);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<BoardBean> selectArticleNAList(PageInfo pageInfo) {
		// 게시물 목록 조회 후 리턴
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		
		int page = pageInfo.getPage();
		int limit = pageInfo.getLimit();
		String k1 = pageInfo.getK1();
		String k2 = pageInfo.getK2();
		/* 
		 * 전체 게시물 중 원하는 페이지의 게시물 첫번째 row 번호 설정
		 * - 원본 글 번호(board_re_ref) 기준으로 내림차순 정렬
		 * - 글 순서번호(board_re_seq) 기준으로 오름차순 정렬
		 * - 조회할 게시물 갯수 : 첫번째 게시물 위치 ~ limit 수 만큼
		 *   첫번째 게시물 위치 = (현재페이지 - 1) * 10
		 * 
		 * ex) 현재 페이지(page) 가 1 페이지 일 경우 : 게시물 조회 결과의 0번 행부터 10개 가져오기
		 */
		int startRow = (page - 1) * 10; // 첫번째 게시물 행(row) 번호 계산
		String sql ="";
		int kID = get_kID(k1, k2);
		try {
			// 조회 결과(ResultSet) 객체가 존재할 경우
			// 반복문을 사용하여 1개 게시물 정보(패스워드 제외한 나머지)를 BoardBean 객체에 저장하고
			// BoardBean 객체를 ArrayList<BoardBean> 객체에 저장 반복
			if(k2 != null) {
				sql = "SELECT * FROM board WHERE kID=? AND boardReLev=0 ORDER BY boardReRef DESC, boardReSeq ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, kID);
			} else {
				sql = "SELECT * FROM board WHERE kID IN (SELECT kID FROM kategorie WHERE k1=?) AND boardReLev=0 "
						+ "ORDER BY boardReRef DESC, boardReSeq ASC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k1);
			}
			pstmt.setInt(2, startRow); pstmt.setInt(3, limit);
            rs = pstmt.executeQuery();
            // ResultSet 객체 내의 모든 레코드를 각각 레코드별로 BoardBean 에 담아서 ArrayList 객체에 저장
            // => 패스워드 제외
            while(rs.next()) {
                BoardBean boardBean = new BoardBean();
                boardBean.setBoardNum(rs.getInt("boardNum"));
                boardBean.setBoardWriter(rs.getString("boardWriter"));
                boardBean.setBoardTitle(rs.getString("boardTitle"));
                boardBean.setBoardContent(rs.getString("boardContent"));
                boardBean.setBoardRegTime(rs.getTimestamp("boardRegTime"));
                boardBean.setBoardReRef(rs.getInt("boardReRef"));
                boardBean.setBoardReLev(rs.getInt("boardReLev"));
                boardBean.setBoardReSeq(rs.getInt("boardReSeq"));
                boardBean.setBoardReadcount(rs.getInt("boardReadcount"));
                
                articleList.add(boardBean);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	
	// FAQ 세부 카테고리 목록 가져오기
	public ArrayList<String> getk2List(String k1) {
		ArrayList<String> k2List = new ArrayList<String>();
		
		String sql = "SELECT k2 FROM kategorie WHERE k1=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, k1);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				k2List.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return k2List;
	}
	
	
	



// 

	
	public int insertReply(BoardBean bb) {
		int insertCount = 0;
		
		int kID = get_kID(bb.getK1(), bb.getK2()); //  1:1 문의 답변 용으로 따로 체크해야함
		String sql = "INSERT INTO board(boardNum, kID, boardWriter, boardTitle, boardContent, boardRegTime, boardReRef, boardReLev, boardReSeq, boardReadcount) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getBoardNum()); pstmt.setInt(2, kID); pstmt.setString(3, bb.getBoardWriter());
			pstmt.setString(4, bb.getBoardTitle()); pstmt.setString(5, bb.getBoardContent());
			pstmt.setTimestamp(6, bb.getBoardRegTime()); pstmt.setInt(7, bb.getBoardReRef()); 
			pstmt.setInt(8, bb.getBoardReLev()); pstmt.setInt(9, bb.getBoardReSeq()); 
			pstmt.setInt(10, bb.getBoardReadcount());
			int update = pstmt.executeUpdate();
			if(update != 0 && (bb.getFileList() != null ||  bb.getFileList().size() != 0)) {
				insertCount = insertFile(bb, kID);
			} else {
				insertCount = update;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}


	public ArrayList<BoardBean> selectQ(int boardNum, String k1) {
		BoardBean bb = null;
		String sql = "";
		ArrayList<BoardBean> QDeatails = new ArrayList<BoardBean>();
		
		try {
			sql = "SELECT b.*, k.k1, k.k2 FROM board b JOIN kategorie k ON b.kID=k.kID WHERE b.boardReRef=? AND k.k1=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum); pstmt.setString(2, k1);
			rs = pstmt.executeQuery();
//			System.out.println(rs.next() + "selectQ");
			
			while(rs.next()) {
				bb = new BoardBean();
				System.out.println(rs.getInt("b.boardNum") + " 글번호임");
						bb.setBoardNum(rs.getInt("b.boardNum")); bb.setK1(rs.getString("k.k1")); bb.setK2(rs.getString("k.k2"));
						bb.setBoardWriter(rs.getString("b.boardWriter")); bb.setBoardTitle(rs.getString("b.boardTitle")); bb.setBoardContent(rs.getString("b.boardContent"));
						bb.setBoardRegTime(rs.getTimestamp("b.boardRegTime")); bb.setBoardReRef(rs.getInt("b.boardReRef")); bb.setBoardReLev(rs.getInt("b.boardReLev"));
						bb.setBoardReSeq(rs.getInt("b.boardReSeq")); bb.setBoardReadcount(rs.getInt("b.boardReadcount")); bb.setBookID(rs.getInt("b.bookID"));
				bb.setFileList(selectFileList(rs.getInt("b.boardNum"), k1));
//				System.out.println("rs.next() 동작");
				QDeatails.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return QDeatails;
	}

//	public int updateSeq(String k1, int boardReRef) {
//		int updateSeq = 0;
//		String sql = "UPDATE board SET boardReSeq = 1 WHERE boardNum=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?)";
//		
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, boardReRef); pstmt.setString(2, k1);
//			
//			updateSeq = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return updateSeq;
//	}

	public int updateLev(String k1, int boardReRef) {
		int updateLev = 0;
		String sql = "UPDATE board SET boardReLev = 1 WHERE boardNum=? AND kID IN (SELECT kID FROM kategorie WHERE k1=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardReRef); pstmt.setString(2, k1);
			
			updateLev = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateLev;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////				쿠폰							


	public int getCouponCount() {
	
		int couponCount = 0;
		//WHERE couponEnd_date > now()
		String sql = "SELECT count(*) FROM coupon"; 
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			couponCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return couponCount;
	}
	
	public ArrayList<CouponBean> getCouponList(int startRow, int limit) {
		ArrayList<CouponBean> couponList = new ArrayList<CouponBean>();
		
		String sql = "SELECT * FROM coupon Order By couponEnd_date desc limit ?, ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow); pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CouponBean coupon = new CouponBean(
					rs.getInt("cID"),
					rs.getString("coupon_name"),
					rs.getTimestamp("couponReg_date"),
					rs.getTimestamp("couponEnd_date"),
					rs.getInt("volume")
				);
				couponList.add(coupon);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		close(rs); close(pstmt);
		}
		
		return couponList;
	}
	
	public ArrayList<CouponBean> getCouponList() {
		ArrayList<CouponBean> couponList = new ArrayList<CouponBean>();
		//WHERE couponEnd_date>now() 
		String sql = "SELECT * FROM coupon Order By couponEnd_date desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CouponBean coupon = new CouponBean(
				rs.getInt("cID"),
				rs.getString("coupon_name"),
				rs.getTimestamp("couponReg_date"),
				rs.getTimestamp("couponEnd_date"),
				rs.getInt("volume")
			);
			couponList.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return couponList;
	}
	
	public CouponBean getCouponInfo(int cID) {
		CouponBean coupon = null;
		String sql = "SELECT * FROM coupon WHERE cID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					coupon = new CouponBean(
					rs.getInt("cID"),
					rs.getString("coupon_name"),
					rs.getTimestamp("couponReg_date"),
					rs.getTimestamp("couponEnd_date"),
					rs.getInt("volume")
				);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		close(rs); close(pstmt);
		}
		
		return coupon;
	}
	
	public int insertCoupon(CouponBean coupon) {
	
		int insertCount = 0;
		String sql = "INSERT INTO coupon(cID, coupon_name, couponReg_date, couponEnd_date, volume) VALUES(null, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, coupon.getCoupon_name()); pstmt.setTimestamp(2, coupon.getCouponReg_date()); 
			pstmt.setTimestamp(3, coupon.getCouponEnd_date()); pstmt.setInt(4, coupon.getVolume());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public int updateCoupon(CouponBean coupon) {
	
		int updateCount = 0;
		String sql = "UPDATE coupon SET couponEnd_date=?, volume=? WHERE cID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, coupon.getCouponEnd_date()); pstmt.setInt(2, coupon.getVolume());
			pstmt.setInt(3, coupon.getcID());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	public int deleteCoupon(int cID) {
	
		int deleteCount = 0;
		String sql = "DELETE FROM couponHistory WHERE cID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cID);
			
			pstmt.executeUpdate();
			
			sql = "DELETE FROM coupon WHERE cID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cID);
			
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	// 메인에 뿌려줄 이벤트 배너
	public ArrayList<BoardBean> selectBoardList(int kID, int page, int limit) {
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		String sql = "SELECT * FROM board"
				+ " WHERE kID=? AND boardReLev=? AND boardReSeq=?"
				+ " ORDER BY boardReRef desc LIMIT ?,?";
		String k1="이벤트";
		int startRow = (page - 1) * limit;
        try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kID);
			pstmt.setInt(2, 0);	// 문의글만 보기
			pstmt.setInt(3, 0); // 답변 안된 글만 보기
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoardNum(rs.getInt("boardNum"));		 
				board.setFileList(selectFileList(rs.getInt("boardNum"), k1));
				
				articleList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            if(rs != null) {close(rs);}
            if(pstmt != null) {close(pstmt);}
        }
		
		return articleList;
	}


//// DAO 의 끝
}