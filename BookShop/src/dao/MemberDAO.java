package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static db.JdbcUtil.*;

import vo.MemberBean;

public class MemberDAO {
public MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() { 
		return instance;
	}
	// ----------------------------------------------------
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public static MemberBean selectreviews(int num) {
		MemberBean member = null;
		
		return member;
	}

	public boolean isArticleWriter(int num, String parameter) {
		// TODO Auto-generated method stub
		return false;
	}

	public int insertMember(MemberBean member) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {

			String sql="insert into user(uID,pw,u_name,address_post,address_1,address_2,phone_num,email,point,grade,joinDate,withdrawal) "
					+ "values(?,?,?,?,?,?,?,?,?,?,now(),?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getuID());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getU_name());
			pstmt.setString(4, member.getAddress_post());
			pstmt.setString(5, member.getAddress_1());
			pstmt.setString(6, member.getAddress_2());
			pstmt.setString(7, member.getPhone_num());
			pstmt.setString(8, member.getEmail());
			pstmt.setInt(9, member.getPoint());
			pstmt.setInt(10,member.getGrade());
			pstmt.setString(11,member.getWithdrawal());
			insertCount = pstmt.executeUpdate();
			System.out.println(insertCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	// 회원가입 판별 메서드
	public boolean idCheck(String uID) {
		boolean idCheck = true;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 // 중복된 아이디(회원탈퇴 포함)	
				
			} else {
				idCheck = false; // 아이디 사용가능
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return idCheck;
	}
	
	
	// 로그인 판별
	public int isLogin(MemberBean member) {
		int loginResult = -2;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user where uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getuID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("withdrawal").equalsIgnoreCase("y")) {
					loginResult = -1; // 회원탈퇴
					return loginResult;
				} else if(rs.getString("pw").equals(member.getPw())){
					loginResult = 1; // 로그인 성공
				} else { 
					loginResult = 0; // 패스워드 틀림
				}
			} else {
				loginResult = -2; // 아이디 없음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginResult;
	}
	
	
	
	public int selectMember(MemberBean member) {
		int loginResult = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from user where uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getuID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("withdrawal").equalsIgnoreCase("y")) {
					loginResult = -1;
					return loginResult;
				}
				if(rs.getString("pw").equals(member.getPw())) {
					loginResult = 1;
					
				} else { 
					loginResult = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginResult;
	}

	public int selectMemberList() {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public List<MemberBean> getMemberList() {
		List<MemberBean> memberList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setuID(rs.getString("uID"));
				mb.setU_name(rs.getString("u_name"));
				mb.setAddress_post(rs.getString("address_post"));
				mb.setAddress_1(rs.getString("address_1"));
				mb.setAddress_2(rs.getString("address_2"));
				mb.setPhone_num(rs.getString("phone_num"));
				mb.setEmail(rs.getString("email"));
				mb.setPoint(rs.getInt("point"));
				mb.setGrade(rs.getInt("grade"));
				memberList.add(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);			
		}
		
		return memberList;
	}

	public MemberBean selectMember(String members) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberBean member = null;
		
		try {
			String sql = "select * from user where uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, members);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				member = new MemberBean();
				member.setuID(rs.getString("uID"));
				member.setU_name(rs.getString("u_name"));
				member.setAddress_post(rs.getString("address_post"));
				member.setAddress_1(rs.getString("address_1"));
				member.setAddress_2(rs.getString("address_2"));
				member.setEmail(rs.getString("email"));
				member.setTell_num(rs.getString("tell_num"));
				member.setPhone_num(rs.getString("phone_num"));
				member.setAddress2(rs.getString("address2"));
				member.setPoint(rs.getInt("point"));
				member.setGrade(rs.getInt("grade"));
				member.setJoinDate(rs.getDate("joinDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	/* 관리자아이디체크 */
//	public boolean isAdminWriter(String uID, String pw) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		boolean isAdminWriter = false;
//		
//		try {
//			String sql = "SELECT pw FROM user WHERE uID=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, uID);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) { // 조회 결과가 있을 경우 패스워드가 일치하는 게시물이므로 true 설정
//				isAdminWriter = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		return isAdminWriter;
//	}
	/* 멤버수정 - 포인트와 그레이드 */
	public int updateMember(MemberBean member) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;

		try {
//			String sql = "UPDATE board SET board_name=?,board_subject=?,board_content=? WHERE board_num=?";
			String sql = "UPDATE user SET point=?, grade=? WHERE uID=?";
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, article.getBoard_name());
			pstmt.setInt(1, member.getPoint());
			pstmt.setInt(2, member.getGrade());
			pstmt.setString(3, member.getuID());

			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteMember(String uID) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM user WHERE uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

//	public ArrayList<MemberBean> selectMemberList(int page, int limit) {
//		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		int startRow = (page - 1) * 10;
//		 
//		try { 
//			String sql = "SELECT * FROM user ORDER BY uID DESC lIMIT ?,?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, limit);
//			
//			while(rs.next()) {
//				MemberBean memberBean = new MemberBean();
//				memberBean.setuID(rs.getString("uID"));
//				memberBean.setU_name(rs.getString("u_name"));
//				memberBean.setAddress(rs.getString("address"));
//				memberBean.setAddress2(rs.getString("address2"));
//				memberBean.setPoint(rs.getInt("point"));
//				memberBean.setGrade(rs.getInt("grade"));
//				
//				memberList.add(memberBean);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		return memberList;
//	}
	
	//회원정보 수정&정보 보기
	public MemberBean getMemberInfo(String uID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean=null;
		
		try {
			
			String sql = "select * from user where uID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,uID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				memberBean=new MemberBean(
						rs.getString("uID"),
						rs.getString("pw"),
						rs.getString("u_name"),
						rs.getString("address_post"),
						rs.getString("address_1"),
						rs.getString("address_2"),
						rs.getString("phone_num"),
						rs.getString("u_name2"),
						rs.getString("address2_post"),
						rs.getString("address2_1"),
						rs.getString("address2_2"),
						rs.getString("phone_num2"),						
						rs.getString("email"),
						rs.getInt("point"),
						rs.getInt("grade"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberBean;
	}

	
	
	
	
	//회원정보수정
	public int memberInfoModify(MemberBean memberBean) {
		System.out.println("memberDAO.memberInfoModify(MemberBean memberBean)");
		PreparedStatement pstmt = null;
		int modifySuccess=0;
		
		try {
			
			String sql = "update user set uID=?,pw=?,u_name=?,address_post=?,address_1=?,address_2=?,phone_num=?,email=? where uID=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,memberBean.getuID());
			pstmt.setString(2,memberBean.getPw());
			pstmt.setString(3,memberBean.getU_name());
			pstmt.setString(4,memberBean.getAddress_post());
			pstmt.setString(5,memberBean.getAddress_1());
			pstmt.setString(6,memberBean.getAddress_2());
			pstmt.setString(7,memberBean.getPhone_num());
			pstmt.setString(8,memberBean.getEmail());
			pstmt.setString(9,memberBean.getuID());
			
			modifySuccess=pstmt.executeUpdate();
			
		  System.out.println(modifySuccess);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifySuccess;
		
	}

	//회원탈퇴시 비밀번호체크&삭제
	public int checkPass(String uID, String pw) {
		int pwCheck=0;
		System.out.println("memberDAO.checkPass(String uID, String pw)");
		PreparedStatement pstmt = null;
		
		
		try {
		
				String sql="delete from user where uID=? and pw=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,uID);
				pstmt.setString(2,pw);
				pwCheck=pstmt.executeUpdate();
		
			System.out.println(pwCheck);
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return pwCheck;
	}

	
	//회원정보 수정시 비밀번호 체크
	public int checkPass2(String uID, String pw) {
		int pwCheck=0;
		System.out.println("memberDAO.checkPass2(String uID, String pw)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
				String sql="select * from user where uID=? and pw=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,uID);
				pstmt.setString(2,pw);
				rs=pstmt.executeQuery();
		
				if (rs.next()) {
					pwCheck=1;
					System.out.println(pwCheck);
				}else {
					pwCheck=0;
					System.out.println(pwCheck);
				}
				
		
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return pwCheck;
	}

	
	//쿠폰정보조회
	public ArrayList<MemberBean> getCouponInfo(String uID) {
		System.out.println("memberDAO.getCouponInfo(String uID)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;
		ArrayList<MemberBean> couponInfo = null;
		
		try {
			
			String sql="select cp.coupon_name,ch.couponStatus,ch.couponContent,cp.couponReg_date,cp.couponEnd_date,ch.couponAction,cp.volume"
					+ " from couponhistory ch join user user on ch.uID=user.uID join coupon cp on ch.cID=cp.cID"
					+ " where user.uID=?";

			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			rs=pstmt.executeQuery();
			
			couponInfo = new ArrayList<MemberBean>();
			
			while (rs.next()) {
				
				memberBean = new MemberBean(
						rs.getString("coupon_name"),
						rs.getString("couponStatus"),
						rs.getString("couponContent"),
						rs.getDate("couponReg_date"),
						rs.getDate("couponEnd_date"),
						rs.getInt("couponAction"),
						rs.getInt("volume")
						);
				couponInfo.add(memberBean);
			}
		
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			close(pstmt);
//			close(rs);
		}
		
		return couponInfo;
	}

	//포인트 
	public ArrayList<MemberBean> getPointInfo(String uID) {
		System.out.println("memberDAO.getPointInfo(String uID)");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;
		ArrayList<MemberBean> pointInfo = null;
		
		try {
			
			String sql="select * from pointhistory where ownerID=?";
			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			rs=pstmt.executeQuery();
			
			pointInfo = new ArrayList<MemberBean>();
			
			while (rs.next()) {
					
			memberBean = new MemberBean(
					rs.getInt("pID"),
					rs.getString("ownerID"),
					rs.getDate("pointRegTime"),
					rs.getString("pointContent"),
					rs.getInt("pointValue"),
					rs.getInt("pointAction")
				
					);
			
			pointInfo.add(memberBean);
	
			}//while문
			
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return pointInfo;
		
	}
	
	//포인트 검색
	public ArrayList<MemberBean> getPointInfo(String uID,String startDate, String endDate) {
		System.out.println("memberDAO.getPointInfo(String uID,String startDate, String endDate)");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;
		ArrayList<MemberBean> pointInfo = null;
		
		try {
			String sql="select * from pointhistory where ownerID=? and pointRegTime >=? and pointRegTime <= date_add(?,interval 1 day)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			pstmt.setString(2,startDate);
			pstmt.setString(3,endDate);
			rs=pstmt.executeQuery();
			
			
			System.out.println("포인트날짜검색 DAO    : "+startDate+","+endDate);
			
			pointInfo = new ArrayList<MemberBean>();
			while (rs.next()) {
			memberBean = new MemberBean(
					rs.getInt("pID"),
					rs.getString("ownerID"),
					rs.getDate("pointRegTime"),
					rs.getString("pointContent"),
					rs.getInt("pointValue"),
					rs.getInt("pointAction")
				
					);
			pointInfo.add(memberBean);
			
			}//while문
			
			for (MemberBean memberBean2 : pointInfo) {
				System.out.println(memberBean2.getPointContent());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return pointInfo;
		
	}
	
	
	//쿠폰페이징을 위한 전체 카운트
	public int getCouponInfoListCount(String uID) {
		int count=0;
		System.out.println("쿠폰총개수가져오기)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql="select cp.coupon_name,ch.couponStatus,cp.couponReg_date,cp.couponEnd_date"
					+ " from couponhistory ch join user user on ch.uID=user.uID join coupon cp on ch.cID=cp.cID"
					+ " where user.uID=?";

			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				
							count+=1;
			}
		
			 System.out.println("멤버디에이오 쿠폰 카운트 ; "+count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			close(pstmt);
//			close(rs);
		}
		
		return count;
	}
	
	
	//쿠폰날짜검색
	public ArrayList<MemberBean> getCouponInfo(String uID, String startDate2, String endDate2) {
		System.out.println("memberDAO.getCouponInfo(String uID)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;
		ArrayList<MemberBean> couponInfo = null;
		
		
		try {
			
			String sql="select cp.coupon_name,ch.couponStatus,ch.couponContent,cp.couponReg_date,cp.couponEnd_date,ch.couponAction,cp.volume"
					+ " from couponhistory ch join user user on ch.uID=user.uID join coupon cp on ch.cID=cp.cID"
					+ " where user.uID=? and couponReg_date >=? and couponReg_date <= date_add(?,interval 1 day)";

			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			pstmt.setString(2,startDate2);
			pstmt.setString(3,endDate2);
			rs=pstmt.executeQuery();
			
			couponInfo = new ArrayList<MemberBean>();
			
			while (rs.next()) {
				
				memberBean = new MemberBean(
						rs.getString("coupon_name"),
						rs.getString("couponStatus"),
						rs.getString("couponContent"),
						rs.getDate("couponReg_date"),
						rs.getDate("couponEnd_date"),
						rs.getInt("couponAction"),
						rs.getInt("volume")
						
						);
				System.out.println(	memberBean.getCoupon_name());
				
				couponInfo.add(memberBean);

			}
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return couponInfo;
	}
	
	
	
	

	// ===================== 주문할 때 사용하는 메서드
	// 사용 가능한 쿠폰 리스트 불러온다
	public ArrayList<MemberBean> getCouponList(String uID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;
		ArrayList<MemberBean> couponInfo = null;
		
		try {
			String sql="SELECT ch.num, cp.cID, cp.coupon_name, cp.volume"
					+ " FROM couponhistory ch JOIN user user ON ch.uID=user.uID"
					+ " JOIN coupon cp ON ch.cID=cp.cID"
					+ " WHERE user.uID=? AND cp.couponEnd_date > now() AND ch.couponAction=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,uID);
			pstmt.setInt(2, 0);		// 사용가능한 쿠폰만 조회 
			rs=pstmt.executeQuery();
			
			couponInfo = new ArrayList<MemberBean>();
			
			while (rs.next()) {
				memberBean = new MemberBean(
						rs.getInt("num"),
						rs.getInt("cID"),
						rs.getString("coupon_name"),
						rs.getInt("volume"));

				couponInfo.add(memberBean);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return couponInfo;
	}
	
	// 사용자 포인트 조회해 온다
	public int getPoint(String uID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		
		String sql = "SELECT point FROM user WHERE uID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return point;
	}

	public String findPass(HashMap hash) {
		String findPass = null;
		String uID = (String)hash.get("uID");
		String phone_num = (String)hash.get("phone_num");
		System.out.println("123: "+ uID);
		System.out.println("456: " + phone_num);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select pw from user where uID=? and phone_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			pstmt.setString(2, phone_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findPass = rs.getString("pw");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return findPass;
	}

	public String findId(HashMap hash) {
		String findId = null;
		String u_name = (String)hash.get("u_name");
		String phone_num = (String)hash.get("phone_num");
		System.out.println("123: "+ u_name);
		System.out.println("456: " + phone_num);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select uID from user where u_name=? and phone_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_name);
			pstmt.setString(2, phone_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findId = rs.getString("uID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return findId;
	}

//	public int updateAddress2(MemberBean member) {
//		PreparedStatement pstmt = null;
//		int updateCount = 0;
//		
//		String sql ="update user set u_name2=?, address2_post =? "
//				+ ", address2_1=?, address2_1=?, phone_num2=? where uID=?";
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, member.getU_name2());
//			pstmt.setString(2, member.getAddress2_post());
//			pstmt.setString(3, member.getAddress2_1());
//			pstmt.setString(4, member.getAddress2_2());
//			pstmt.setString(5, member.getPhone_num2());
//			pstmt.setString(6, member.getuID());
//			
//			updateCount = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return updateCount;
//	}
//
}
