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

public class CouponDeleteAction implements Action {

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
		
		BoardService boardService = new BoardService();
		
		int deleteCount = boardService.deleteCoupon(cID)
				;
		forward = new ActionForward();
		
		if(deleteCount != 0) {
			forward.setPath("./Coupon.adb");
			forward.setRedirect(true);
		} else {
			session.setAttribute("ErrorMSG", "쿠폰 삭제에 실패하였습니다.");
			forward.setPath("failed.adb");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
