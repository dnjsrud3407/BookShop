package admin.board.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.CouponBean;
import static access.Access.*;
public class CouponModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		// 관리자 체크
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		int cID = Integer.parseInt(request.getParameter("cID"));
		int volume = Integer.parseInt(request.getParameter("mod_volume"));
		String sEndDate = request.getParameter("mod_End_date");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date endDate = dateFormat.parse(sEndDate);
		
		Timestamp couponEnd_date = new Timestamp(endDate.getYear(), endDate.getMonth(), endDate.getDay(), 0, 0, 0, 0);
		
		CouponBean coupon = new CouponBean();
		coupon.setcID(cID); coupon.setCouponEnd_date(couponEnd_date); coupon.setVolume(volume);
		
		BoardService boardService = new BoardService();
		
		int updateCount = boardService.updateCoupon(coupon);

		forward = new ActionForward();
		
		if(updateCount != 0) {
			forward.setPath("./Coupon.adb");
			forward.setRedirect(true);
		} else {
			session.setAttribute("ErrorMSG", "쿠폰 수정에 실패하였습니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
