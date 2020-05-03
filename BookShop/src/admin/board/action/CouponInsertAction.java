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
public class CouponInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		// 관리자 체크
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		String coupon_name = request.getParameter("coupon_name");
		int volume = Integer.parseInt(request.getParameter("volume"));
//		String time = " 00:00:00";
		String sRegDate = request.getParameter("couponReg_date");
		String sEndDate = request.getParameter("couponEnd_date");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date regDate = dateFormat.parse(sRegDate);
		Date endDate = dateFormat.parse(sEndDate);
		
		Timestamp couponReg_date = new Timestamp(regDate.getYear(), regDate.getMonth(), regDate.getDay(), 0, 0, 0, 0);
		Timestamp couponEnd_date = new Timestamp(endDate.getYear(), endDate.getMonth(), endDate.getDay(), 0, 0, 0, 0);
		
		CouponBean coupon = new CouponBean(coupon_name, couponReg_date, couponEnd_date, volume);
		
		BoardService boardService = new BoardService();
		
		int insertCount = boardService.insertCoupon(coupon);

		forward = new ActionForward();
		
		if(insertCount != 0) {
			forward.setPath("./Coupon.adb");
			forward.setRedirect(true);
		} else {
			session.setAttribute("ErrorMSG", "쿠폰 생성에 실패하였습니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
