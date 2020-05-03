package board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.Action;
import board.svc.QDetailService;
import board.svc.QListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class QListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("QListAction");
		QListService q_ListService = new QListService();
		HttpSession session=request.getSession();
		String uID=(String) session.getAttribute("uID");
		
		int page = 1; // 현재 페이지 번호
		int limit = 10; // 한 페이지 당 출력할 게시물 수
	
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // 정수로 변환하여 저장
		}
		if (page <0) {
			page=1;
		}
	
		int listCount=q_ListService.getOneonOneQListCount(uID);
		System.out.println("1 리스트카운트"+listCount);

		int maxPage = (int)((double)listCount / limit + 0.95);
		System.out.println("2 maxPAge"+maxPage);
		
		// 2. 시작 페이지 번호 계산
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		System.out.println("3 startPage"+startPage);
		// 3. 마지막 페이지 번호 계산
		int endPage = startPage + 10 - 1;
		System.out.println("4 endPage"+endPage);
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println("4 endPage강제설정"+endPage);
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		int startRow=((page-1)*limit)+1;
		
		
		
		System.out.println("시작페이지"+pageInfo.getStartPage());
		System.out.println("끝페이지"+pageInfo.getEndPage());
		System.out.println("멕스페이지"+pageInfo.getMaxPage());
		
		
		//-----------1:1문의내역 불러오기----------

		
		List<BoardBean> QList =q_ListService.getList(uID,startRow,limit);
			
		
			request.setAttribute("QList",QList);
			request.setAttribute("pageInfo", pageInfo);
		
		
		
		
		
		forward = new ActionForward();
		forward.setPath("board/QList.jsp");
		return forward;
	}

}
