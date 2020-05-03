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

public class BookListAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int page = 1;
		// 페이지 타입 지정
		String type = request.getParameter("type");
		int limit = 6;
		int bk2 = 0;
		int bkID = 0;
		String bk2Str = "";
		BookBean book = null;
		int listCount = 0;
		ArrayList<BookBean> bookList = null;
		BookListService bookListService = new BookListService();
		ArrayList<BookBean> bookKategorie = null;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); //정수로 변환하여 저장
		}
			

		if(request.getParameter("bk2") != null) {
			bk2 = Integer.parseInt(request.getParameter("bk2"));
			if(bk2 == 1) {
				bk2Str = "1단계";
			} else if(bk2 == 2) {
				bk2Str = "2단계";
			} else {
				bk2Str = "3단계";
			}
			
			// 단계>과목별 세부 항목이라면
			if(request.getParameter("BKID") != null) {
				bkID = Integer.parseInt(request.getParameter("BKID"));
				bookList = bookListService.userbkIDBookList(page, limit, bkID);
				listCount = bookListService.userbkIDListCount(bkID);
				// 대표 카테고리 들고가는 book
				book = new BookBean(bkID, bookList.get(0).getBK1(), bookList.get(0).getBK2(), bookList.get(0).getBK3());
			} else {	// 단계별이라면
				bookList = bookListService.userbk2BookList(page, limit, bk2Str);
				listCount = bookListService.userbk2ListCount(bk2Str);
				book = new BookBean(bkID, bookList.get(0).getBK2());
			}
		} 
		// option에 들어갈 카테고리
		bookKategorie = bookListService.getKategorie(bk2Str);
		
		
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
		
		request.setAttribute("bk2", bk2);
		request.setAttribute("book", book);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookKategorie", bookKategorie);

		
		forward = new ActionForward();
		if (type.equals("L")) {	// 리스트형
			forward.setPath("/book/book_list_l.jsp");
		} else {				// 이미지형
			forward.setPath("/book/book_list_i.jsp");
		}
		return forward;

	}

}
