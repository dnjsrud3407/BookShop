package admin.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import admin.board.svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import vo.CouponBean;
import vo.PageInfo;
import static access.Access.*;
public class EventWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		BoardService boardService = new BoardService();
		
		List<CouponBean> couponList = boardService.getCouponList();
		request.setAttribute("couponList", couponList);
		
		forward = new ActionForward();
		// 이벤트로 고칠것
		forward.setPath("/admin/board/EventWriteForm.jsp");
		
		return forward;
	}

}
