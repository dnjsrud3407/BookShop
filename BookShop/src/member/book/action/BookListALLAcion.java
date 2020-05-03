package member.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.book.svc.ListService;
import member.book.svc.BookListService;
import vo.ActionForward;
import vo.BookBean;
import vo.PageInfo;

public class BookListALLAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int page = 1;
		int limit = 6;
		//page 파라미터가 존재할 경우 파라미터에 전달된 데이터를 현재 페이지번호로 대체
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); //정수로 변환하여 저장
		}
		// 페이지 타입 지정
		String type = request.getParameter("type");
		
		BookListService bookListService = new BookListService();
		ArrayList<BookBean>  bookAllList=bookListService.getAllBookList(page, limit);
			
		
		// 책 전체 개수
		int listCount = bookListService.getListCount();
		
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
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("bookAllList", bookAllList);

		
		forward = new ActionForward();
		if (type.equals("L")) {	// 리스트형
			forward.setPath("/book/book_AllList_l.jsp");
		} else {				// 이미지형
			forward.setPath("/book/book_AllList_i.jsp");
		}
		
		return forward;

	}

}	
