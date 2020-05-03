package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 현재 페이지 번호 및 한 페이지당 게시글 수
		int page = 1;
		int limit = 10;
		
		String stringPage = request.getParameter("page");
		
		// 파라미터에 page값이 없는 경우
		if(stringPage == null || stringPage.trim().equals("null")) {
			page = 1;
		} else {
			page = Integer.parseInt(stringPage);
		}
		
		int kID = 103;
		QnReListService reviewListService = new QnReListService();
		ArrayList<BoardBean> reviewList = reviewListService.getList(kID, page, limit);
		
		// 상품 문의 게시글 개수 가져오기
		int listCount = reviewListService.getListCount(103);
		// 1. 총 페이지 수 계산
		int maxPage = listCount / limit + (listCount % limit == 0 ? 0 : 1);
		// 페이징 사이즈
		int pageBlock = 10;
		// 2. 시작 페이지 번호
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		// 3. 마지막 페이지 번호
		int endPage = startPage + pageBlock - 1;
		
		// 마지막 페이지 번호가 총 페이지 수보다 클 경우
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, pageBlock, listCount);
		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("reviewList", reviewList);
		
		forward = new ActionForward();
		forward.setPath("./admin/book/reviewList.jsp");
		
		return forward;
	}

}
