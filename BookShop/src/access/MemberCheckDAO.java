package access;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberCheckDAO {

	public static MemberCheckDAO instance = new MemberCheckDAO();
	
	public static MemberCheckDAO getInstance() {
		return instance;
	}
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int getMemberGrade(String uID) {
		int memberGrade = 0;
		
		String sql = "SELECT grade FROM user WHERE uID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberGrade = rs.getInt("grade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(pstmt);
		}
		
		return memberGrade;
	}
	
}
