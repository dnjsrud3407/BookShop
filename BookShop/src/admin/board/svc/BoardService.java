package admin.board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

//import org.apache.catalina.startup.SetContextPropertiesRule;

import dao.AdminBoardDAO;
import vo.BoardBean;
import vo.CouponBean;
import vo.PageInfo;

public class BoardService {

	// 해당 대분류 내의 가장 큰 글 번호를 가져오는 메서드
	public int getMaxNum(String k1) {
		System.out.println("BoardService의 getMaxNum() 메서드");
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		int maxNum = boardDAO.getMaxNum(k1);	// k1 카테고리의 글 중 최신글의 글 번호(가장 큼)를 가져옮
		// DB 연결 종료
		close(con);
		return maxNum; // 가져온 최신글의 글 번호 반환
	}

	// 글을 작성하는 메서드
	public int writeArticle(BoardBean bb) {
		System.out.println("BoardService의 writeArticle() 메서드");
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		// 글이 작성되었는가 여부는 int로 리턴받게 된다. 0이면 실패
		int insertCount = boardDAO.insertArticle(bb);
		
		if(insertCount != 0) {
			commit(con);	// 삽입된 글 정보 적용(커밋)
		} else {
			rollback(con);
		}
		// DB 연결 종료
		close(con);
		
		return insertCount; // 가져온 최신글의 글 번호 반환
	}

	// 글 정보를 가져오는 메서드
	public BoardBean getArticle(int boardNum, String k1) {
		System.out.println("BoardService의 getArticle() 메서드");
		// 객체 반환을 위한 BoardBean 객체 선언
		BoardBean bb = null;
		//
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		bb = boardDAO.selectArticle(boardNum, k1);
		// DB 연결 종료
		close(con);
		
		return bb;
	}

	public int deleteArticle(int boardNum, String k1) {
		System.out.println("BoardService의 deleteArticle() 메서드");
		
		Connection con = null;
		
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		con = getConnection();
		
		boardDAO.setConnection(con);
		
		int deleteCount = 0;
		
		deleteCount = boardDAO.deleteArticle(boardNum, k1);
		if(deleteCount != 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return deleteCount;
	}

	public int modifyArticle(BoardBean bb, List<String> deleteFileName) {
		System.out.println("BoardService의 modifyArticle() 메서드");

		Connection con = null;
		
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		con = getConnection();
		
		int updateCount = 0;
		
		boardDAO.setConnection(con);
		
		updateCount = boardDAO.updateArticle(bb, deleteFileName);
		
		if(updateCount != 0) {
			System.out.println("수정성공");
			commit(con);
		} else {
			System.out.println("수정 실패");
			rollback(con);
		}
		close(con);
		return updateCount;
	}

	
	public int getListCount(String k1, String k2) {
		int listCount = 0;
		
		// 1. Connection 객체 가져오기
		Connection con = getConnection(); // static import 가 아니라면  jdbcUtill.으로 호출(스태틱 메서드 호출 시)
				
		// 2. DAO 객체 가져오기(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
				
		// 3. DAO 객체에 Connection 객체 전달하기
		boardDAO.setConnection(con);
		
		// 4. DAO 객체의 getListCount() 메서드 실행
		listCount = boardDAO.selectListCount(k1, k2);
		
		// 5. Connection 객체 반환하기
		close(con);
		
		return listCount;
	}
	
	

	public ArrayList<BoardBean> getArticleList(PageInfo pageInfo) {
		ArrayList<BoardBean> articleList = null;
		// 1. Connection 객체 가져오기
		Connection con = getConnection(); // static import 가 아니라면  jdbcUtill.으로 호출(스태틱 메서드 호출 시)
						
		// 2. DAO 객체 가져오기(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
						
		// 3. DAO 객체에 Connection 객체 전달하기
		boardDAO.setConnection(con);
				
		// 4. DAO 객체의 selectArticleList() 메서드 실행
		articleList = boardDAO.selectArticleList(pageInfo);
		
		// 5. Connection 객체 반환하기
		close(con);
		return articleList;
	}

	
	
	// 미답변 글 갯수 가져오기
	public int getNAListCount(String k1, String k2) {
		int listCount = 0;
		
		// 1. Connection 객체 가져오기
		Connection con = getConnection(); // static import 가 아니라면  jdbcUtill.으로 호출(스태틱 메서드 호출 시)
				
		// 2. DAO 객체 가져오기(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
				
		// 3. DAO 객체에 Connection 객체 전달하기
		boardDAO.setConnection(con);
		
		// 4. DAO 객체의 getListCount() 메서드 실행
		listCount = boardDAO.selectNAListCount(k1, k2);
		
		// 5. Connection 객체 반환하기
		close(con);
		
		return listCount;
	}

	public ArrayList<BoardBean> getArticleNAList(PageInfo pageInfo) {
		ArrayList<BoardBean> articleList = null;
		// 1. Connection 객체 가져오기
		Connection con = getConnection(); // static import 가 아니라면  jdbcUtill.으로 호출(스태틱 메서드 호출 시)
						
		// 2. DAO 객체 가져오기(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
						
		// 3. DAO 객체에 Connection 객체 전달하기
		boardDAO.setConnection(con);
				
		// 4. DAO 객체의 selectArticleList() 메서드 실행
		articleList = boardDAO.selectArticleNAList(pageInfo);
		
		// 5. Connection 객체 반환하기
		close(con);
		return articleList;
	}

	public ArrayList<String> getk2List(String k1) {
		ArrayList<String> k2List = null;
		// 1. Connection 객체 가져오기
		Connection con = getConnection(); // static import 가 아니라면  jdbcUtill.으로 호출(스태틱 메서드 호출 시)
						
		// 2. DAO 객체 가져오기(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
						
		// 3. DAO 객체에 Connection 객체 전달하기
		boardDAO.setConnection(con);
				
		// 4. DAO 객체의 selectArticleList() 메서드 실행
		k2List = boardDAO.getk2List(k1);
		
		// 5. Connection 객체 반환하기
		close(con);
		return k2List;
	}

	

	
	// 1:1 문의용
	public ArrayList<BoardBean> getQ(int boardNum, String k1) {
		System.out.println("BoardService의 getQ() 메서드");
		// 객체 반환을 위한 BoardBean 객체 선언
		BoardBean bb = null;
		//
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		ArrayList<BoardBean> QDeatails = boardDAO.selectQ(boardNum, k1);
		// DB 연결 종료
		close(con);
		
		return QDeatails;
	}
	
	public int writeReply(BoardBean bb) {
		System.out.println("BoardService의 writeArticle() 메서드");
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		// 글이 작성되었는가 여부는 int로 리턴받게 된다. 0이면 실패
		int insertCount = boardDAO.insertArticle(bb);
		
		if(insertCount != 0) {
			int updateSeq = boardDAO.updateLev(bb.getK1(), bb.getBoardReRef());
			if(updateSeq != 0) {
				commit(con);	// 삽입된 글 정보 적용(커밋)
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		// DB 연결 종료
		close(con);
		
		return insertCount; // 가져온 최신글의 글 번호 반환
	}

	public int getCouponCount() {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		
		int couponCount = boardDAO.getCouponCount();
		
		// DB 연결 종료
		close(con);
		
		return couponCount;
	}

	public ArrayList<CouponBean> getCouponList(int startRow, int limit) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		
		ArrayList<CouponBean> couponList = boardDAO.getCouponList(startRow,limit);
		
		// DB 연결 종료
		close(con);
		
		return couponList;
	}

	public List<CouponBean> getCouponList() {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		
		ArrayList<CouponBean> couponList = boardDAO.getCouponList();
		
		// DB 연결 종료
		close(con);
		
		return couponList;
	}

	public CouponBean getCouponInfo(int cID) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		
		CouponBean coupon = boardDAO.getCouponInfo(cID);
		
		// DB 연결 종료
		close(con);
		
		return coupon;
	}

	public int insertCoupon(CouponBean coupon) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		int insertCount= 0;
		
		insertCount = boardDAO.insertCoupon(coupon);
		
		if(insertCount != 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		// DB 연결 종료
		close(con);
		
		return insertCount;
	}

	public int updateCoupon(CouponBean coupon) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		int updateCount= 0;
		
		updateCount = boardDAO.updateCoupon(coupon);
		
		if(updateCount != 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		// DB 연결 종료
		close(con);
		
		return updateCount;
	}

	public int deleteCoupon(int cID) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		// DB 작업
		int deleteCount= 0;
		
		deleteCount = boardDAO.deleteCoupon(cID);
		
		if(deleteCount != 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		// DB 연결 종료
		close(con);
		
		return deleteCount;
	}

	// 메인에 뿌려줄 이벤트 배너
	public ArrayList<BoardBean> getArticleList(int kID, int page, int limit) {
		Connection con = null;
		// BoardDAO 객체 생성(싱글톤 패턴)
		AdminBoardDAO boardDAO = AdminBoardDAO.getInstance();
		// DB 연결
		con = getConnection();
		boardDAO.setConnection(con);
		
		ArrayList<BoardBean> articleList = boardDAO.selectBoardList(kID, page, limit);
		
		close(con);
		
		return articleList;
	}

	
	
}
