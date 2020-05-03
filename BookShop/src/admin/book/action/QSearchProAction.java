package admin.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.QnReSearchService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class QSearchProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 검색날짜(전, 후), 답변여부 파라미터 들고오기
		String boardRegTime_Before = request.getParameter("boardRegTime_Before");
		String boardRegTime_After = request.getParameter("boardRegTime_After");
		String answer = request.getParameter("answer");
		
		// 답변 여부 sql 만들기 (all일경우는 조건문 생성 X)
		String searchSql = "";
		if(answer.equals("false")) {
			searchSql = " and boardReSeq=0";
		} else if(answer.equals("true")) {
			searchSql = " and boardReSeq=1";
		}

		// ================= 페이징 =================
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
    	
    	int kID = 102;
    	
		QnReSearchService qSearchService = new QnReSearchService();
		// 게시글 개수
		int listCount = qSearchService.getSearchListCount(kID, boardRegTime_Before, boardRegTime_After, searchSql);
		ArrayList<BoardBean> qSearchList = null;
		// === boardList 가져오기
		qSearchList = qSearchService.getSearchBoardList(kID, boardRegTime_Before, boardRegTime_After, searchSql, page, limit);
		
		// 1. 총 페이지 수 계산
    	int maxPage = listCount / limit + (listCount % limit == 0 ? 0 : 1);
    	// 페이징 사이즈
    	int pageBlock = 5;
    	// 2. 시작 페이지 번호
    	int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
    	// 3. 마지막 페이지 번호
    	int endPage = startPage + pageBlock - 1;
    	
    	// 마지막 페이지 번호가 총 페이지 수보다 클 경우
    	if(endPage > maxPage) {
    		endPage = maxPage;
    	}
    	
    	PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, pageBlock, listCount);
    	
    	request.setAttribute("boardRegTime_Before", boardRegTime_Before);
    	request.setAttribute("boardRegTime_After", boardRegTime_After);
    	request.setAttribute("answer", answer);
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("qSearchList", qSearchList);
    	
		forward = new ActionForward();
		forward.setPath("./admin/book/qSearchList.jsp");
		return forward;
	}

}


















