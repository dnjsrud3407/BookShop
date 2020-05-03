package member.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.book.svc.ReviewDeleteProService;
import vo.ActionForward;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDeleteProAction");
		
		ActionForward forward = null;
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		HttpSession session = request.getSession();
		String boardWriter = (String)session.getAttribute("uID");
		
		String k1="상품후기";
		
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
		boolean isDeleteSuccess = reviewDeleteProService.removeReview(boardNum, boardWriter, k1);
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//		
//		boolean isRightUser = false;
//		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
//		isRightUser = reviewDeleteProService.isReviewWriter(num, request.getParameter("board_pass"));
//		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 성공!!')");
			out.println("histroy.back()");
			out.println("</script>");
		}

		
		forward = new ActionForward();
		forward.setPath("Book.book?bookID=" + bookID);
		forward.setRedirect(true);

		return forward;
	}

}
