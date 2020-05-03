package member.book.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import admin.book.svc.DetailService;
import member.book.svc.BookDetailService;
import member.book.svc.QListService;
import member.book.svc.ReviewListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;
import vo.PageInfo;

public class BookDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int listCount = 0;
		// bookID
		int bookID = 0;
		
		if(request.getParameter("bookID") != null) {
			bookID = Integer.parseInt(request.getParameter("bookID"));
		}
		
		// 상품 상세보기//
		BookDetailService bookDetailService = new BookDetailService();
		BookBean book = bookDetailService.getArticle(bookID);

		// 상품문의보기 //
		int page = 1;
		int limit = 10;
		String k1 = "상품문의";

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 상품문의 서비스
		QListService qListService = new QListService();
		
		// 상품후기 서비스
		ReviewListService reviewListSerivce = new ReviewListService();
		
		// 상품문의 페이징
		PageInfo pageInfoQna = new PageInfo();
		pageInfoQna.setPage(page);
		pageInfoQna.setLimit(limit);
		pageInfoQna.setK1(k1);
		// 상품후기 페이징
		PageInfo pageInfoReview = new PageInfo();
		pageInfoReview.setPage(page);
		pageInfoReview.setLimit(limit);	
		
		ArrayList<BoardBean> articleQnaList = null;
		ArrayList<BoardBean> articleReviewList = null;

		
		// 상품문의 글 들고 온 다음 상품 후기 글 들고옴
// ---------------------------- 상품문의 --------------------------------------------------------		
		if(pageInfoQna.getK1().equals("상품문의")) {
			articleQnaList = qListService.getQnaBoard(pageInfoQna, bookID);
			listCount = qListService.qnaListCount(bookID, k1);
			k1 = "상품후기";
			
		}
		//전체 글 개수 가져오기
		
		//System.out.println(listCount);	
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
		
		pageInfoQna = new PageInfo(page, maxPage, startPage, endPage, pageBlock, listCount);
// ------------------------------------  상품문의     ------------------------------------------------		
		
// ------------------------------------ 상품후기  ---------------------------------------------------		
		if(k1.equals("상품후기")) {
			listCount = 0; // 초기화
			pageInfoReview.setK1(k1);
			articleReviewList = reviewListSerivce.getReviewBoard(pageInfoReview, bookID);
			listCount = reviewListSerivce.reviewListCount(bookID, k1);
		}
	
		//System.out.println(listCount);	
		// 1. 총 페이지 수 계산
		 maxPage = listCount / limit + (listCount % limit == 0 ? 0 : 1);
		// 페이징 사이즈
		 pageBlock = 10;
		// 2. 시작 페이지 번호
		 startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		// 3. 마지막 페이지 번호
		 endPage = startPage + pageBlock - 1;
		
		// 마지막 페이지 번호가 총 페이지 수보다 클 경우
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		pageInfoReview = new PageInfo(page, maxPage, startPage, endPage, pageBlock, listCount);
// ------------------------------------ 상품후기  ---------------------------------------------------

		
		// 가져온 book request 객체에 넣기
		request.setAttribute("book", book);
		request.setAttribute("articleQnaList", articleQnaList);
		request.setAttribute("articleReviewList", articleReviewList);
		request.setAttribute("pageInfoQna", pageInfoQna);
		request.setAttribute("pageInfoReview", pageInfoReview);
		for(int i= 0; i<articleQnaList.size(); i++) {
//			System.out.println("안녕하세요"  + articleQnaList.get(i).getAnswserList().get(i).getBoardWriter());
					
		}
		
		forward = new ActionForward();
		forward.setPath("./book/book.jsp");

		return forward;
	}

}
