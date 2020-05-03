package member.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.book.svc.ReviewModifyProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.BookBean;

public class ReviewModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 상품후기 수정
		System.out.println("ReviewModifyFormAction");
//		int board_num = Integer.parseInt(request.getParameter("board_num"));
//		String page = request.getParameter("page");
//		
//		BookBean views = null;
//		ReviewModifyProService viewModifyProService = new ReviewModifyProService();
//		views = viewModifyProService.getReviews(num);
	
//		request.setAttribute("article", article);
//		request.setAttribute("page", page);
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		System.out.println(boardNum);
		
		HttpSession session = request.getSession();
		String boardWriter = (String)session.getAttribute("uID");
		int kID = 103;
		
		ReviewModifyProService reviewModify = new ReviewModifyProService();
		BoardBean boardBean = reviewModify.getReviews(boardNum, kID, boardWriter);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/book/review_modify.jsp");
		request.setAttribute("boardBean", boardBean);
		
		return forward;	
		}

}
