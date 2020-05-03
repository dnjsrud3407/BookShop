package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.NoticeListService;
import vo.ActionForward;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("Notice 문의 내역 보기");
//		이벤트
		
		NoticeListService notice_ListService = new NoticeListService();
		ArrayList list = notice_ListService.getList();
		
		forward = new ActionForward();
		forward.setPath("board/EventBoard.jsp");
		
		return forward;
	}

}
