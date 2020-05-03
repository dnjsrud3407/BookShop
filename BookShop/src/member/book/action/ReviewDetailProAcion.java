package member.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.book.svc.ReviewDetailService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;

public class ReviewDetailProAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDetailProAcion");
		// 상품후기 상세보기
		ActionForward forward = null;
		
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//				
//		ReviewDetailService reviewDetailService = new ReviewDetailService();
//		BookBean book = reviewDetailService.getBook(num);
		
//		if(book != null) {
//			reviewDetailService.plusReadcount(board_num);
//		}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int kID = 103;
		HttpSession session = request.getSession();
		String boardWriter = (String)session.getAttribute("uID");
		
		String page = request.getParameter("page");
		
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		BoardBean reviewDetail = reviewDetailService.getReviewDtBoard(boardNum, kID, boardWriter);
		
		if(reviewDetail != null) {
			reviewDetailService.plusReadcount(boardNum, kID, boardWriter);
		}
		request.setAttribute("reviewDetail", reviewDetail);
		request.setAttribute("page", page);

	
//		forward = new ActionForward();
//		forward.setPath("./book/review_detail.jsp");
		
		return null;
	}

}
