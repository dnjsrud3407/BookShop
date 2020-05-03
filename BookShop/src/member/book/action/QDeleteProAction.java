package member.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.book.svc.QDeleteProService;
import vo.ActionForward;
import vo.BoardBean;


public class QDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QDeleteProAction");
		
		// 상품문의 삭제
		ActionForward forward = null;
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		HttpSession session = request.getSession();
		String uID = (String)session.getAttribute("uID");
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		BoardBean boardBean = new BoardBean();
		boardBean.setBoardNum(boardNum);
		boardBean.setBoardWriter(uID);
		
		QDeleteProService qDeleteProService = new QDeleteProService();
		
		boolean isDeleteSuccess = qDeleteProService.removeQuestion(boardBean);
//		int num = Integer.parseInt(request.getParameter("num"));
//		String page = request.getParameter("page");
//		
//		boolean isRightUser = false;
//		QDeleteProAction qDeleteProAction = new QDeleteProAction();
//		isRightUser = qDeleteProAction.isQuestionWriter(num, request.getParameter("board_pass"));
//		

		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("Book.book?bookID=" + bookID);
			forward.setRedirect(true);
		}
	
		
		return forward;
		
	}

}
