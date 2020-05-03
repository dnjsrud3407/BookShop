package admin.board.action;

import java.util.ArrayList;

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
public class CouponListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardService boardService = new BoardService();
		// 관리자 체크
		HttpSession session = request.getSession();
		if(!isAdmin(session)) {
			forward = deniedAccess(session);
			return forward;
		}
		// PageInfo 객체 선언
		PageInfo pageInfo = new PageInfo();
		int page = 1;  // 현 페이지 정보
		int limit = 10; // 한 페이지 당 출력할 게시물 수  - 10개
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		pageInfo.setPage(page);	// 들고온 페이지 정보가 없을 때 기본 페이지 1
		
		int startRow = (page - 1) * 10;
		
		int couponCount = boardService.getCouponCount();
		
		ArrayList<CouponBean> couponList = boardService.getCouponList(startRow, limit);
		// 쿠폰목록 리퀘스트 저장
		request.setAttribute("couponList", couponList);
		
		// 페이지 계산
		// 1. 총 페이지 수 계산
		 
		int maxPage = (((double)couponCount / (double)limit ) - (couponCount / limit) > 0 ? couponCount / limit + 1 : couponCount / limit);
		if(maxPage == 0) {
			maxPage = 1;
		}
		// 2. 시작 페이지 번호 계산
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		// 3. 마지막 페이지 번호 계산
		int endPage = startPage + 10 - 1;
		// 마지막 페이지 번호가 총 페이지 수 보다 클 경우 총 페이지 수를 마지막 페이지 번호로 설정
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// pageInfo 객체에 페이지 정보 저장(총 페이지, 시작 페이지, 현재 페이지, 끝 페이지, 총 게시글 수 등)
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage); 
		pageInfo.setPage(page);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(couponCount);
		pageInfo.setPageBlock(10);
		
		request.setAttribute("pageInfo", pageInfo);
		
		
		forward = new ActionForward();
		// 이벤트로 고칠것
		forward.setPath("/admin/board/couponList.jsp");		
		
		return forward;
	}

}
