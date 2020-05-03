package member.book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.ListService;
import member.book.svc.BookListService;
import member.book.svc.BookSearchListService;
import vo.ActionForward;
import vo.BookBean;
import vo.PageInfo;

public class BookSearchListAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		// 검색한 책 이름,페이지 번호 가져오기
		String bookTitle = request.getParameter("bookTitle");
		
		if(bookTitle != null && bookTitle != "") {
			int page = 1;
			//page 파라미터가 존재할 경우 파라미터에 전달된 데이터를 현재 페이지번호로 대체
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page")); //정수로 변환하여 저장
			}
			
			int limit = 5;
			
			//svc에 만들기
			BookSearchListService bookSearchListService = new BookSearchListService();
		
			// 책 목록 가져오기
			ArrayList<BookBean> bookSearchList = bookSearchListService.getBookSearchList(page, limit, bookTitle);
			
			// 책 전체 개수
			int listCount = bookSearchListService.getSearchListCountMem(bookTitle);
			
			// 1. 총 페이지 수 계산
			int maxPage = listCount / limit + (listCount % limit == 0 ? 0 : 1);
			// 페이징 사이즈(한 화면 페이지 번호 개수)
			int pageBlock = 6;
			// 2. 시작 페이지 번호
			int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
			// 3. 마지막 페이지 번호
			int endPage = startPage + pageBlock - 1;
			
			// 마지막 페이지 번호가 총 페이지 수보다 클 경우
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, pageBlock, listCount);
			
			request.setAttribute("listCount", listCount);
			request.setAttribute("bookTitle", bookTitle);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bookSearchList", bookSearchList);
			
			forward = new ActionForward();
			forward.setPath("/book/book_search.jsp");
			
		} else {
			// 검색어가 없을 때
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<script>");        
            out.println("alert('검색어를 입력하세요')");
            // 이전 페이지로 돌아가기
            out.println("history.back()");       
            out.println("</script>");
		}
		
		
		return forward;

	}

}
